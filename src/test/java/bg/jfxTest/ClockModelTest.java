
import bg.jfx.*;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import static org.testng.Assert.*;



import static org.testng.Assert.assertTrue;

public class ClockModelTest {


    private ClockModel clockModel;

    private Stage stage;

    @BeforeClass
    public void setUp() throws Exception{
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() ->{
            clockModel = new ClockModel();
            Stage stage = new Stage();
            try{
                clockModel.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.stage = stage;
            latch.countDown();
        });
        latch.await();


    }

    @AfterClass
    public void tearDown() {
        // Close the stage
        Platform.runLater(() -> stage.close());
    }

    @Test
    public void testTimeLabelUpdates() throws InterruptedException {
        // Wait for the clock to update a few times
        Thread.sleep(3000);

        // Check that the text in the label has changed
        String initialTime = clockModel.getClockView().getTime().getText();
        Thread.sleep(1000);
        assertNotEquals(initialTime, clockModel.getClockView().getTime().getText());
    }
    //test if it starts out in standard time
    @Test
    public void testTimeFormat() {
        String timeText = clockModel.getClockView().getTime().getText();
        Pattern timePattern = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}");
        assertTrue(timePattern.matcher(timeText).matches(), "Time format should match 'hh:mm:ss");
    }
    //tests if the about dialog is displayed
    @Test
    public void testAboutDialogDisplay() {
        Platform.runLater(() -> {
            // Simulate the "About" menu item click
            clockModel.getClockView().getAbout().fire();
        });

        // Wait for the "About" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(clockModel.getClockView().getAbout().isVisible(), "About dialog should be displayed");
    }
    //tests if the edit dialog is displayed
    @Test
    public void testEditDialogDisplay() {
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getClockView().getEdit().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(clockModel.getClockView().getEdit().isVisible(), "Edit dialog should be displayed");
    }
    //this test will check if the time is changed when the military time button is clicked
    @Test
    public void testMilitaryTime() throws InterruptedException {
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getClockView().getEdit().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the text in the label has changed
        String initialTime = clockModel.getClockView().getTime().getText();


        //click the military time button
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getController().getChangeTime().getMilitaryTimeButton().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //assert that the time has changed and the format is in military time
        assertNotEquals(initialTime, clockModel.getClockView().getTime().getText());
        String timeText = clockModel.getClockView().getTime().getText();
        //based off of the pattern in the controller class
        String expectedPattern = Controller.getPatternHelper(clockModel.getController().getCurrentTimeState(), clockModel.getClock());
        String actualPattern = DateTimeFormatter.ofPattern(expectedPattern).format(clockModel.getClock().getNow());

        assertTrue(actualPattern.equals(timeText), "Time format should match the expected pattern: " + expectedPattern + ", but got: " + timeText);

    }
    //this test will check if the time is changed when the standard time button is clicked
    @Test
    public void testStandardFromMilitaryTime() throws InterruptedException {
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getClockView().getEdit().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the text in the label has changed
        String initialTime = clockModel.getClockView().getTime().getText();


        //click the military time button
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getController().getChangeTime().getMilitaryTimeButton().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //click the military time button
        Platform.runLater(() -> {
            // Simulate the "Edit" menu item click
            clockModel.getController().getChangeTime().getStandardTimeButton().fire();
        });

        // Wait for the "Edit" dialog to be shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //assert that the time has changed and the format is in military time
        assertNotEquals(initialTime, clockModel.getClockView().getTime().getText());
        String timeText = clockModel.getClockView().getTime().getText();
        //based off of the pattern in the controller class
        String expectedPattern = Controller.getPatternHelper(clockModel.getController().getCurrentTimeState(), clockModel.getClock());
        String actualPattern = DateTimeFormatter.ofPattern(expectedPattern).format(clockModel.getClock().getNow());

        assertTrue(actualPattern.equals(timeText), "Time format should match the expected pattern: " + expectedPattern + ", but got: " + timeText);


    }


}

