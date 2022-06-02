package com.example.pokemondefence.scenes;

import android.graphics.Canvas;

import com.example.pokemondefence.framework.interfaces.GameObject;

import java.util.Random;


public class FlyGen implements GameObject {
    private static final float GEN_INTERVAL = 2.0f;
    private static final float MIN_INTERVAL = 0.1f;
    private float interval;
    private float time;
    private float speed;
    private static Random random = new Random();

    public FlyGen() {
        speed = 2 * TiledSprite.unit;
        interval = GEN_INTERVAL;
    }

    @Override
    public void update(float frameTime) {
        time += frameTime;
        if (time > interval) {
            spawn();
            time -= interval;
            interval *= 0.995;
            if (interval < MIN_INTERVAL) interval = MIN_INTERVAL;
        }
    }

    private void spawn() {
        float size = (float) (random.nextDouble() * 0.3 + 0.7);
        float speed = (float) (this.speed * (random.nextDouble() * 0.2 + 0.9));
        Fly fly = Fly.get(Fly.Type.RANDOM, speed, size);
        MainScene.get().add(MainScene.Layer.enemy.ordinal(), fly);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
