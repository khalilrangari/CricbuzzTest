package com.android.khalilcricbuzztest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.khalilcricbuzztest.R;
import com.android.khalilcricbuzztest.model.Restaurant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is a adapter class which is use to display multiple data in single view
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViveHolder> {
  private List<Restaurant> restaurants;
  private Context mContext;

  public RestaurantAdapter(List<Restaurant> restaurants) {
    this.restaurants = restaurants;
  }

  @Override
  public MyViveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    this.mContext = parent.getContext();
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
    return new MyViveHolder(view);
  }

  @Override
  public void onBindViewHolder(MyViveHolder holder, int position) {
    holder.tvRestaurantName.setText(restaurants.get(position).getName());
    holder.tvRestaurantAddress.setText(restaurants.get(position).getAddress());
    holder.tvRestaurantCuisineType.setText(restaurants.get(position).getCuisineType());
    Glide.with(mContext)
        .load(restaurants.get(position).getPhotograph())
        .into(holder.ivFood);
  }

  @Override
  public int getItemCount() {
    return restaurants != null && restaurants.size() > 0 ? restaurants.size() : 0;
  }

  public static class MyViveHolder extends RecyclerView.ViewHolder {
    ImageView ivFood;
    TextView tvRestaurantName, tvRestaurantAddress, tvRestaurantCuisineType;
    public MyViveHolder(View itemView) {
      super(itemView);
      ivFood = itemView.findViewById(R.id.ivFood);
      tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
      tvRestaurantAddress = itemView.findViewById(R.id.tvRestaurantAddress);
      tvRestaurantCuisineType = itemView.findViewById(R.id.tvRestaurantCuisineType);
    }
  }
}
