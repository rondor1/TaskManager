package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

/**
 * Created by student on 8.6.2017.
 */

public class StatisticsNative {

    public native float getStatisticsResult(float mDone, float mSum);

    static
    {
        System.loadLibrary("StatisticsNative");
    }
}
