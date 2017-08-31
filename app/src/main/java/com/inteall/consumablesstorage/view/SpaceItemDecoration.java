package com.inteall.consumablesstorage.view;

import android.graphics.Rect;
import android.support.v4.widget.Space;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lyz on 2017/8/31.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;

    public SpaceItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mSpace;


    }
}
