package com.example.pokemondefence.scenes;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;

import com.example.pokemondefence.R;
import com.example.pokemondefence.app.GameActivity;
import com.example.pokemondefence.app.MainActivity;
import com.example.pokemondefence.framework.game.RecycleBin;
import com.example.pokemondefence.framework.interfaces.Recyclable;
import com.example.pokemondefence.framework.objects.Score;
import com.example.pokemondefence.framework.objects.SheetSprite;
import com.example.pokemondefence.framework.interfaces.GameObject;
import com.example.pokemondefence.framework.objects.Sprite;
import com.example.pokemondefence.framework.res.Metrics;
import com.example.pokemondefence.framework.util.Gauge;

import com.example.pokemondefence.framework.game.Scene;
import com.example.pokemondefence.framework.view.GameView;

import java.util.Random;


public class Fly extends Sprite implements Recyclable {

    private Type type;
    private float dist;
    private float speed;
    private float angle;
    private float dx, dy;
    private float health, maxHealth;
    private Gauge gauge;
    public Score life;

    private static Random random = new Random();
    //private static Path path;
    private static PathMeasure pathMeasure;
    private static int cnt = 0;

    public static void setPath(Path path) {
        //Fly.path = path;
        Fly.pathMeasure = new PathMeasure(path, false);
    }

    public boolean decreaseHealth(float power) {
        health -= power;
        return health < 0;
    }

    public int score() {
        return Math.round(maxHealth / 10) * 10;
    }

    public enum Type {
        boss, red, blue, cyan, dragon, COUNT, RANDOM;
//        float getMaxHealth() {
//            return HEALTHS[ordinal()];
//        }
        static float[] HEALTHS = { 150, 50, 30, 20, 10 };
    }

    public static Fly get(Type type, float speed, float size) {
        Fly fly = (Fly) RecycleBin.get(Fly.class);
        if (fly == null) {
            fly = new Fly();
        }
        fly.init(type, speed, size);
        return fly;
    }

    private Fly() {
        super(0, 0, TiledSprite.unit, TiledSprite.unit, R.mipmap.monster);
        if (rects_array == null) {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            rects_array = new Rect[Type.COUNT.ordinal()][];
            int x = 0;
            for (int i = 0; i < Type.COUNT.ordinal(); i++) {
                rects_array[i] = new Rect[2];
                for (int j = 0; j < 2; j++) {
                    rects_array[i][j] = new Rect(x, 0, x+h, h);
                    x += h;
                }
            }
        }

        gauge = new Gauge(
                Metrics.size(R.dimen.fly_gauge_thickness_fg),
                R.color.fly_gauge_fg,
                Metrics.size(R.dimen.fly_gauge_thickness_bg),
                R.color.fly_gauge_bg,
                TiledSprite.unit
        );
    }

    private Rect[][] rects_array;
    private void init(Type type, float speed, float size) {
//        if (type == Type.RANDOM) {
//            int index = random.nextInt(Type.COUNT.ordinal() - 1) + 1;
//            type = Type.values()[index];
//        }

        this.type = type;
        //srcRects = rects_array[type.ordinal()];
        this.speed = speed;

        radius = TiledSprite.unit * size;
        dist = 0;
        dx = dy = 0;
        //health = maxHealth = type.getMaxHealth() * size;
        MainScene scene = MainScene.get();
        health = maxHealth = 50 * scene.wave.get();
    }


    private float[] pos = new float[2];
    private float[] tan = new float[2];
    @Override
    public void update(float frameTime) {
        dist += speed * frameTime;
        if (dist > pathMeasure.getLength()) {
            MainScene scene = MainScene.get();
            MainScene.get().remove(this);
            scene.life.add(-1);
            cnt = cnt + 1;
            if (cnt > 20) {
                GameView.view.getActivity().finish();

            }
            return;
        }

        dx += (2 * radius * random.nextFloat() - radius) * frameTime;
        if (dx < -radius) dx = -radius;
        else if (dx > radius) dx = radius;
        dy += (2 * radius * random.nextFloat() - radius) * frameTime;
        if (dy < -radius) dy = -radius;
        else if (dy > radius) dy = radius;

        pathMeasure.getPosTan(dist, pos, tan);
        x = pos[0] + dx;
        y = pos[1] + dy;
        angle = (float)(Math.atan2(tan[1], tan[0]) * 180 / Math.PI) ;
        setDstRectWithRadius();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        super.draw(canvas);
        canvas.restore();
        gauge.setValue(health / maxHealth);
        gauge.draw(canvas, x, y + radius);

        life = new Score(R.mipmap.gold_number,
                TiledSprite.unit / 2.0f,TiledSprite.unit * 15.0f,
                TiledSprite.unit * 1.2f);
        life.set(20);

    }

    @Override
    public void finish() {
    }
}
