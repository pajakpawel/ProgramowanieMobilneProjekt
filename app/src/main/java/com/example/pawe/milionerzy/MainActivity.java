package com.example.pawe.milionerzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void newGameButtonOnClick(View view)
    {

    }

    public void rulesButtonOnClick(View view)
    {
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }
}
