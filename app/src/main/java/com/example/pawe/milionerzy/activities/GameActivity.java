package com.example.pawe.milionerzy.activities;

import android.app.AlertDialog;
import android.content.*;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pawe.milionerzy.DBobjects.DBManager;
import com.example.pawe.milionerzy.models.Record;
import com.example.pawe.milionerzy.service.BoundService;
import com.example.pawe.milionerzy.R;
import com.example.pawe.milionerzy.service.ServiceCallBacks;

import java.io.Console;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements ServiceCallBacks
{

    private AlertDialog.Builder dlgAlert;

    private BoundService mService;
    private boolean mBound = false;
    Intent intentBoundService;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonA = (Button)(findViewById(R.id.button1));
        Button buttonB = (Button)(findViewById(R.id.button2));
        Button buttonC = (Button)(findViewById(R.id.button3));
        Button buttonD = (Button)(findViewById(R.id.button4));
        TextView textView = findViewById(R.id.textView3);

        dlgAlert  = new AlertDialog.Builder(this);

        //wypełnienie rekordu danymi z bazy
        dbManager = new DBManager(this);
        int start = 1;
        int end = dbManager.getCountOfRecords();
        Random rand = new Random();
        int id_record = rand.nextInt(end) + start;

        //Log.d("xd","\"ILE REKORDOW: " + id_record);

        Record record = dbManager.getRecord(id_record);

        textView.setText(record.getQuestion());
        buttonA.setText("A." + record.getAnswer1());
        buttonB.setText("B." + record.getAnswer2());
        buttonC.setText("C." + record.getAnswer3());
        buttonD.setText("D." + record.getAnswer4());

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog("Niepoprawna odpowiedź. Przegrałeś");
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog("Poprawna odpowiedź. Wygrałeś!");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog("Niepoprawna odpowiedź. Przegrałeś");
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog("Niepoprawna odpowiedź. Przegrałeś");
            }
        });

        intentBoundService = new Intent(GameActivity.this, BoundService.class);
        bindService(intentBoundService, mConnection, Context.BIND_AUTO_CREATE);

    }

    protected void onStop()
    {
        super.onStop();
        //Log.d("DEBUG","MainActivity onStop()"); TODO delete

        if (mBound)
        {
            mBound = false;
            mService.setCallbacks(null);
            unbindService(mConnection);
        }

    }

    protected void onRestart()
    {
        super.onRestart();
        //Log.d("DEBUG","MainActivity onRestart()"); TODO delete
        bindService(intentBoundService, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            BoundService.BoundServiceBinder binder = (BoundService.BoundServiceBinder) service;
            mService = binder.getService();
            mBound = true;

            TextView timerTextView = (TextView)findViewById(R.id.timerTextView);
            mService.setTimerTextView(timerTextView);
            mService.setCallbacks(GameActivity.this);

        }

        @Override
        public void onServiceDisconnected(ComponentName className)
        {
            mService = null;
            mBound = false;
        }
    };


    public void ShowDialog(String message)
    {
        if(mBound)
            mService.stopTimer();
        dlgAlert.setMessage(message)
                .setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(GameActivity.this, MainActivity.class));
                    }
                });
        dlgAlert.show();
    }

    @Override
    public void showLostDialog()
    {
        ShowDialog("Koniec czasu. Przegrałeś");
    }
}
