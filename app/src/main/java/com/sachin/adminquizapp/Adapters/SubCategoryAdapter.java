package com.sachin.adminquizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sachin.adminquizapp.Models.CategoryModel;
import com.sachin.adminquizapp.Models.SubCategoryModel;
import com.sachin.adminquizapp.R;
import com.sachin.adminquizapp.databinding.RvCategoryDesignBinding;
import com.sachin.adminquizapp.databinding.RvSubcategoryDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.viewHolder> {

    Context context;
    ArrayList<SubCategoryModel>list;

    public SubCategoryAdapter(Context context, ArrayList<SubCategoryModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_subcategory_design,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SubCategoryModel categoryModel = list.get(position);

        holder.binding.subCategoryName.setText(categoryModel.getCategoryName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RvSubcategoryDesignBinding binding;
        public viewHolder(@NonNull View itemView){
            super(itemView);

            binding = RvSubcategoryDesignBinding.bind(itemView);
        }
    }
}
