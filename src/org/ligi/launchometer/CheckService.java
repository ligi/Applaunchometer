package org.ligi.launchometer;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * User: ligi
 * Date: 2/14/13
 * Time: 11:02 PM
 */
public class CheckService extends Service {

    private String package2start;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        package2start=intent.getStringExtra("package2start");
        new CheckAsyncTask().execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    class CheckAsyncTask extends AsyncTask<Void, Void, Void> {

        private long start_time;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent intent = new Intent(CheckService.this, ResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("time",(int)(System.currentTimeMillis()-start_time));
            startActivity(intent);

        }

        @Override
        protected Void doInBackground(Void... params) {
            start_time=System.currentTimeMillis();
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            while (true) {

                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

                for (ActivityManager.RunningAppProcessInfo rapi : runningAppProcesses) {
                    if (rapi.processName.equals(package2start))
                        return null;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
