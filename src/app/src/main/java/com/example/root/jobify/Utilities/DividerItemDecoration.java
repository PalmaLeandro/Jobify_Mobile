package com.example.root.jobify.Utilities;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dani on 17/09/16.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private static final int firstChild = 0;

    private int space;
    private int orientation;

    public DividerItemDecoration(int space, int orientation) {
        this.space = space;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (orientation == DividerItemDecoration.HORIZONTAL_LIST) {
            if (parent.getChildAdapterPosition(view) != DividerItemDecoration.firstChild)
                outRect.left = space;
        } else {
            if (parent.getChildAdapterPosition(view) != DividerItemDecoration.firstChild)
                outRect.top = space;
        }
        view.setBackgroundResource(android.R.color.transparent);
    }

}
