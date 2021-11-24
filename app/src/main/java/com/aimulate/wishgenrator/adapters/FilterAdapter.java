package com.aimulate.wishgenrator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aimulate.wishgenrator.R;
import com.aimulate.wishgenrator.data.Filter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class FilterAdapter extends ListAdapter<Filter, FilterAdapter.FilterViewHolder> {

    private static final DiffUtil.ItemCallback<Filter> diffCallback = new DiffUtil.ItemCallback<Filter>() {
        @Override
        public boolean areItemsTheSame(@NonNull Filter oldItem, @NonNull Filter newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Filter oldItem, @NonNull Filter newItem) {
            return false;
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(String filterId);
    }

    private OnItemClickListener clickListener;
    public FilterAdapter(OnItemClickListener clickListener) {
        super(diffCallback);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.filter_item,parent,false);
        return new FilterViewHolder(view , clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    static class FilterViewHolder extends RecyclerView.ViewHolder {

        private TextView filterHead;
        private TextView filterDescription;
        private Filter filter;

        public FilterViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                clickListener.onItemClicked(filter.getId());
            });
            filterHead = itemView.findViewById(R.id.filterHead);
            filterDescription = itemView.findViewById(R.id.filterDescription);
        }

        void bindTo(Filter filter) {
            this.filter = filter;
            filterHead.setText(filter.getHead());
            filterDescription.setText(filter.getDescription());

        }
    }
}
