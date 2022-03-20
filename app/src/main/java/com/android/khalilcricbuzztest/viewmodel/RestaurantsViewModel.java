package com.android.khalilcricbuzztest.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.android.khalilcricbuzztest.model.Menu;
import com.android.khalilcricbuzztest.model.MenuData;
import com.android.khalilcricbuzztest.model.Restaurant;
import com.android.khalilcricbuzztest.model.Restaurants;
import com.android.khalilcricbuzztest.utils.Utility;
import com.android.khalilcricbuzztest.view.MainActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantsViewModel extends ViewModel {

  private Gson mGson;
  private Restaurant restaurant;
  private MediatorLiveData<Restaurants> mRestaurantsLiveData = new MediatorLiveData<>();
  private Restaurants restaurants;
  private MenuData menuData;

  public RestaurantsViewModel(){
    mGson = new Gson();
    restaurant = new Restaurant();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void getDataFromJsonFile(MainActivity mainActivity, String fileName, String menuFileName) {
    try {
      JSONObject obj = new JSONObject(Utility.loadJSONFromAsset(mainActivity,fileName));
      restaurants = mGson.fromJson(obj.toString(),Restaurants.class);
      obj = new JSONObject(Utility.loadJSONFromAsset(mainActivity,menuFileName));
      menuData = mGson.fromJson(obj.toString(),MenuData.class);
      final Map<Integer, Menu> menuMap = menuData.getMenus().parallelStream().collect(Collectors.toMap(Menu::getRestaurantId, Function.identity()));
      restaurants.getRestaurants().forEach(r -> r.setMenu(menuMap.get(r.getId())));
      mRestaurantsLiveData.setValue(restaurants);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public MediatorLiveData<Restaurants> getRestaurantsLiveData() {
    return mRestaurantsLiveData;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void searchResult(String query) {
    Restaurants searchResult = new Restaurants();
    searchResult.setRestaurants(restaurants.getRestaurants());
    if(!(query == null || query.trim().isEmpty()))
    {
      Stream<Restaurant> restaurantStream = restaurants.
          getRestaurants().
          parallelStream().
          filter(
              r -> r.getName().toLowerCase().contains(query.toLowerCase())
                  || r.getCuisineType().toLowerCase().contains(query.toLowerCase())
                  || r.getAddress().toLowerCase().contains(query.toLowerCase())
                  || (r.getMenu() != null
                    && r.getMenu().getCategories() != null
                    && r.getMenu().getCategories().parallelStream().filter(
                        c -> c.getMenuItems() != null
                  && c.getMenuItems().parallelStream().anyMatch(m -> m.getName() != null &&  m.getName().toLowerCase().contains(query.toLowerCase()))
              ).count() > 0
          ));
      searchResult.setRestaurants(restaurantStream.collect(Collectors.toList()));
    }
    mRestaurantsLiveData.setValue(searchResult);
  }
}
