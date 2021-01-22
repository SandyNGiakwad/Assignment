package com.sng.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sng.assignment.R;
import com.sng.assignment.mClickListener;
import com.sng.assignment.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<Item> items;
    Context context;
    mClickListener mclickListener;

    public ListAdapter(Context context, List<Item> items, mClickListener mclickListener) {
        this.context = context;
        this.items = items;
        this.mclickListener = mclickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tv_label.setText(item.getLable());
        Picasso.get().load(item.getImg_path()).into(holder.iv_item);
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclickListener.onClick(position);
            }
        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item, iv_edit,iv_delete;
        TextView tv_label;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_edit = itemView.findViewById(R.id.iv_edit);
            iv_item = itemView.findViewById(R.id.iv_item);
            tv_label = itemView.findViewById(R.id.tv_label);
            iv_delete=itemView.findViewById(R.id.iv_delete);

        }
    }

    public void DeleteItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    public void AddItem(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }
    public void updateItem(int position,Item item)
    {
        items.set(position,item);
        notifyDataSetChanged();
    }

}
