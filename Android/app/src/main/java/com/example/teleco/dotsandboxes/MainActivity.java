package com.example.teleco.dotsandboxes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button human;
    Button random;
    Button intelligent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        human = (Button) findViewById(R.id.human);
        random = (Button) findViewById(R.id.random);
        intelligent = (Button) findViewById(R.id.intelligent);

        human.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, HumanVsHuman.class));
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, HumanVsRandom.class));
            }
        });

        intelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MainActivityHumanVsIntelligent.class));
            }
        });


    }
}
