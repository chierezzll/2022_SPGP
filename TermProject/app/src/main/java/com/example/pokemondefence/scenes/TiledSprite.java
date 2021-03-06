package com.example.pokemondefence.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.pokemondefence.framework.map.MapLoader;
import com.example.pokemondefence.framework.map.TiledMap;
import com.example.pokemondefence.framework.objects.Sprite;
import com.example.pokemondefence.framework.res.Metrics;
import com.example.pokemondefence.framework.view.GameView;


public class TiledSprite extends Sprite {
    public static final int TILEINDEX_BRICK = 10;
    public static float unit;
    public final TiledMap map;
    private Paint paint;
    private static Path path;
    private Path transformedPath = new Path();
    private Matrix matrix = new Matrix();

    static {
        path = new Path();
        path.moveTo(-8, 428);
        path.cubicTo(94, 418, 91, 343, 91, 295);
        path.cubicTo(87, 223, 23, 226, 26, 152);
        path.cubicTo(28, 88, 76, 27, 163, 27);
        path.cubicTo(248, 25, 301, 99, 302, 168);
        path.cubicTo(301, 239, 202, 327, 314, 396);
        path.cubicTo(362, 432, 454, 436, 515, 397);
        path.cubicTo(617, 309, 500, 222, 501, 160);
        path.cubicTo(497, 95, 549, 15, 631, 15);
        path.cubicTo(720, 13, 771, 96, 773, 156);
        path.cubicTo(772, 230, 704, 261, 706, 310);
        path.cubicTo(702, 362, 722, 414, 807, 419);
    }
    public TiledSprite() {
        this.map = new MapLoader(GameView.view.getContext()).loadAsset("map", "desert.tmj");
        unit = Metrics.height / map.height;
        map.setDstTileSize(unit);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);

//        transformedPath = path;
        matrix.reset();
        float scale = map.getDstTileSize() / 25f;
        matrix.setScale(scale, scale);
        path.transform(matrix, transformedPath);

        Fly.setPath(transformedPath);
    }

    @Override
    public void draw(Canvas canvas) {
        map.draw(canvas);

        //canvas.drawPath(transformedPath, paint);
    }
}
