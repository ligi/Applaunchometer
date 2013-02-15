package org.ligi.launchometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntoActivity extends Activity {

    private String package2start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro);


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
                finish();
            }
        });

        setTitle("Launch-O-Meter Result");

    }

}
