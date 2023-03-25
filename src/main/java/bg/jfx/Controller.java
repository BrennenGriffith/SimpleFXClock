package bg.jfx;

import bg.clock.Clock;
import javafx.application.HostServices;
import javafx.event.ActionEvent;


import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
/**
 * This class takes care of logic that has to do with
 * Clock to data to the Javafx GUI
 * Adding Button logic
 * Adding Menu Logic for an about page
 */
public class Controller {
    /**
     * run's the equals method to check if now needs to be changed
     * every second
     */
    private ScheduledExecutorService scheduleExecutor;
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
     * @param clock use clock logic
     * @param view use view logic
     */
    public Controller(Clock clock, MainClockView view, HostServices hostServices)
    {
        this.clock = clock;
        this.view = view;
        this.hostServices = hostServices;

        eventHandlers();
        //runs the clock periodically
        runClock();
        //event handlers

    }

    private void eventHandlers()
    {
        view.getEdit().setOnAction(this::changeTime);
        view.getAbout().setOnAction(this::aboutPage);
    }
    private void changeTime(ActionEvent e)
    {
        changeTime = new ChangeTime(this);
        changeTime.showAndWait();
    }

    private void aboutPage(ActionEvent e)
    {
        About about = new About(hostServices);
        about.showAndWait();
    }


    private void runClock()
    {
       // ExecutorService executorService = Executors.newSingleThreadExecutor();

        //makes a runnable
        Runnable runClock = () -> {
                //run the clock
                clock.run();
                //change ui each time after
                changeUI();
        };


        //then create a new thread for that function to be run in
        scheduleExecutor = Executors.newScheduledThreadPool(2);
        //finally set the function schedule with zero delay every one second to check th time
        scheduleExecutor.scheduleAtFixedRate(runClock, 0, 1, TimeUnit.SECONDS);


    }

    /**
     * Stopping any thread started anything that needs to stop
     * When the application is closed
     */
    public void stop()
    {
        scheduleExecutor.shutdown();
    }

    private void changeUI()
    {
        if(currentTimeState == TimeState.MILITARY)
            view.getTime().setText(clock.getNow().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        else
            view.getTime().setText(clock.getNow().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        //sets time data this code will change if i change from 24 hour

        //set the am pm text on javafx
        view.getAmPm().setText(clock.getAmPm());
    }

//    public boolean isStopped() {
//    }

    public enum TimeState
    {
        STANDARD, MILITARY;

    }

    public void switchStates()
    {
        currentTimeState = currentTimeState
                == TimeState.STANDARD ? TimeState.MILITARY : TimeState.STANDARD;
        changeUI();
    }

    public TimeState getCurrentTimeState() {
        return currentTimeState;
    }
}