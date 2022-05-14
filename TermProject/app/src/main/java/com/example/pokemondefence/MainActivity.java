package com.example.pokemondefence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // int[] images = new int[] {R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5};
    int[] images = new int[] {R.mipmap.balsheng, R.mipmap.esanghaessi, R.mipmap.firy, R.mipmap.ggobuk, R.mipmap.gugu, R.mipmap.kash, R.mipmap.merif,
                                R.mipmap.minyung, R.mipmap.modapi, R.mipmap.pikachu, R.mipmap.sixtail, R.mipmap.ssodra};
    // 애니메이션 변수 선언
    Animation anim_test;
    Animation anim_test2;
    Animation anim_test3;
    Animation anim_test4;
    Animation anim_test5;
    ImageButton startWave;
    ImageView monster2;
    ImageView monster3;
    ImageView monster4;
    ImageView monster5;
    
    ImageButton shop1;
    ImageButton shop2;
    ImageButton shop3;
    ImageButton shop4;
    ImageButton shop5;

    ImageButton reRoll;
    ImageView coin;

    // 돈
    TextView coinCount;
    int count = 50;

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
        startWave = findViewById(R.id.btn_test);
        monster2 = findViewById(R.id.monster2);
        monster3 = findViewById(R.id.monster3);
        monster4 = findViewById(R.id.monster4);
        monster5 = findViewById(R.id.monster5);

        // 상점 목록
        shop1 = findViewById(R.id.shop1);
        shop2 = findViewById(R.id.shop2);
        shop3 = findViewById(R.id.shop3);
        shop4 = findViewById(R.id.shop4);
        shop5 = findViewById(R.id.shop5);
        reRoll = findViewById(R.id.re_roll);
        coin = findViewById(R.id.coin);
        coinCount = findViewById(R.id.coin_count);
        coinCount.setText(count+"");

        // 버튼 누르면 애니메이션 실행
        startWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim();
            }
        });

    }

    private void startAnim() {
        startWave.startAnimation(anim_test);
        monster2.startAnimation(anim_test2);
        monster3.startAnimation(anim_test3);
        monster4.startAnimation(anim_test4);
        monster5.startAnimation(anim_test5);
    }

    int getRandom(int range, int min) {
        return (int)(Math.random() * range) + min;
    }

    public void onButton1Clicked(View v){
        changeImage();
    }

    private void changeImage() {

        if(imageIndex == 0){
            shop1.setVisibility(View.VISIBLE);
            shop2.setVisibility(View.VISIBLE);
            shop3.setVisibility(View.VISIBLE);
            shop4.setVisibility(View.VISIBLE);
            shop5.setVisibility(View.VISIBLE);
            reRoll.setVisibility(View.VISIBLE);
            coin.setVisibility(View.VISIBLE);
            coinCount.setVisibility(View.VISIBLE);
            imageIndex = 1;
        }

        else if(imageIndex == 1){
            shop1.setVisibility(View.INVISIBLE);
            shop2.setVisibility(View.INVISIBLE);
            shop3.setVisibility(View.INVISIBLE);
            shop4.setVisibility(View.INVISIBLE);
            shop5.setVisibility(View.INVISIBLE);
            reRoll.setVisibility(View.INVISIBLE);
            coin.setVisibility(View.INVISIBLE);
            coinCount.setVisibility(View.INVISIBLE);
            imageIndex = 0;
        }
    }

    public void onButtonReRoll(View view) {
        count = count - 2;
        coinCount.setText(count+"");
        int rand = getRandom(12,0 );
        shop1.setImageResource(images[rand]);
        rand = getRandom(12, 0);
        shop2.setImageResource(images[rand]);
        rand = getRandom(12, 0);
        shop3.setImageResource(images[rand]);
        rand = getRandom(12, 0);
        shop4.setImageResource(images[rand]);
        rand = getRandom(12, 0);
        shop5.setImageResource(images[rand]);
    }
}