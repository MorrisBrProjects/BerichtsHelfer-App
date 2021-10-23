package com.example.berichtshelfer.listener;

import android.view.View;
import android.widget.AdapterView;

public abstract class DoubleClickListener implements AdapterView.OnItemClickListener {

    private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

    long lastClickTime = 0;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
            onDoubleClick(adapterView, view, position, l);
        } else {
            onSingleClick(adapterView, view, position, l);
        }
        lastClickTime = clickTime;
    }

    public abstract void onSingleClick(AdapterView<?> adapterView, View view, int position, long l);
    public abstract void onDoubleClick(AdapterView<?> adapterView, View view, int position, long l);
}
