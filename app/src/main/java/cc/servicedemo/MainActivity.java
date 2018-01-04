package cc.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mStart;
    private Button mStop;
    private Button mBind;
    private Button mUnbind;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStart = (Button) findViewById(R.id.button_start);
        mStop = (Button) findViewById(R.id.button_stop);
        mBind = (Button) findViewById(R.id.button_bind);
        mUnbind = (Button) findViewById(R.id.button_unbind);
        mStop.setOnClickListener(this);
        mStart.setOnClickListener(this);
        mBind.setOnClickListener(this);
        mUnbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                Intent startIntent = new Intent(MainActivity.this,MyService.class);
                startService(startIntent);
                break;
            case R.id.button_stop:
                Log.d("MyService", "click Stop Service button");
                Intent stopIntent = new Intent(MainActivity.this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.button_bind:
                Intent bindIntent = new Intent(MainActivity.this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.button_unbind:
                Log.d("MyService", "click Unbind Service button");
                unbindService(connection);
                break;
        }
    }
}
