package com.amber.random.datapoliceukv2.ui.adapters;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.amber.random.datapoliceukv2.R;

public abstract class BaseController
        extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final View.OnTouchListener sOnTouch = new View.OnTouchListener() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.findViewById(R.id.row_content).getBackground()
                    .setHotspot(event.getX(), event.getY());
            return false;
        }
    };

    public BaseController(View itemView) {
        super(itemView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            itemView.setOnTouchListener(sOnTouch);
    }

    public abstract void onClick(View v);
}
