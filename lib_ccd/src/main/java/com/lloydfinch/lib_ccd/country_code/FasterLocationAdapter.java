package com.lloydfinch.lib_ccd.country_code;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lloydfinch.lib_ccd.R;
import com.lloydfinch.lib_ccd.lib_tools.view.LTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Name: FasterLocationAdapter
 * Author: lloydfinch
 * Function: FasterLocationAdapter
 * Date: 2020-06-12 11:24
 * Modify: lloydfinch 2020-06-12 11:24
 */
public class FasterLocationAdapter extends RecyclerView.Adapter<FasterLocationAdapter.FasterLocationVH> {

    private List<String> datas;
    private OnItemClick onItemClick;

    public FasterLocationAdapter(List<String> datas) {
        this.datas = datas;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public FasterLocationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_faster_po, parent, false);
        return new FasterLocationVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FasterLocationVH holder, int position) {
        String cap = datas.get(position);
        holder.tvCap.setText(cap);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.onItemClick(position, cap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    static class FasterLocationVH extends RecyclerView.ViewHolder {

        LTextView tvCap;

        FasterLocationVH(@NonNull View itemView) {
            super(itemView);
            tvCap = itemView.findViewById(R.id.tv_cap);
        }
    }

    public interface OnItemClick {
        void onItemClick(int position, String data);
    }
}
