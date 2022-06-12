package com.example.pokemondefence.scenes;

import android.view.MotionEvent;

import com.example.pokemondefence.R;
import com.example.pokemondefence.framework.game.Scene;
import com.example.pokemondefence.framework.interfaces.GameObject;
import com.example.pokemondefence.framework.objects.Score;
import com.example.pokemondefence.framework.res.Metrics;
import com.example.pokemondefence.framework.res.Sound;

import java.util.ArrayList;


public class MainScene extends Scene implements TowerMenu.Listener {
    public static final String PARAM_STAGE_INDEX = "stage_index";
    private static MainScene singleton;
    private TiledSprite tiledSprite;
    private Selector selector;
    private TowerMenu towerMenu;
    public Score score;
    public Score life;
    public Score wave;


    int[] images = new int[] {
            R.mipmap.p_01_01,R.mipmap.p_01_02,R.mipmap.p_01_03,R.mipmap.p_02_01,R.mipmap.p_02_02,
            R.mipmap.p_03_01,R.mipmap.p_03_02,R.mipmap.p_04_01,R.mipmap.p_05_01,R.mipmap.p_06_01,
            R.mipmap.p_06_02,R.mipmap.p_07_01,R.drawable.re_roll};


    int getRandom(int range, int min) {
        return (int)(Math.random() * range) + min;
    }

    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }

    public int rand = getRandom(11,0 );
    public int rand2 = getRandom(11,0 );
    public int rand3 = getRandom(11,0 );
    public int rand4 = getRandom(11,0 );
    public int rand5 = getRandom(11,0 );

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
        tile, cannon, enemy, shell, explosion, score, selection, controller, COUNT;
    }

    public float size(float unit) {
        return Metrics.height / 9.5f * unit;
    }

    public void init() {
        super.init();


        initLayers(Layer.COUNT.ordinal());

        tiledSprite = new TiledSprite();
        tiledSprite.map.wraps = true;
        add(Layer.tile.ordinal(), tiledSprite);

        add(Layer.controller.ordinal(), new FlyGen());

        selector = new Selector();
        selector.select(-1, -1);
        add(Layer.selection.ordinal(), selector);

        towerMenu = new TowerMenu(this);
        add(Layer.selection.ordinal(), towerMenu);

        score = new Score(R.mipmap.gold_number,
                TiledSprite.unit / 2.0f,TiledSprite.unit / 2.0f,
                TiledSprite.unit * 1.2f);
        score.set(100);
        add(Layer.score.ordinal(), score);

        life = new Score(R.mipmap.gold_number,
                TiledSprite.unit / 2.0f,TiledSprite.unit * 15.0f,
                TiledSprite.unit * 1.2f);
        life.set(20);
        add(Layer.score.ordinal(), life);

        wave = new Score(R.mipmap.gold_number,
                TiledSprite.unit / 2.0f,TiledSprite.unit * 30.5f,
                TiledSprite.unit * 1.2f);
        wave.set(1);
        add(Layer.score.ordinal(), wave);



    }
    public static int getLife (){return 20;}


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Sound.playEffect(R.raw.touch);
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        if (towerMenu.onTouchEvent(event)) {
            return true;
        }
        int x = (int) (event.getX() / TiledSprite.unit);
        int y = (int) (event.getY() / TiledSprite.unit);
        int tileIndex = tiledSprite.map.getTileAt(x, y);
        //Log.d("MainScene", "("+x+","+y+")"+tileIndex);
        if (tileIndex != TiledSprite.TILEINDEX_BRICK) {
            selector.select(-1, -1);
            towerMenu.setMenu(-1, -1);
            return false;
        }
        Cannon cannon = selector.select(x, y);
        if (cannon != null) {
            towerMenu.setMenu(x, y,
                    R.mipmap.uninstall,
                    R.mipmap.upgrade);
        } else {
            towerMenu.setMenu(x, y,
                    images[rand],
                    images[rand2],
                    images[rand3],
                    images[rand4],
                    images[rand5],
                    images[12]
                    );
        }
        return true;
    }

    @Override
    public void onMenuSelected(int menuMipmapResId) {


        Cannon cannon = selector.getCannon();

        if (cannon != null) {
            switch (menuMipmapResId) {
                case R.mipmap.upgrade:
                    int cost = cannon.getUpgradeCost();
                    if (score.get() < cost) {
                        return;
                    }
                    score.add(-cost);
                    cannon.upgrade();
                    break;
                case R.mipmap.uninstall:
                    selector.remove();
                    int price = cannon.getSellPrice();
                    score.add(price);
                    remove(cannon);
                    break;
            }
            selector.select(-1, -1);
            towerMenu.setMenu(-1, -1);
            return;
        }
        int level = 0;
        int num = 0;
        switch (menuMipmapResId) {
            case R.mipmap.p_01_01:
                level = 1;
                num = 1;
                break;
            case R.mipmap.p_01_02:
                level = 1;
                num = 2;
                break;
            case R.mipmap.p_01_03:
                level = 1;
                num = 3;
                break;
            case R.mipmap.p_02_01:
                level = 1;
                num = 4;
                break;
            case R.mipmap.p_02_02:
                level = 1;
                num = 5;
                break;
            case R.mipmap.p_03_01:
                level = 1;
                num = 6;
                break;
            case R.mipmap.p_03_02:
                level = 1;
                num = 7;
                break;
            case R.mipmap.p_04_01:
                level = 1;
                num = 8;
                break;
            case R.mipmap.p_05_01:
                level = 1;
                num = 9;
                break;
            case R.mipmap.p_06_01:
                level = 1;
                num = 10;
                break;
            case R.mipmap.p_06_02:
                level = 1;
                num = 11;
                break;
            case R.mipmap.p_07_01:
                level = 1;
                num = 12;
                break;

            case R.drawable.re_roll:
                rand = getRandom(11,0 );
                rand2 = getRandom(11,0 );
                rand3 = getRandom(11,0 );
                rand4 = getRandom(11,0 );
                rand5 = getRandom(11,0 );

                score.add(-20);

                selector.select(-1, -1);
                towerMenu.setMenu(-1, -1);

                return;
            default:
                return;
        }
        int cost = Cannon.getInstallCost(level);
        if (score.get() < cost) {
            return;
        }
        score.add(-cost);
        cannon = new Cannon(level, num, selector.getX(), selector.getY(), 10, 2);
        selector.install(cannon);
        add(Layer.cannon.ordinal(), cannon);
        selector.select(-1, -1);
        towerMenu.setMenu(-1, -1);

        rand = getRandom(11,0 );
        rand2 = getRandom(11,0 );
        rand3 = getRandom(11,0 );
        rand4 = getRandom(11,0 );
        rand5 = getRandom(11,0 );



    }

    @Override
    public void start() {
        Sound.playMusic(R.raw.main);
    }

    @Override
    public void pause() {
        Sound.pauseMusic();
    }

    @Override
    public void resume() {
        Sound.resumeMusic();
    }

    @Override
    public void end() {
        Sound.stopMusic();
    }
}