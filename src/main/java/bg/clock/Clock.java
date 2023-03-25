package bg.clock;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
/**
 * Clock will do everything clock related including printing
 * and logic.
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
        //when clock is created
        now = LocalTime.now();
    }
    /**
     *
     * @param object This will typically be the instance of localTime
     * @return false indicates a change true is the same
     */

    public boolean equals(Object object)
    {
        if(object == this) return true;
        //this is when it passes in local time
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
        if(!equals(now)) return;
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
        return now.format(DateTimeFormatter.ofPattern("a"));
    }


    public LocalTime getNow()
    {return now;}


    /**
     * Returns the clock as the String
     * @return output of: 00:00:00 AM/PM
     */
    @Override
    public String toString()
    {
        return now.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }
}
