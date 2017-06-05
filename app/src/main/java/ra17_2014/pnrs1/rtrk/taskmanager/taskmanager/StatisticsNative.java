package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

/**
 * Created by ASUS on 6/5/2017.
 */

public class StatisticsNative {

    public native float getStatistics(float mNumTaskDone, float mNumAllTasks);

    static
    {
        System.loadLibrary("statistics");
    }
}
