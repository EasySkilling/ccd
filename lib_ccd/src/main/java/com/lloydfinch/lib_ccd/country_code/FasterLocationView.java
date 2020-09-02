package com.lloydfinch.lib_ccd.country_code;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Name: FasterLocationView
 * Author: lloydfinch
 * Function: FasterLocationView
 * Date: 2020-06-12 11:16
 * Modify: lloydfinch 2020-06-12 11:16
 */
public class FasterLocationView extends LinearLayoutCompat {

    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private FasterLocationAdapter adapter = new FasterLocationAdapter(datas);

    public FasterLocationView(Context context) {
        this(context, null);
    }

    public FasterLocationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FasterLocationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(recyclerView, layoutParams);
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        adapter.refresh(datas);
    }

    public void setOnItemClick(FasterLocationAdapter.OnItemClick onItemClick) {
        adapter.setOnItemClick(onItemClick);
    }
}
