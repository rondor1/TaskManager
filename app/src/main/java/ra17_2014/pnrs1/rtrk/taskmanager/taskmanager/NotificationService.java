package ra17_2014.pnrs1.rtrk.taskmanager.taskmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {

    private ThreadChecker mThreadChecker;
    private CustomNotifications mCustomNotifications;

    @Override
    public void onCreate() {
        mThreadChecker = new ThreadChecker(this);
        mThreadChecker.start();
        mCustomNotifications = new CustomNotifications(this);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mThreadChecker.exit();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mCustomNotifications;
    }
}
