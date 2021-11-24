package com.aimulate.wishgenrator.adapters;

import android.graphics.fonts.Font;
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

    public String wish;

    protected FontAdapter(String wish) {
        super(diffCallback);
        this.wish = wish;
    }

    @NonNull
    @Override
    public FontAdapter.FontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wish_item,parent,false);
        return new FontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FontAdapter.FontViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class FontViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewWish;

        public FontViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWish = itemView.findViewById(R.id.textViewWish);
        }


        public void bindTo(String font) {
        }

        public String generateText(String wish,String font){
            wish.toLowerCase(Locale.ROOT);
            char[] arrayWish = wish.toCharArray();
            char[] fontArray = font.toCharArray();

            for(int i = 0 ; i < arrayWish.length ; i++) {
                if(arrayWish[i] != ' '){
                    int index = getIndexOfAlphabet(arrayWish[i]);
                    arrayWish[i] = fontArray[index];
                }
            }
            return arrayWish.toString();
        }

        private int getIndexOfAlphabet(char c) {
            int tamp = c;
            return tamp - 96;
        }
    }
}
