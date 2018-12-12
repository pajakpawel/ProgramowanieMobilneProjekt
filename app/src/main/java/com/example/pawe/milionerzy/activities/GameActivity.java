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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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

        while(Record.previousQuestions.contains(id_record))
        {
            id_record = rand.nextInt(end) + start;
        }

        //Log.d("xd","\"ILE REKORDOW: " + id_record);
        Record.previousQuestions.add(id_record);

//        for (int id : Record.previousQuestions)
//        {
//            Log.d("xd", "Element: " + id);
//        }


        Record record = dbManager.getRecord(id_record);
        final String correctAnswer = record.getCorrectAnswer();

        textView.setText(record.getQuestion());
        buttonA.setText("A." + record.getAnswer1());
        buttonB.setText("B." + record.getAnswer2());
        buttonC.setText("C." + record.getAnswer3());
        buttonD.setText("D." + record.getAnswer4());

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(correctAnswer, "A");
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(correctAnswer, "B");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(correctAnswer, "C");
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(correctAnswer, "D");
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


    public void ShowDialog(String correctAnswer, String answer)
    {
        if(mBound)
            mService.stopTimer();

        String message = "";

        //Log.d("xd", correctAnswer + " " + answer);

        if (correctAnswer.equals(answer))
        {
            if (Record.previousQuestions.size() >= 5)
            {
                Record.previousQuestions.clear();
                Dialog("Wygrałeś milion. Gratulacje!");
                return;
            }


            message = "Gratulacje, przechodzisz do następnego pytania";
            dlgAlert.setMessage(message)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(GameActivity.this, GameActivity.class));
                                }
                            });
            dlgAlert.show();
        }
        else
        {
            message = "Błędna odpowiedź, przegrałeś";
            Dialog(message);
        }
    }

    public void Dialog(String message)
    {
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
        Dialog("Koniec czasu. Przegrałeś");
    }
}
