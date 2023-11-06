package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch swtTypeGame = findViewById(R.id.swtTypeGame);
                if(Boolean.parseBoolean(String.valueOf(swtTypeGame.getShowText())) == true){
                    Intent intent  = new Intent(MainActivity.this, SingleGameActivity.class);
                    startActivity(intent);
                }else{

                }
                //Toast.makeText(MainActivity.this, "" + String.valueOf(swtTypeGame.getShowText()), Toast.LENGTH_LONG).show();
            }
        });
    }
}