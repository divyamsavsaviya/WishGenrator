package com.aimulate.wishgenrator.adapters;

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
import com.aimulate.wishgenrator.data.Filter;
import com.aimulate.wishgenrator.data.Wish;

public class WishAdapter extends ListAdapter<Wish,WishAdapter.WishViewHolder> {

    private static final DiffUtil.ItemCallback<Wish> diffCallback = new DiffUtil.ItemCallback<Wish>() {
        @Override
        public boolean areItemsTheSame(@NonNull Wish oldItem, @NonNull Wish newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Wish oldItem, @NonNull Wish newItem) {
            return false;
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(String wish);
    }

    private OnItemClickListener clickListener;
    public WishAdapter(WishAdapter.OnItemClickListener clickListener) {
        super(diffCallback);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public WishAdapter.WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wish_item,parent,false);
        return new WishAdapter.WishViewHolder(view , clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WishAdapter.WishViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class WishViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWish;
        private Wish wish;
        public WishViewHolder(@NonNull View itemView,OnItemClickListener clickListener) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                clickListener.onItemClicked(wish.getWish());
            });
            textViewWish = itemView.findViewById(R.id.textViewWish);
        }

        public void bindTo(Wish wish) {
            this.wish = wish;
            textViewWish.setText(wish.getWish());
        }
    }
}
