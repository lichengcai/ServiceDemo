package cc.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mStart;
    private Button mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStart = (Button) findViewById(R.id.button_start);
        mStop = (Button) findViewById(R.id.button_stop);
        mStop.setOnClickListener(this);
        mStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                Intent startIntent = new Intent(MainActivity.this,MyService.class);
                startService(startIntent);
                break;
            case R.id.button_stop:
                Intent stopIntent = new Intent(MainActivity.this,MyService.class);
                stopService(stopIntent);
                break;
        }
    }
}
