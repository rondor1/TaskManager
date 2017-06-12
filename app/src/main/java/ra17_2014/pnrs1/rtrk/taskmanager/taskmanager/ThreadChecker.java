package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class ThreadChecker extends Thread {


    private boolean mRun;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat;
    private static int SLEEP_PERIOD = 5000;
    private NotificationManager mNotificationManager;
    private Notification.Builder mNotificationBuilder;
    private boolean mNotificationReady;

    public ThreadChecker(Context context)
    {
        mRun = true;
        mContext = context;
        mSimpleDateFormat = new SimpleDateFormat("hh:mm");
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context
                .NOTIFICATION_SERVICE);
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(android.R.drawable.ic_popup_reminder);

    }

    @Override
    public synchronized void start() {
        mRun = true;
        super.start();
    }

    public synchronized void exit()
    {
        mRun = false;
    }

    @Override
    public void run() {
        super.run();
        while(mRun)
        {
            Log.i("Robert", "thread running");
            String mMessage = "Task needs to be done in 15 minutes ";
            for(Task mTask : MainActivity.mTaskList)
            {
                if(mTask.getTaskDate().equals(mContext.getResources().getString(R.string.today))
                        && mTask.isTaskReminder() == 1)
                {
                    Log.i("Robert", "If statement, no 15");
                    Calendar mCurrentDate = Calendar.getInstance();
                    Calendar mTaskTime = Calendar.getInstance();
                    try
                    {
                        mTaskTime.setTime(mSimpleDateFormat.parse(mTask.getTaskTime()));
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    if(mTaskTime.get(Calendar.HOUR_OF_DAY) == mCurrentDate.get(Calendar.HOUR_OF_DAY))
                    {
                        if((mTaskTime.get(Calendar.MINUTE) - mCurrentDate.get(Calendar.MINUTE)) <= 15 &&
                                (mTaskTime.get(Calendar.MINUTE) - mCurrentDate.get(Calendar.MINUTE))>=0)
                        {
                            if(mNotificationReady)
                            {
                                mMessage += ", " + mTask.getTaskName();
                            }
                            else
                            {
                                mMessage += mTask.getTaskName();
                            }
                            mNotificationReady = true;
                        }
                    }
                    else if((mTaskTime.get(Calendar.HOUR_OF_DAY) - mCurrentDate.get(Calendar.HOUR_OF_DAY) )== 1)
                    {
                        if((mTaskTime.get(Calendar.MINUTE)+60 - mCurrentDate.get(Calendar.MINUTE)) <= 15 &&
                                (mTaskTime.get(Calendar.MINUTE)+60 - mCurrentDate.get(Calendar.MINUTE)+60)>=0)
                        {
                            if(mNotificationReady)
                            {
                                mMessage += ", " + mTask.getTaskName();
                            }
                            else
                            {
                                mMessage += mTask.getTaskName();
                            }
                            mNotificationReady = true;
                        }
                    }
                }
            }
            if(mNotificationReady) {
                mNotificationBuilder.setContentText(mMessage);

                mNotificationManager.notify(0, mNotificationBuilder.build());

            }else{

                mNotificationManager.cancel(0);

            }
            try
            {
                sleep(SLEEP_PERIOD);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
