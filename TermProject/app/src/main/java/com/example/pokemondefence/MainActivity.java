package com.example.pokemondefence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int[] images = new int[] {R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5};

    ImageButton imageView1;
    ImageButton imageView2;
    ImageButton imageView3;
    ImageButton imageView4;
    ImageButton imageView5;

    ImageButton reRoll;
    int imageIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        imageView1 = findViewById(R.id.test1);
        imageView2 = findViewById(R.id.test2);
        imageView3 = findViewById(R.id.test3);
        imageView4 = findViewById(R.id.test4);
        imageView5 = findViewById(R.id.test5);
        reRoll = findViewById(R.id.re_roll);

    }
    int getRandom(int range, int min) {
        return (int)(Math.random() * range) + min;
    }

    public void onButton1Clicked(View v){
        changeImage();
    }

    private void changeImage() {

        if(imageIndex == 0){
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.VISIBLE);
            imageView5.setVisibility(View.VISIBLE);
            reRoll.setVisibility(View.VISIBLE);
            imageIndex = 1;
        }

        else if(imageIndex == 1){
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
            imageView5.setVisibility(View.INVISIBLE);
            reRoll.setVisibility(View.INVISIBLE);
            imageIndex = 0;
        }
    }

    public void onButtonReRoll(View view) {
        int rand = getRandom(5,0 );
        imageView1.setImageResource(images[rand]);
        rand = getRandom(5, 0);
        imageView2.setImageResource(images[rand]);
        rand = getRandom(5, 0);
        imageView3.setImageResource(images[rand]);
        rand = getRandom(5, 0);
        imageView4.setImageResource(images[rand]);
        rand = getRandom(5, 0);
        imageView5.setImageResource(images[rand]);
    }
}