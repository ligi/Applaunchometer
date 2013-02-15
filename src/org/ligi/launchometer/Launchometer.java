package org.ligi.launchometer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Launchometer extends Activity {

    private String package2start;

    @Override
    protected void onResume() {

        super.onResume();
        TextView tv = (TextView) findViewById(R.id.text);
        ImageView iv = (ImageView) findViewById(R.id.imageView);

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allresolveInfoList = getPackageManager().queryIntentActivities(mainIntent, 0);

        ResolveInfo activity2start_ri = allresolveInfoList.get((int) (Math.random() * allresolveInfoList.size()));
        String label = activity2start_ri.loadLabel(getPackageManager()).toString() + " ";
        package2start = activity2start_ri.activityInfo.packageName;
        iv.setImageDrawable(activity2start_ri.loadIcon(getPackageManager()));


        tv.setText(label);

        // new CheckAsyncTask().execute();
        Intent i=(new Intent(this,CheckService.class));
        i.putExtra("package2start",package2start);
        startService(i);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("Launch-O-Meter");


    }



}
