package com.example.skyforlife.projectwithactivity.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class NavigateHelper implements View.OnClickListener {
    private Context from = null;
    private Class to = null;

    public NavigateHelper(Context from, Class to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(from, to);
        from.startActivity(intent);
    }
}
