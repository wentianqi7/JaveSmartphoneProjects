package tianqiw.vocalartistdisplayer.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * dealing with gestures to shift between activities
 */
public class GestureHandler implements View.OnTouchListener {
    static final int MIN_DISTANCE = 100;
    private Activity activity;
    private float startX, startY, endX, endY;
    private Class next = null;
    private Class prev = null;

    public GestureHandler(Activity activity, Class prev, Class next) {
        this.activity = activity;
        this.next = next;
        this.prev = prev;
    }

    public void toRightSwipe() {
        if (next != null) {
            Intent intent = new Intent();
            intent.setClass(activity, next);
            activity.startActivity(intent);
        }
    }

    public void toLeftSwipe() {
        if (prev != null) {
            Intent intent = new Intent();
            intent.setClass(activity, prev);
            activity.startActivity(intent);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                startY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                endX = event.getX();
                endY = event.getY();
                float deltaX = startX - endX;
                float deltaY = startY - endY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        // rght to left
                        if (deltaX > 0) {
                            this.toLeftSwipe();
                            return true;
                        }
                        // left to right
                        if (deltaX < 0) {
                            this.toRightSwipe();
                            return true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
