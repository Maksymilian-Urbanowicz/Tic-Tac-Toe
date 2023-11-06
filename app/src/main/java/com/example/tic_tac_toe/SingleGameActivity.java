package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SingleGameActivity extends AppCompatActivity {
        public Game game;
        public Button[][] buttons;
    //    public Boolean player1 = true;
//    public Boolean player2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game();

        buttons = new Button[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                int id = getResources().getIdentifier("btn" + j + i, "id", getPackageName());
                buttons[j][i] = (Button) findViewById(id);
                Button btn = buttons[j][i];
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(game.player1==true) {
                            btn.setText("X");
                            btn.setEnabled(false);
                            game.player2=true;
                            game.player1=false;
                        }else{
                            btn.setText("O");
                            btn.setEnabled(false);
                            game.player1=true;
                            game.player2=false;
                        }

                        int gameStatus = checkBoard(buttons);
                        if(gameStatus ==1){
                            game.scoreP1++;
                            //Toast.makeText(SingleGameActivity.this, ""+ game.scoreP1, Toast.LENGTH_LONG).show();
                            TextView tvScoreP1 = findViewById(R.id.tvScoreP1);
                            tvScoreP1.setText(String.valueOf(game.scoreP1));

                            new CountDownTimer(1000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    clearBoard(true);
                                }
                            }.start();

                        }else if(gameStatus ==2){
                            game.scoreP2++;
                            //Toast.makeText(SingleGameActivity.this, ""+ game.scoreP2, Toast.LENGTH_LONG).show();
                            TextView tvScoreP2 = findViewById(R.id.tvScoreP2);
                            tvScoreP2.setText(String.valueOf(game.scoreP2));

                            new CountDownTimer(1000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    clearBoard(true);
                                }
                            }.start();
                        }
                        checkWinner();
                    }
                });
            }
        }
    }

    public int checkBoard ( Button[][] buttons) {

        for(int j=0; j<3; j++) {
            int i=0;
            if (buttons[j][i].getText().toString().equals(buttons[j][i + 1].getText().toString()) && buttons[j][i].getText().toString().equals(buttons[j][i + 2].getText().toString())) {
                //vertical
                if (buttons[j][i].getText().toString().contains("X")) {
                    setWin(
                            buttons[j][i],
                            buttons[j][i + 1],
                            buttons[j][i + 2]
                    );
                    return 1;
                } else if (buttons[j][i].getText().toString().contains("O")) {
                    setWin(
                            buttons[j][i],
                            buttons[j][i + 1],
                            buttons[j][i + 2]
                    );
                    return 2;
                }
            }
        }

        for(int i=0; i<3; i++) {
            int j=0;
            if (buttons[j][i].getText().toString().equals(buttons[j + 1][i].getText().toString()) && buttons[j][i].getText().toString().equals(buttons[j + 2][i].getText().toString())) {
                //horizontal
                if (buttons[j][i].getText().toString().contains("X")) {
                    setWin(
                            buttons[j][i],
                            buttons[j + 1][i],
                            buttons[j + 2][i]
                    );
                    return 1;
                } else if (buttons[j][i].getText().toString().contains("O")) {
                    setWin(
                            buttons[j][i],
                            buttons[j + 1][i],
                            buttons[j + 2][i]
                    );
                    return 2;
                }
            }
        }

        int i = 0;
        int j = 0;

        if (buttons[j][i].getText().toString().equals(buttons[j + 1][i + 1].getText().toString()) && buttons[j][i].getText().toString().equals(buttons[j + 2][i + 2].getText().toString())) {
            //diagonal left to right
            if (buttons[j][i].getText().toString().contains("X")) {
                setWin(
                        buttons[j][i],
                        buttons[j+1][i+1],
                        buttons[j+2][i+2]
                );
                return 1;
            } else if (buttons[j][i].getText().toString().contains("O")) {
                setWin(
                        buttons[j][i],
                        buttons[j+1][i+1],
                        buttons[j+2][i+2]
                );
                return 2;
            }
        }

        i=0;
        j=0;

        if (buttons[j+2][i].getText().toString().equals(buttons[j + 1][i + 1].getText().toString()) && buttons[j + 2][i].getText().toString().equals(buttons[j][i + 2].getText().toString())) {
            //diagnoal right to left

            if (buttons[j + 2][i].getText().toString().contains("X")) {
                setWin(
                        buttons[j+2][i],
                        buttons[j+1][i+1],
                        buttons[j][i+2]
                    );
                return 1;
            } else if (buttons[j + 2][i].getText().toString().contains("O")) {
                //Toast.makeText(SingleGameActivity.this, "Wygrał XD", Toast.LENGTH_LONG).show();
                setWin(
                        buttons[j+2][i],
                        buttons[j+1][i+1],
                        buttons[j][i+2]
                );
                return 2;
            }
        }
            return 0;
    }

    public void setWin(Button btn1, Button btn2, Button btn3){
        btn1.setBackgroundResource(R.drawable.btn_win);
        btn2.setBackgroundResource(R.drawable.btn_win);
        btn3.setBackgroundResource(R.drawable.btn_win);
    }

    public void checkWinner(){
        TextView tvWinner = findViewById(R.id.tvWinner);
        TextView tvWinner1 = findViewById(R.id.tvWinner1);
        ImageView imgWinner = findViewById(R.id.imgWinner);

        if(game.scoreP1 ==3){
            animateView(tvWinner, R.anim.fade_in);
            animateView(tvWinner1, R.anim.fade_in);
            animateView(imgWinner, R.anim.fade_in);
            tvWinner1.setText("Player One");
            clearBoard(false);
        }else if(game.scoreP2 ==3){
            animateView(tvWinner, R.anim.fade_in);
            animateView(tvWinner1, R.anim.fade_in);
            animateView(imgWinner, R.anim.fade_in);
            tvWinner1.setText("Player Two");
            clearBoard(false);
        }
    }

    public void animateView(View view, int animationId) {
        Animation animation = AnimationUtils.loadAnimation(this, animationId);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    public void clearBoard(Boolean bool){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                Button btn = buttons[j][i];
                if(bool == false) {
                    btn.setText(" ");
                    btn.setBackgroundResource(R.drawable.btn_table_customized);
                }
                btn.setEnabled(bool);
            }
        }
    }

//        Toast.makeText(SingleGameActivity.this, "" + String.valueOf(buttons[j][i].getText().toString().contains("X")), Toast.LENGTH_LONG).show();
}
