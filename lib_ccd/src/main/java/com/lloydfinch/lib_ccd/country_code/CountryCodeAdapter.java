package com.lloydfinch.lib_ccd.country_code;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lloydfinch.lib_ccd.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Name: CountryCodeAdapter
 * Author: lloydfinch
 * Function: CountryCodeAdapter
 * Date: 2020-06-12 13:39
 * Modify: lloydfinch 2020-06-12 13:39
 */
public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.CountryCodeVH> {

    private List<CountryCode.CodeInfo> countryCodes;
    private OnCountryCodeClick onCountryCodeClick;

    CountryCodeAdapter(List<CountryCode.CodeInfo> countryCodes) {
        this.countryCodes = countryCodes;
    }

    void setOnCountryCodeClick(OnCountryCodeClick onCountryCodeClick) {
        this.onCountryCodeClick = onCountryCodeClick;
    }

    @NonNull
    @Override
    public CountryCodeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_country_code, parent, false);
        return new CountryCodeVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryCodeVH holder, int position) {
        CountryCode.CodeInfo code = countryCodes.get(position);
        if (code.isCap()) {
            holder.tvCode.setTextColor(ContextCompat.getColor(holder.tvCode.getContext(), R.color.color_reg));
            holder.tvCode.setText(code.getCaps());
            holder.placeHolder.setVisibility(View.GONE);
            holder.itemView.setBackgroundResource(R.color.color_selected_country_code);
            holder.itemView.setClickable(false);
        } else {
            holder.tvCode.setTextColor(ContextCompat.getColor(holder.tvCode.getContext(), R.color.color_base_txt));
            String content = code.getDes();
            holder.tvCode.setText(content);
            holder.placeHolder.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundResource(R.drawable.selector_country_bg);
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(v -> {
                if (this.onCountryCodeClick != null) {
                    onCountryCodeClick.onItemClick(code);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return countryCodes.size();
    }

    void refresh(List<CountryCode.CodeInfo> codeInfos) {
        this.countryCodes = codeInfos;
        notifyDataSetChanged();
    }

    static class CountryCodeVH extends RecyclerView.ViewHolder {

        TextView tvCode;
        View placeHolder;

        CountryCodeVH(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            placeHolder = itemView.findViewById(R.id.place_holder);
        }
    }

    public interface OnCountryCodeClick {
        void onItemClick(CountryCode.CodeInfo codeInfo);
    }
}
