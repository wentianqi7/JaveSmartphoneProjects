package com.example.skyforlife.projectwithactivity.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.example.skyforlife.projectwithactivity.ui.activities.PlayActivity;

/**
 * Created by STuotuo.Wen on 2015/11/6.
 */
public class GestureHandler implements View.OnTouchListener {
    static final int MIN_DISTANCE = 100;
    private Activity activity;
    private float startX, startY, endX, endY;
    private Class dest = null;

    public GestureHandler(Activity activity, Class dest) {
        this.activity = activity;
        this.dest = dest;
    }

    /**
     * from play music view to other activities
     */
    public void toLeftSwipe() {
        if (activity instanceof PlayActivity) {
            Intent intent = new Intent();
            intent.setClass(activity, dest);
            activity.startActivity(intent);
        }
    }

    /**
     * from activities to play music view
     */
    public void toRightSwipe() {
        if (activity instanceof PlayActivity) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(activity, dest);
        activity.startActivity(intent);
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
