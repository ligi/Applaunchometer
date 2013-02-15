package org.ligi.launchometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeoutActivity extends Activity {

    private String package2start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        TextView tv=(TextView)findViewById(R.id.text);

        tv.setText("Foul - You took too long to launch the App - perhaps you where distracted or screwed up in another way - or I screwed up detecting an app launch - let's not point fingers here - we know stuff like this can happen - you are allowed to have 2 fouls in one session");

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
                Intent i=new Intent(TimeoutActivity.this,Launchometer.class);
                startActivity(i);
                finish();
            }
        });
        setTitle("Launch-O-Meter Timeout");

    }

}
