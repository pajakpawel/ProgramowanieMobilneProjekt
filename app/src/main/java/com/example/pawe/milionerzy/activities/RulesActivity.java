package com.example.pawe.milionerzy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.pawe.milionerzy.R;
import com.example.pawe.milionerzy.fragments.RulesFragment1;
import com.example.pawe.milionerzy.fragments.RulesFragment2;

public class RulesActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new RulesFragment1()).commit();

        Button btn_page_1 = (Button) findViewById(R.id.rules_page_1);
        btn_page_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new RulesFragment1()).commit();
            }
        });

        Button btn_page_2 = (Button) findViewById(R.id.rules_page_2);
        btn_page_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new RulesFragment2()).commit();
            }
        });
    }
}
