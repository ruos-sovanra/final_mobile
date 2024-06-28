package com.ruossovanra.ecommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ruossovanra.ecommerce.Domain.ItemsDomain;
import com.ruossovanra.ecommerce.Helper.ChangeNumberItemsListener;
import com.ruossovanra.ecommerce.Helper.ManagmentCart;
import com.ruossovanra.ecommerce.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    ArrayList<ItemsDomain> listSelectedItems;
    ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart managmentCart;

    public CartAdapter(ArrayList<ItemsDomain> listSelectedItems, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listSelectedItems = listSelectedItems;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.binding.titleTxt.setText(listSelectedItems.get(position).getTitle());
        holder.binding.feeEachItem.setText("$"+listSelectedItems.get(position).getPrice());
        holder.binding.totalEachItem.setText("$"+Math.round((listSelectedItems.get(position).getNumberinCart() * listSelectedItems.get(position).getPrice())));
        holder.binding.numberItemTxt.setText(String.valueOf(listSelectedItems.get(position).getNumberinCart()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop());

        Glide.with(holder.itemView)
                .load(listSelectedItems.get(position).getPicUrl().get(0))
                .apply(requestOptions)
                .into(holder.binding.pic);


        holder.binding.plusCartBtn.setOnClickListener(v -> managmentCart.plusItem(listSelectedItems, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.binding.minusCartBtn.setOnClickListener(v -> managmentCart.minusItem(listSelectedItems, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return listSelectedItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderCartBinding binding;
        public ViewHolder(ViewholderCartBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
