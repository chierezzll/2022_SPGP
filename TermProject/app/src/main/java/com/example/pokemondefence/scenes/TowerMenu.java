package com.example.pokemondefence.scenes;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.pokemondefence.R;
import com.example.pokemondefence.framework.interfaces.Touchable;
import com.example.pokemondefence.framework.objects.Sprite;
import com.example.pokemondefence.framework.res.BitmapPool;
import com.example.pokemondefence.framework.res.Metrics;


public class TowerMenu extends Sprite implements Touchable {
    public interface Listener {
        public void onMenuSelected(int menuMipmapResId);
    }
    private Listener listener;
    private int[] items;
    private RectF itemRect = new RectF();
    private Paint alphaPaint = new Paint();

    public TowerMenu(Listener listener) {
        this.listener = listener;
        bitmap = BitmapPool.get(R.mipmap.menu_bg);
        items = new int[] {};
    }
    public void setMenu(float leftUnit, float topUnit, int... items) {
        float left = (leftUnit + 1) * TiledSprite.unit;
        float top = topUnit * TiledSprite.unit;
        this.items = items;
        dstRect.set(left, top, left + items.length * TiledSprite.unit * 2, top + TiledSprite.unit * 2);
        if (dstRect.right > Metrics.width) {
            dstRect.offset(-(items.length + 0.5f) * TiledSprite.unit * 2, 0);
        }
        if (dstRect.bottom > Metrics.height) {
            dstRect.offset(0, -TiledSprite.unit);
        }
        ValueAnimator animator = ValueAnimator
                .ofInt(0, 192)
                .setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                alphaPaint.setAlpha((Integer)valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.items.length == 0) return;
//        super.draw(canvas);
        canvas.drawBitmap(bitmap, null, dstRect, alphaPaint);
        itemRect.set(dstRect);
        itemRect.right = itemRect.left + TiledSprite.unit * 2;
        for (int item: items) {
            Bitmap bitmap = BitmapPool.get(item);
            canvas.drawBitmap(bitmap, null, itemRect, alphaPaint);
            itemRect.offset(TiledSprite.unit * 2, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (this.items.length == 0) return false;
        float x = e.getX();
        float y = e.getY();
        if (!dstRect.contains(x, y)) {
            return false;
        }
        itemRect.set(dstRect);
        itemRect.right = itemRect.left + TiledSprite.unit * 2;
        int foundItem = 0;
        for (int item: items) {
            if (itemRect.contains(x, y)) {
                foundItem = item;
                break;
            }
            itemRect.offset(TiledSprite.unit * 2, 0);
        }
        if (foundItem != 0 && listener != null) {
            listener.onMenuSelected(foundItem);
        }
        return true;
    }
}
