    package com.example.teleco.dotsandboxes;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


    public class HumanVsHuman extends AppCompatActivity {

    Table table;
    private int T = 1;
    int points1 = 0;
    int points2 = 0;
    HumanPlayer player1 = new HumanPlayer();
    HumanPlayer player2 = new HumanPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_vs_human);
    }

    @Override
    public void onStart()
    {
        table = new Table(5,5);
        super.onStart();
        TextView activePlayer = (TextView) findViewById(R.id.activePlayer);
        TextView player1Points = (TextView) findViewById(R.id.player1Points);
        TextView player2Points = (TextView) findViewById(R.id.player2Points);
        player1Points.setText("0");
        player2Points.setText("0");
        activePlayer.setText("Player1 Turn");
    }

    public void onClick(View view) {

        int buttonId = view.getId();
        Button button =  (Button) findViewById(buttonId);
        TextView player1Points = (TextView) findViewById(R.id.player1Points);
        TextView player2Points = (TextView) findViewById(R.id.player2Points);
        TextView activePlayer = (TextView) findViewById(R.id.activePlayer);


        if (getPlayer(this.T) == 1) {

            String moveString = view.getTag().toString();
            String[] move;
            String comma = ",";
            move = moveString.split(comma);
            if (table.isEdgeFree(Integer.valueOf(move[0]), Integer.valueOf(move[1]), move[2])) {
                button.setBackgroundColor(Color.GREEN);
                Play play = new Play(Integer.parseInt(move[0]), Integer.parseInt(move[1]), move[2], this.T);
                this.table.insertPlay(play);
                int aux1 = this.table.getPointsP1();
                if (this.points1 == aux1){
                    this.T++;
                    activePlayer.setText("Player2 Turn");
                } else {
                    this.T = T + 2;
                    this.points1 = aux1;
                }
                player1Points.setText(Integer.toString(aux1));
            }


        } else {

            String moveString = view.getTag().toString();
            String[] move;
            String comma = ",";
            move = moveString.split(comma);
            if (table.isEdgeFree(Integer.valueOf(move[0]), Integer.valueOf(move[1]), move[2])) {
                button.setBackgroundColor(Color.RED);
                Play play = new Play(Integer.parseInt(move[0]), Integer.parseInt(move[1]), move[2], this.T);
                this.table.insertPlay(play);
                int aux2 = this.table.getPointsP2();
                if (this.points2 == aux2){
                    this.T++;
                    activePlayer.setText("Player1 Turn");
                } else {
                    this.T = T + 2;
                    this.points2 = aux2;
                }
                player2Points.setText(Integer.toString(aux2));
            }
        }

        if(this.table.isFinished()) {

            Intent i = new Intent(getApplicationContext(), Results.class);
            i.putExtra("points1", Integer.toString(this.points1));
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
}