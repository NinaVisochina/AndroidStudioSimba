package com.example.simba.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simba.R;
import com.example.simba.category.OnItemClickListener;
import com.example.simba.constants.Urls;
import com.example.simba.DTO.CategoryItemDTO;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {
    private List<CategoryItemDTO> items;
    private final OnItemClickListener editCategory;
    private final OnItemClickListener deleteCategory;

    public CategoriesAdapter(List<CategoryItemDTO> items, OnItemClickListener editCategory,
                             OnItemClickListener deleteCategory) {
        this.items = items;
        this.editCategory = editCategory;
        this.deleteCategory = deleteCategory;
    }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.simba.adapters.CategoryCardViewHolder holder, int position) {
        if(items!=null && position<items.size()) {
            CategoryItemDTO item = items.get(position);
            holder.getCategoryName().setText(item.getName());
            String url = Urls.BASE+item.getImagePath();

            Glide.with(holder.itemView.getContext())
                    .load(url)
                    //.apply(new RequestOptions().override(400))
                    .into(holder.getIvCategoryImage());

           holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editCategory.onItemClick(item);
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCategory.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}