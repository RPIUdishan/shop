package com.example.shop.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView> {


    List<String> categorySet;
    Context context;
    TextView categoryText;

    public CategoryAdapter(List<String>  dataSet) {
        this.categorySet = dataSet;

    }

     class CategoryView extends RecyclerView.ViewHolder{

        public CategoryView(@NonNull View itemView) {
            super(itemView);

            categoryText = (TextView) itemView.findViewById(R.id.categoryTextView);
        }

         public TextView getTextView() {
             return categoryText;
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
    }

    @Override
    public int getItemCount() {
        return  categorySet.size();

    }


}
