package com.example.pawe.milionerzy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private AlertDialog.Builder dlgAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button buttonA = (Button)(findViewById(R.id.button1));
        Button buttonB = (Button)(findViewById(R.id.button2));
        Button buttonC = (Button)(findViewById(R.id.button3));
        Button buttonD = (Button)(findViewById(R.id.button4));

        dlgAlert  = new AlertDialog.Builder(this);

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
    }


    public void ShowDialog(String message)
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
}
