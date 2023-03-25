package bg.jfx;

import javafx.application.Application;
import javafx.stage.Stage;
import bg.clock.Clock;

/**
 * Clock Model Holds The Entry Point of Creating the View Controller and Clock
 */
public class ClockModel extends Application {
    /**
     *  View Class Everything javafx related
     */
    private MainClockView clockView;
    /**
     * Logic for the clock that will be counted and displayed in the view
     */
    private Clock clock;
    /**
     * Controller to control both the clock and the view
     */

    private Controller controller;

    /**
     * Main Method
     * @param args getParameters() can be used but is not
     */
    public static void main(String[] args)
    {
       launch(args);
    }

    /**
     * Initialize Clock And Model-View-Controller
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        //creating clock
        clock = new Clock();
        //creating the mvc
        controller = new Controller(clock,clockView = new MainClockView(stage, controller), getHostServices());

    }

    /**
     * Overriding to shut down any other thread if any
     * @throws Exception
     */
    @Override
    public void stop() throws Exception
    {
        //regular close method in Application
        super.stop();
        //stop the task ending the program on close
        controller.stop();
    }
    //Generate Gettters

    public MainClockView getClockView() {
        return clockView;
    }

    public Clock getClock() {
        return clock;
    }

    public Controller getController() {
        return controller;
    }

}
