package bg.jfx;

import bg.alert.About;
import bg.alert.ChangeTime;
import bg.clock.Clock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * This class takes care of logic that has to do with
 * Clock to data to the Javafx GUI
 * Adding Button logic
 * Adding Menu Logic for an about page
 * @author Brennen Griffith
 */
public class Controller {
    /**
     * Timeline to run the clock
     */
    private Timeline timeLine;

    /**
     * Clock with clock logic
     */
    private Clock clock;
    /**
     * View with view logic/ getter's
     */
    private MainClockView view;

    private ChangeTime changeTime;

    private HostServices hostServices;

    private TimeState currentTimeState = TimeState.STANDARD;

    /**
     * Controller constructor
     *
     * @param clock use clock logic
     * @param view  use view logic
     */
    public Controller(Clock clock, MainClockView view, HostServices hostServices) {
        this.clock = clock;
        this.view = view;
        this.hostServices = hostServices;

        eventHandlers();
        //runs the clock periodically
        runClock();
        //event handlers

    }

    /**
     * Registers the event handlers for the menu bar items.
     */
    private void eventHandlers() {
        view.getEdit().setOnAction(this::changeTime);
        view.getAbout().setOnAction(this::aboutPage);
        view.getExitButton().setOnAction(this::exitApplication);


    }

    /**
     * Creates and shows the ChangeTime dialog box when the Edit menu item is clicked.
     *
     * @param e The ActionEvent triggering the method call.
     */
    private void changeTime(ActionEvent e) {
        changeTime = new ChangeTime(this);
        changeTime.showAndWait();
    }

    /**
     * Creates and shows the About dialog box when the About menu item is clicked.
     *
     * @param e The ActionEvent triggering the method call.
     */
    private void aboutPage(ActionEvent e) {
        About about = new About(hostServices);
        about.showAndWait();
    }

    /**
     * Starts running the clock by setting up a timeline that updates the clock every second.
     */
    private void runClock() {
        timeLine = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            clock.run();
            changeUI();
        }), new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();

    }

    /**
     * Configures the Exit button to exit the application when clicked.
     *
     * @param e The ActionEvent triggering the method call.
     */
    private void exitApplication(ActionEvent e) {
        view.getExitButton().addEventFilter(MouseEvent.ANY, event -> {

            if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                // Mouse is over the button, consume the event to ignore the MenuBar
                event.consume();
            } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                // Mouse is no longer over the button, do not consume the event
            } else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                // Mouse clicked on the button, exit the application
                Platform.exit();
            }
        });

    }

    /**
     * Updates the UI with the current time and AM/PM indicator.
     */
    private void changeUI() {
        updateTime();
        updateAmPm();
    }

    /**
     * Updates the time displayed in the UI with the current time.
     */

    private void updateTime() {
        String pattern = getPatternHelper(currentTimeState, clock);
        String timeString = clock.getNow().format(DateTimeFormatter.ofPattern(pattern));
        view.getTime().setText(timeString);
    }

    /**
     * Updates the AM/PM indicator displayed in the UI based on the current time.
     */

    private void updateAmPm() {
        String amPm = clock.getAmPm();
        view.getAmPm().setText(amPm);
    }

    /**
     * Stops the clock when the application is closed.
     */
    public void stop() {

        timeLine.stop();
    }

    /**
     * Changes the time format used by the clock and updates the UI.
     *
     * @param state The TimeState enum value indicating the desired time format.
     */

    public void switchStates(TimeState state) {
        currentTimeState = state;

        changeUI();
    }

    /**
     * Returns the current time format state.
     *
     * @return The current TimeState enum value.
     */
    public TimeState getCurrentTimeState() {
        return currentTimeState;
    }

    /**
     * Returns the ChangeTime dialog box instance.
     *
     * @return The current ChangeTime dialog box instance.
     */
    public ChangeTime getChangeTime() {
        return changeTime;
    }

    /**
     * Helper method for obtaining the time format pattern based on the current time state.
     *
     * @param currentTimeState The current TimeState enum value.
     * @param clock            The Clock instance.
     * @return The time format pattern string.
     */
    public static String getPatternHelper(TimeState currentTimeState, Clock clock) {
        String timeFormatPattern;

        if (currentTimeState == TimeState.MILITARY) {
            timeFormatPattern = getMilitaryTimeFormat(clock);
        } else {
            timeFormatPattern = getStandardTimeFormat(clock);
        }

        return timeFormatPattern;
    }

    /**
     * Returns the time format pattern for military time.
     *
     * @param clock The Clock instance.
     * @return The time format pattern string for military time.
     */
    private static String getMilitaryTimeFormat(Clock clock) {
        String timeFormatPattern = "H:mm:ss";
        if (clock.getNow().getHour() > 9)
            timeFormatPattern = "HH:mm:ss";
        else
            timeFormatPattern = "H:mm:ss";

        return timeFormatPattern;
    }

    /**
     * Returns the time format pattern for standard time.
     *
     * @param clock The Clock instance.
     * @return The time format pattern string for standard time.
     */
    private static String getStandardTimeFormat(Clock clock) {
        String timeFormatPattern = "h:mm:ss";

        if (clock.getNow().getHour() < 10 || (clock.getNow().getHour() > 12 && clock.getNow().getHour() <= 21)) {
            timeFormatPattern = "h:mm:ss";
        } else {
            timeFormatPattern = "hh:mm:ss";
        }

        return timeFormatPattern;
    }

    /**
     * An enum representing the current time format state.
     */
    public enum TimeState {
        STANDARD, MILITARY;
    }

}