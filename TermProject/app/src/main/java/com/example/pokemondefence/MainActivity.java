package com.example.pokemondefence;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int[] images = new int[] {R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5};

    // 애니메이션 변수 선언
    Animation anim_test;
    Animation anim_test2;
    Animation anim_test3;
    Animation anim_test4;
    Animation anim_test5;
    ImageButton btn_test;
    ImageView monster2;
    ImageView monster3;
    ImageView monster4;
    ImageView monster5;

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


        // 애니메이션 리소스 할당
        anim_test = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anims);
        anim_test2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anims2);
        anim_test3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anims3);
        anim_test4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anims4);
        anim_test5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anims5);
        btn_test = findViewById(R.id.btn_test);
        monster2 = findViewById(R.id.monster2);
        monster3 = findViewById(R.id.monster3);
        monster4 = findViewById(R.id.monster4);
        monster5 = findViewById(R.id.monster5);


        imageView1 = findViewById(R.id.test1);
        imageView2 = findViewById(R.id.test2);
        imageView3 = findViewById(R.id.test3);
        imageView4 = findViewById(R.id.test4);
        imageView5 = findViewById(R.id.test5);
        reRoll = findViewById(R.id.re_roll);

        // 버튼 누르면 애니메이션 실행
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_test.startAnimation(anim_test);
                monster2.startAnimation(anim_test2);
                monster3.startAnimation(anim_test3);
                monster4.startAnimation(anim_test4);
                monster5.startAnimation(anim_test5);

            }
        });

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