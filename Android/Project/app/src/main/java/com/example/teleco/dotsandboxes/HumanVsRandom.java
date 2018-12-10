package com.example.teleco.dotsandboxes;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HumanVsRandom extends AppCompatActivity {

    Table table;
    private int T = 1;
    int points1 = 0;
    int points2 = 0;
    HumanPlayer player1 = new HumanPlayer();
    RandomPlayer player2 = new RandomPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_vs_random);
    }

    public void onStart()
    {
        table = new Table(5,5);
        super.onStart();
        TextView player1Points = (TextView) findViewById(R.id.player1Points);
        TextView player2Points = (TextView) findViewById(R.id.player2Points);
        player1Points.setText("0");
        player2Points.setText("0");
    }

    public void onClick(View view) {

        int buttonId = view.getId();
        Button button =  (Button) findViewById(buttonId);
        TextView player1Points = (TextView) findViewById(R.id.player1Points);
        TextView player2Points = (TextView) findViewById(R.id.player2Points);


        if (getPlayer(this.T) == 1) {
            button.setBackgroundColor(Color.GREEN);
            String moveString = view.getTag().toString();
            String[] move;
            String comma = ",";
            move = moveString.split(comma);
            Play play = new Play(Integer.parseInt(move[0]), Integer.parseInt(move[1]), move[2], this.T);
            this.table.insertPlay(play);
            int aux1 = this.table.getPointsP1();
            if (this.points1 == aux1){
                this.T++;
            } else {
                this.T = T + 2;
                this.points1 = aux1;
            }
            player1Points.setText(Integer.toString(aux1));

            if(this.table.isFinished()) {

                Intent i = new Intent(getApplicationContext(), Results.class);
                i.putExtra("points1", Integer.toString(1));
                i.putExtra("points2", Integer.toString(this.points2));
                startActivity(i);
            } else {
                randomTurn();
            }
        }
        if(this.table.isFinished()) {

            Intent i = new Intent(getApplicationContext(), Results.class);
            i.putExtra("points1", Integer.toString(1));
            i.putExtra("points2", Integer.toString(this.points2));
            startActivity(i);
        }
    }

    public static int getPlayer(int T) {
        if ((T % 2) != 0) {
            return 1;
        } else {
            return 2;
        }
    }

    private void randomTurn() {
        Random random = new Random();
        int x, y;
        String pos;
        do {
            x = random.nextInt(5 - 1) + 0;
            y = random.nextInt(5 - 1) + 0;
            String positions = "nseo";
            pos = String.valueOf(positions.charAt(random.nextInt(4)));
        } while (!this.table.isEdgeFree(x, y, pos));

        MapeButtons mapping = new MapeButtons();
        String randomButtonIdString = mapping.getIdButton(Integer.toString(x) + "," + Integer.toString(y) + "," + pos);
        int randomButtonId = getApplicationContext().getResources().getIdentifier(randomButtonIdString, "id", getApplicationContext().getPackageName());
        Button buttonTest = (Button) findViewById(randomButtonId);
        buttonTest.setBackgroundColor(Color.RED);

        Play play = new Play(x, y, pos, this.T);
        this.table.insertPlay(play);
        int aux2 = this.table.getPointsP1();
        if (this.points2 == aux2){
            this.T++;
        } else {
            this.T = T + 2;
            this.points2 = aux2;
            randomTurn();
        }
    }
}
