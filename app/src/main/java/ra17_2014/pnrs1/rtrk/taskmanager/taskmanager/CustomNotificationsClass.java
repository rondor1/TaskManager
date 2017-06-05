package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.RemoteException;

/**
 * Created by ASUS on 6/4/2017.
 */

public class CustomNotificationsClass extends NotificationAidlInterface.Stub {


    private Context mContext;
    private NotificationManager mNotificationManager;
    private Notification.Builder mNotificationBuilder;

    public CustomNotificationsClass(Context context)
    {
        mContext = context;
    }

    @Override
    public void notificationAdd()  {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.todoicon))
                .setContentText("Task added!");
        mNotificationManager.notify(1, mNotificationBuilder.build());
    }

    @Override
    public void notificationEdit() throws RemoteException {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.todoicon))
                .setContentText("Task edited!");
        mNotificationManager.notify(1, mNotificationBuilder.build());
    }

    @Override
    public void notificationDelete() throws RemoteException {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.todoicon))
                .setContentText("Task deleted!");
        mNotificationManager.notify(1, mNotificationBuilder.build());
    }
}
