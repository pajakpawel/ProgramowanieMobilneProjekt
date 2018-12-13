package com.example.pawe.milionerzy.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BoundService extends IntentService {

    private final IBinder mBinder = new BoundServiceBinder();
    private int mCounter = 10;

    public static final long INTERVAL = 1000;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;

    private TextView timerTextView = null;

    private ServiceCallBacks serviceCallBacks;

    public BoundService() {
        super("BoundService");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Toast.makeText(this, "Bounded Service start", Toast.LENGTH_SHORT).show(); //TODO delete
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new BoundService.TimeDisplayTimerTask(), INTERVAL, INTERVAL);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    @Override
    public void onDestroy()
    {
        //Log.d("DEBUG","BoundService onDestroy()"); TODO delete
        mTimer.cancel();
        Toast.makeText(getApplicationContext(), "Bound Service stopped", Toast.LENGTH_SHORT).show(); //TODO delete
    }

    public class BoundServiceBinder extends Binder
    {
        public BoundService getService()
        {
            return BoundService.this;
        }
    }


    private class TimeDisplayTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mCounter -= 1;
                    timerTextView.setText(String.valueOf(mCounter));
                    if(mCounter == 0)
                    {
                        mTimer.cancel();
                        serviceCallBacks.showLostDialog();
                    }
                    //Toast.makeText(getApplicationContext(), "Bound Service running", Toast.LENGTH_SHORT).show();// TODO delete
                }
            });
        }
    }

    public void setTimerTextView(TextView t)
    {
        this.timerTextView = t;
    }

    public void setCallbacks(ServiceCallBacks callbacks)
    {
        serviceCallBacks = callbacks;
    }

/*    public void stopTimer() TODO delete
    {
        //mTimer.cancel();
        //stopSelf();
        this.onDestroy();
        Toast.makeText(this, "Bounded Service stopTimer", Toast.LENGTH_SHORT).show(); //TODO delete
    }*/
}
