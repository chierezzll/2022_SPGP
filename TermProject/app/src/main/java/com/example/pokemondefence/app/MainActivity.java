package com.example.pokemondefence.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemondefence.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, GameActivity.class));
    }

    public void onBtnStart(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }
}