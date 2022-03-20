package com.android.khalilcricbuzztest.model;

import java.util.List;

public class MenuItem {
  private String id;
  private String name;
  private String description;
  private String price;
  private List<Object> images = null;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public List<Object> getImages() {
    return images;
  }

  public void setImages(List<Object> images) {
    this.images = images;
  }

}
