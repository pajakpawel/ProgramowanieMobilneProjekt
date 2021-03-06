package com.example.pawe.milionerzy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.pawe.milionerzy.R;

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
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    public void rulesButtonOnClick(View view)
    {
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }
}
