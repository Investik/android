package com.example.alex.odometer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private OdometrService odometer;
    private boolean bound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            OdometrService.OdometerBinder odometerBinder =
                    (OdometrService.OdometerBinder) binder;
            odometer = odometerBinder.getOdometer();
            bound = true;
            Log.v("here", "done");
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("here", "disconnected");
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("onStart", "start");
        Intent intent = new Intent(this, OdometrService.class);
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        watchMileage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }

    }
    private void watchMileage() {
        final TextView distanceView = (TextView)findViewById(R.id.distance);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                String check = "0";
                if (odometer != null) {
                    distance = odometer.getMiles();
                    check = "2";
                }
                String distanceStr = String.format("%1$,.2f metres", distance);

                distanceView.setText(distanceStr);
                handler.postDelayed(this, 10000);
                Log.v("here", String.valueOf(bound));
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
