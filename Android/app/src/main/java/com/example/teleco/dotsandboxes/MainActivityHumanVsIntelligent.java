package com.example.teleco.dotsandboxes;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityHumanVsIntelligent extends AppCompatActivity {

    Table table;
    private int T = 1;
    int points1 = 0;
    int points2 = 0;
    HumanPlayer player1 = new HumanPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_human_vs_intelligent);
    }

    public void onStart()
    {
        TextView activePlayer = (TextView) findViewById(R.id.activePlayer);
        table = new Table(4,4);
        super.onStart();
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
                    agentTurn();
                } else {
                    this.T = T + 2;
                    this.points1 = aux1;
                    updatePointsBox(1);
                    activePlayer.setText("Player1 Turn");
                }
                player1Points.setText(Integer.toString(aux1));
            }

        }
        if(this.table.isFinished()) {

            Intent i = new Intent(getApplicationContext(), Results.class);
            i.putExtra("points1", Integer.toString(this.points1));
            i.putExtra("points2", Integer.toString(this.points2));
            startActivity(i);
        }
    }

    private void updatePointsBox(int playerId) {
        int player_id = playerId;
        String pointIdString;
        int pointId;
        ImageView point;
        int p = 0;
        for (int i = 0; i < this.table.rows - 1; i++) {
            for (int j = 0; j < this.table.rows - 1; j++) {
                if (this.table.Cbox[i][j] == player_id) {
                    pointIdString = getPointId(i,j);
                    pointId = getApplicationContext().getResources().getIdentifier(pointIdString, "id", getApplicationContext().getPackageName());
                    point = (ImageView) findViewById(pointId);
                    if (player_id == 1) {
                        point.setColorFilter(Color.GREEN);
                        point.setVisibility(View.VISIBLE);
                    } else {
                        point.setColorFilter(Color.RED);
                        point.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    private String getPointId(int x, int y){
        if (x == 0 && y == 0) {
            return "point1";
        }
        if (x == 0 && y == 1) {
            return "point2";
        }
        if (x == 0 && y == 2) {
            return "point3";
        }
        if (x == 1 && y == 0) {
            return "point4";
        }
        if (x == 1 && y == 1) {
            return "point5";
        }
        if (x == 1 && y == 2) {
            return "point6";
        }
        if (x == 2 && y == 0) {
            return "point7";
        }
        if (x == 2 && y == 1) {
            return "point8";
        }
        if (x == 2 && y == 2) {
            return "point9";
        }
        return "unknown";
    }

    public static int getPlayer(int T) {
        if ((T % 2) != 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public void agentTurn(){

        TextView activePlayer = (TextView) findViewById(R.id.activePlayer);
        TextView player2Points = (TextView) findViewById(R.id.player2Points);
        Tree tree = Minimax.constructTree(this.table);
        Edge edge = tree.checkBest();
        String moveIntelligent = edge.toString();
        MapeButtons mapping = new MapeButtons();
        String buttonIdString = mapping.getIdButton(moveIntelligent);
        int intelligentButtonId = getApplicationContext().getResources().getIdentifier(buttonIdString, "id", getApplicationContext().getPackageName());
        Button buttonIntelligent = (Button) findViewById(intelligentButtonId);
        buttonIntelligent.setBackgroundColor(Color.RED);

        this.table.insertPlay(edge.getX(), edge.getY(), edge.isHorizontal(), T);;

        int aux2 = this.table.getPointsP2();
        if (this.points2 == aux2){
            this.T++;
            activePlayer.setText("Player1 Turn");

        } else {
            this.T = T + 2;
            this.points2 = aux2;
            player2Points.setText(Integer.toString(aux2));
            updatePointsBox(-1);
            if(this.table.isFinished()) {
                Intent i = new Intent(getApplicationContext(), Results.class);
                i.putExtra("points1", Integer.toString(this.points1));
                i.putExtra("points2", Integer.toString(this.points2));
                startActivity(i);
            } else {
                agentTurn();
            }
        }
    }
}

