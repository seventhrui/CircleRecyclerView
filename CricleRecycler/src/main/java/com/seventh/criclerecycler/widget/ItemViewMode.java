package com.seventh.criclerecycler.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 */
public interface ItemViewMode {
    void applyToView(View v, RecyclerView parent);
}
