package bg.jfx;

import javafx.application.Application;
import javafx.stage.Stage;
import bg.clock.ClockModel;

/**
 * This is the entry point that holds the clockModel model view and controller
 * @author Brennen Griffith
 */
public class Entry extends Application {
    /**
     *  View Class Everything javafx related
     */
    private MainClockView clockView;
    /**
     * Logic for the clockModel that will be counted and displayed in the view
     */
    private ClockModel clockModel;
    /**
     * Controller to control both the clockModel and the view
     */
    private Controller controller;
    /**
     *  The primary stage for this application, onto which the application scene can be set.
     *  Applications may create other stages, if needed, but they will not be primary stages.
     */
    private Stage stage;



    /**
     * Main Method
     * @param args getParameters() can be used but is not
     */
    public static void main(String[] args)
    {
       launch(args);
    }

    /**
     * Initialize clockModel And Model-View-Controller
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        //creating clockModel
        clockModel = new ClockModel();
        //creating the mvc
        controller = new Controller(clockModel,clockView = new MainClockView(stage, controller), getHostServices());

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

    /**
     * Get the main clockModel view
     * @return the main clockModel view
     */
    public MainClockView getClockView() {
        return clockView;
    }
    /**
     * Get the clockModel object
     * @return the clockModel object
     */
    public ClockModel getClock() {
        return clockModel;
    }
    /**
     * Get the controller object
     * @return the controller object
     */
    public Controller getController() {
        return controller;
    }
    /**
     * Get the primary stage
     * @return the primary stage
     */
    public Stage getStage() {
        return stage;
    }
}
