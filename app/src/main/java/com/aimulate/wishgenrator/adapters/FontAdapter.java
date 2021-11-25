package com.aimulate.wishgenrator.adapters;

import android.graphics.fonts.Font;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aimulate.wishgenrator.R;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class FontAdapter extends ListAdapter<String, FontAdapter.FontViewHolder> {

    private static final DiffUtil.ItemCallback<String> diffCallback = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(String fancyWish);
    }

    private OnItemClickListener clickListener;
    public FontAdapter(FontAdapter.OnItemClickListener clickListener) {
        super(diffCallback);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FontAdapter.FontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wish_item,parent,false);
        return new FontViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FontAdapter.FontViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class FontViewHolder extends RecyclerView.ViewHolder {
        String wish;
        private TextView textViewWish;

        public FontViewHolder(@NonNull View itemView, FontAdapter.OnItemClickListener clickListener) {
            super(itemView);
            textViewWish = itemView.findViewById(R.id.textViewWish);
            itemView.setOnClickListener(v -> {
                clickListener.onItemClicked(wish);
            });
        }

        public void bindTo(String wish) {
            this.wish = wish;
            textViewWish.setText(wish);
        }
    }
}
