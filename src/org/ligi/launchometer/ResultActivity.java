package org.ligi.launchometer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ResultActivity extends Activity {

    private String package2start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        TextView tv=(TextView)findViewById(R.id.text);
        int time = getIntent().getIntExtra("time", -1);
        double apps_p_s=(int)((60000.0/(time)));
        tv.setText("Yea you launched the App in "+ time/1000 +"."+ Math.abs(time) %1000+"s  - thats " + apps_p_s +"Apps/min");

        Button exit_btn=(Button)findViewById(R.id.button_exit);
        Button next_btn=(Button)findViewById(R.id.button_next);

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultActivity.this,Launchometer.class);
                startActivity(i);
                finish();
            }
        });
        setTitle("Launch-O-Meter Result");

    }

}
