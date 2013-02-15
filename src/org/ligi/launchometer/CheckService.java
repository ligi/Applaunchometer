package org.ligi.launchometer;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
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
        return null;
    }

    private void killActivity(String pkg) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

        //String pkg=getIntent().getStringExtra("pkg");
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(pkg);
    }

    class CheckAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private long start_time;

        @Override
        protected void onPostExecute(Boolean res) {
            super.onPostExecute(res);

            if (res) {
                killActivity(package2start);

                Intent intent = new Intent(CheckService.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("time",(int)(System.currentTimeMillis()-start_time));

                startActivity(intent);
            } else {

                Intent intent = new Intent(CheckService.this, TimeoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            start_time=System.currentTimeMillis();

            while (true) {

                if (isPkgRunning(CheckService.this,package2start)) return true;

                if ((System.currentTimeMillis()-start_time)>30000)
                    return false;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static boolean isPkgRunning(Context ctx,String pkg) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo rapi : runningAppProcesses) {
            if (rapi.processName.equals(pkg))
                return true;
        }
        return false;
    }
}
