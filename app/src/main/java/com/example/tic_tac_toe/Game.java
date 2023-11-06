package com.example.tic_tac_toe;

public class Game {
    public boolean player1;
    public boolean player2;
    public int scoreP1;
    public int scoreP2;

    Game(){
        setStartPlayer();
        this.scoreP1 = 0;
        this.scoreP2 = 0;
    }

    public void setStartPlayer(){
        int rand = (int) (Math.random() * 2) + 1;
        if(rand == 1){
            this.player1 = true;
            this.player2 = false;
        } else{
            this.player2 = true;
            this.player1 = false;
        }
    }
}
