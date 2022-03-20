package com.android.khalilcricbuzztest.view;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.khalilcricbuzztest.adapter.RestaurantAdapter;
import com.android.khalilcricbuzztest.databinding.ActivityMainBinding;
import com.android.khalilcricbuzztest.model.Restaurant;
import com.android.khalilcricbuzztest.model.Restaurants;
import com.android.khalilcricbuzztest.viewmodel.RestaurantsViewModel;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding mBinding;
  private RestaurantsViewModel restaurantsViewModel;
  private LinearLayoutManager mLinearLayoutManager;
  private RestaurantAdapter restaurantAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(mBinding.getRoot());
    initializeViewModel();
    subScribeRestaurantData();
    mBinding.rvRestaurant.requestFocus();
    mBinding.etSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          restaurantsViewModel.searchResult(charSequence.toString());
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  /**
   * subScribeRestaurantData this method id use for observe data and get updated data from view model class
   */
  private void subScribeRestaurantData() {
    restaurantsViewModel.getRestaurantsLiveData().observe(this, new Observer<Restaurants>() {
      @Override
      public void onChanged(@Nullable Restaurants restaurants) {
        if (restaurants != null) {
          setupDataInRecyclerView(restaurants);
        }
      }
    });
  }

  /**
   * this method is use to set up recyclerview and its data and setup adapter also
   * @param restaurants restaurants result
   */
  private void setupDataInRecyclerView(Restaurants restaurants) {
    mLinearLayoutManager =new LinearLayoutManager(this);
    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mBinding.rvRestaurant.setLayoutManager(mLinearLayoutManager);
    restaurantAdapter = new RestaurantAdapter(restaurants.getRestaurants());
    mBinding.rvRestaurant.setAdapter(restaurantAdapter);
    Log.v("MainActivity","no of fields count "+new Restaurant().getColumnCount());
  }

  /**
   * initializeViewModel is use to initialize view modle and set view model in binding
   */
  private void initializeViewModel() {
    restaurantsViewModel = ViewModelProviders.of(this).get(RestaurantsViewModel.class);
    mBinding.setRestaurantsViewModel(restaurantsViewModel);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      restaurantsViewModel.getDataFromJsonFile(MainActivity.this,"Restaurants","Menus");
    }
  }
}