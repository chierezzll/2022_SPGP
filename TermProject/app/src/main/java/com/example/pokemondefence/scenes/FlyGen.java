package com.example.pokemondefence.scenes;

import android.graphics.Canvas;

import com.example.pokemondefence.framework.interfaces.GameObject;

import java.util.Random;


public class FlyGen implements GameObject {
    private static final float GEN_INTERVAL = 3.0f;
    private static final float MIN_INTERVAL = 0.1f;
    private static final float WAVE_INTERVAL = 30.0f;
    private float time, interval;
    private float waveTime, waveInteral;
    private float speed;
    private static Random random = new Random();
    private int wave;
    private boolean normalPhase;

    public FlyGen() {
        speed = 2 * TiledSprite.unit;
        interval = GEN_INTERVAL;
        waveInteral = WAVE_INTERVAL;
        wave = 1;
        normalPhase = true;
    }

    @Override
    public void update(float frameTime) {
        waveTime += frameTime;
        if (normalPhase) {
            time += frameTime;
            if (time > interval) {
                spawn(false);
                time -= interval;
                interval *= 0.995;
                if (interval < MIN_INTERVAL) interval = MIN_INTERVAL;
            }
            if (waveTime > waveInteral) {
                spawn(false);
                waveTime = 0;
                normalPhase = false;
            }
            if (wave == 5 || wave == 10 || wave == 15 || wave == 20 ) {
                spawn(true);
                waveTime = 0;
                normalPhase = false;
            }
        } else {
            final int layerIndex = MainScene.Layer.enemy.ordinal();
            if (waveTime > waveInteral || MainScene.get().objectsAt(layerIndex).size() == 0) {
                waveTime = 0;
                normalPhase = true;
                MainScene scene = MainScene.get();
                wave += 1;
                scene.wave.add(1);
            }
        }
    }

    private void spawn(boolean boss) {
        float size = (float) (0.2 * 0.3 + 0.7);
        float speed = (float) (this.speed * (1 * 0.2 + 0.9));
        Fly.Type type = Fly.Type.boss;
        if (boss) {
            size *= 1.5;
        } else {
            type = Fly.Type.red;
        }
        Fly fly = Fly.get(type, speed, size);
        MainScene.get().add(MainScene.Layer.enemy.ordinal(), fly);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}