package org.ligi.launchometer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends BaseActivity {

    private String package2start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        TextView tv = (TextView) findViewById(R.id.text);


        Button exit_btn = (Button) findViewById(R.id.button_exit);
        Button next_btn = (Button) findViewById(R.id.button_next);
        Button share_btn = (Button) findViewById(R.id.button_share);

        final int time = getIntent().getIntExtra("time", -1);
        double apps_p_s = 60000.0 / time;

        String text = "Yea you launched the App in " + String.format("%.2f", time / 1000.0) + "s";
        text += "\nThat's " + String.format("%.2f", apps_p_s) + "Apps/min";

        getApp().results.add(time);

        setTitle("Launch-O-Meter Step " + getApp().results.size() + "/" + AppDefinitions.STEPS);


        int average=0;

        if (getApp().results.size() > 1) {
            average = time;
            int best = Integer.MAX_VALUE;
            for (Integer i : getApp().results) {
                average += i;
                if (i < best)
                    best = i;
            }

            average /= (getApp().results.size());

            text += "\nYour average is: " + String.format("%.2f", 60000.0 / average) + "Apps/min and your best this session: " + String.format("%.2f", 60000.0 / best);

        }

        final int average_final=average;   // finalize for use in onClick

        if (getApp().results.size() == AppDefinitions.STEPS) {
            getApp().reset();
            next_btn.setText("Again!");
            setTitle("Launch-O-Meter End Result");
            share_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,"Launch-O-Meter");
                    String sAux = "I can manually launch Apps with " + String.format("%.2f", 60000.0 / average_final) + "Apps/s - how fast are you? https://play.google.com/store/apps/details?id=org.ligi.launchometer";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    ResultActivity.this.startActivity(Intent.createChooser(i, "How to share?"));
                }
            });
        } else {
            share_btn.setVisibility(View.GONE);
        }

        tv.setText(text);

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, Launchometer.class);

                startActivity(i);
                finish();
            }
        });


    }

}
