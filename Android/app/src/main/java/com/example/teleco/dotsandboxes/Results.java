package com.example.teleco.dotsandboxes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button playAgain;

        playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Results.this, MainActivity.class));
            }
        });
    }

    public void onStart()
    {
        super.onStart();

        TextView player1 = (TextView) findViewById(R.id.resultPlayer1);
        TextView player2 = (TextView) findViewById(R.id.resultPlayer2);
        TextView winner = (TextView) findViewById(R.id.resultWinner);

        Intent i = getIntent();
        String pointsPlayer1 = i.getStringExtra("points1");
        String pointsPlayer2 = i.getStringExtra("points2");


        player1.setText("Player 1: " + pointsPlayer1 + " points");
        player2.setText("Player 2: " + pointsPlayer2 + " points");



        if (Integer.valueOf(pointsPlayer1) > Integer.valueOf(pointsPlayer2)) {
            winner.setText("Player 1 Wins!!!");
        } else if (Integer.valueOf(pointsPlayer1) < Integer.valueOf(pointsPlayer2)){
            winner.setText("Player 2 Wins!!!");
        } else {
            winner.setText("Draw");
        }


    }

}
