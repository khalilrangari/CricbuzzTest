package com.android.khalilcricbuzztest.model;

import java.util.List;

public class Menu {
  private Integer restaurantId;
  private List<Category> categories = null;

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
