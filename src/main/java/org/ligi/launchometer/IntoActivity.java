package org.ligi.launchometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntoActivity extends BaseActivity {

    private String package2start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro);

        TextView tv=(TextView)findViewById(R.id.text);
        tv.setText(Html.fromHtml(getString(R.string.intro)));

        Button exit_btn=(Button)findViewById(R.id.button_exit);
        Button next_btn=(Button)findViewById(R.id.button_start);

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(IntoActivity.this,Launchometer.class);
                startActivity(i);

                getApp().reset();

                finish();
            }
        });

        setTitle("Launch-O-Meter Intro");

    }

}
