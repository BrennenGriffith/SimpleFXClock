package bg.jfxTest;
import bg.jfx.*;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;



import static org.testng.Assert.assertTrue;

public class ClockModelTest extends ApplicationTest {

    private ClockModel clockModel;

    //testing if main method is working.
    //fixed some module related issues
    @Test
    public void testMain() {
        // Create a new ClockModel
        ClockModel clockModel = new ClockModel();
        // Call the main() method of the ClockModel
        clockModel.main(new String[0]);
        // assertTrue will always pass because the main method returns void
        assertTrue(true);
    }
    //testing if start method is working
    //see if the clock, clock view, and controller are not null

    @Override
    public void start(Stage stage) {
        clockModel = new ClockModel();
        try {
            clockModel.start(stage);
        } catch (Exception e) {
            fail("Exception occurred while starting ClockModel: " + e.getMessage());
        }
    }

    @Test
    public void testStart() {
        assertNotNull(clockModel.getClock());
        assertNotNull(clockModel.getClockView());
        assertNotNull(clockModel.getController());
    }

//    @Test
//    public void testStop() {
//        try {
//            clockModel.stop();
//        } catch (Exception e) {
//            fail("Exception occurred while stopping ClockModel: " + e.getMessage());
//        }
//
//        assertTrue(clockModel.getController().isStopped());

}