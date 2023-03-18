package com.example.shop.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView> {


    List<String> categorySet;
    Context context;
    TextView categoryText;
    int selectedPosition = 0;

    public CategoryAdapter(List<String> dataSet, Context context) {
        this.categorySet = dataSet;
        this.context = context;
    }

    class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CategoryView(@NonNull View itemView) {
            super(itemView);

            categoryText = (TextView) itemView.findViewById(R.id.categoryTextView);
            itemView.setOnClickListener(this); // set click listener on itemView
        }

        public TextView getTextView() {
            return categoryText;
        }

        @Override
        public void onClick(View v) {
            selectedPosition = getAdapterPosition(); // update selected position
            Log.d("1111111111", String.valueOf(selectedPosition));
            notifyDataSetChanged(); // notify adapter of data change
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryView onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);

        return new CategoryView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        holder.getTextView().setText(categorySet.get(position));
        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.selected_item_bg);
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.category_normal_background));
        }
    }


    @Override
    public int getItemCount() {
        return categorySet.size();
    }
}