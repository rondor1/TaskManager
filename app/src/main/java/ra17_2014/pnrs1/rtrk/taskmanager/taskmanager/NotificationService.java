package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by ASUS on 6/5/2017.
 */

public class NotificationService extends Service {

    private CustomNotificationsClass mCustomNotificationsClass;
    private ThreadChecker mThreadChecker;

    @Override
    public void onCreate() {
        mThreadChecker = new ThreadChecker(this);
        mThreadChecker.start();
        mCustomNotificationsClass = new CustomNotificationsClass(this);
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        mThreadChecker.exit();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mCustomNotificationsClass;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
