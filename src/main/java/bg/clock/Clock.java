package bg.clock;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
/**
 * Clock will do everything clock related including printing
 * and logic.
 * @author Brennen Griffith
 */
public class Clock implements Runnable {
    /**
     * now is the current time the depending on current Computer Clock
     */
    private LocalTime now;

    /**
     * Clock Constructor
     * Just sets now
     */
    public Clock()
    {
        //when clock is created, set now to the current local time
        now = LocalTime.now();
    }

    /**
     * This method checks if the object passed in is equal to the current local time.
     * @param object This will typically be the instance of localTime
     * @return false indicates a change true is the same
     */
    public boolean equals(Object object)
    {
        if(object == this) return true;

        // Cast object to LocalTime and compare hour, minute, and second values
        if(object instanceof LocalTime c)
        {
            if(now.getHour() == c.getHour())
            {
                if(now.getMinute() == c.getMinute())
                {
                    if(now.getSecond() == c.getSecond())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This Run Method Will be in a Schedule Executor setting local time depending on
     * the equals method
     */
    @Override
    public void run()
    {
        // If current time has not changed, return without updating now
        if(!equals(now)) return;
        // Update now with current local time
        now = LocalTime.now();
    }

    /**
     * Get Current Clock Hours
     * @return Hours in int
     */
    public int getHours()
    {
        return now.getHour();
    }

    /**
     * Get Current Clock Minutes
     * @return Minutes in int
     */
    public int getMinutes()
    {
        return now.getMinute();
    }

    /**
     * Get Current Clock Seconds
     * @return seconds in int
     */
    public int getSeconds()
    {
        return now.getSecond();
    }

    /**
     * Getting PM AM
     * @return am pm
     */
    public String getAmPm()
    {
        // Format current local time to retrieve AM/PM value
        return now.format(DateTimeFormatter.ofPattern("a"));
    }


    /**
     * Returns the current local time.
     * @return now the current local time
     */
    public LocalTime getNow()
    {return now;}


    /**
     * Returns the clock as the String
     * @return output of: 00:00:00 AM/PM
     */
    @Override
    public String toString()
    {
        // Format current local time as a string
        return now.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }
}
