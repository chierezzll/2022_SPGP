package com.example.pokemondefence.scenes;

import android.view.MotionEvent;

import com.example.pokemondefence.framework.game.Scene;
import com.example.pokemondefence.framework.interfaces.GameObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainScene extends Scene {
    public static final String PARAM_STAGE_INDEX = "stage_index";
    private static MainScene singleton;
    private TiledSprite tiledSprite;
    private HashMap<Integer, Cannon> cannons = new HashMap<>();

    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }

    public void setMapIndex(int stageIndex) {

    }

    public Fly findNearestFly(Cannon cannon) {
        float dist = Float.MAX_VALUE;
        Fly nearest = null;
        float cx = cannon.getX();
        float cy = cannon.getY();
        ArrayList<GameObject> flies = objectsAt(Layer.enemy.ordinal());
        for (GameObject gameObject: flies) {
            if (!(gameObject instanceof Fly)) continue;
            Fly fly = (Fly) gameObject;
            float fx = fly.getX();
            float fy = fly.getY();
            float dx = cx - fx;
            if (dx > dist) continue;
            float dy = cy - fy;
            if (dy > dist) continue;
            float d = (float) Math.sqrt(dx * dx + dy * dy);
            if (dist > d) {
                dist = d;
                nearest = fly;
            }
        }
        return nearest;
    }

    public enum Layer {
        tile, cannon, enemy, shell, controller, COUNT;
    }

    public void init() {
        super.init();

        initLayers(Layer.COUNT.ordinal());

        tiledSprite = new TiledSprite();
        tiledSprite.map.wraps = true;
        add(Layer.tile.ordinal(), tiledSprite);

        add(Layer.controller.ordinal(), new FlyGen());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        int x = (int) (event.getX() / TiledSprite.unit);
        int y = (int) (event.getY() / TiledSprite.unit);
        int tileIndex = tiledSprite.map.getTileAt(x, y);
        //Log.d("MainScene", "("+x+","+y+")"+tileIndex);
        if (tileIndex != TiledSprite.TILEINDEX_BRICK) {
            return false;
        }
        int key = x * 1000 + y;
        Cannon cannon = cannons.get(key);
        if (cannon == null) {
            cannon = new Cannon(1,
                    TiledSprite.unit * (x + 0.5f),
                    TiledSprite.unit * (y + 0.5f),
                    10, 4);
            cannons.put(key, cannon);
            add(Layer.cannon.ordinal(), cannon);
        } else {
            cannon.upgrade();
        }
        return true;
    }
}
