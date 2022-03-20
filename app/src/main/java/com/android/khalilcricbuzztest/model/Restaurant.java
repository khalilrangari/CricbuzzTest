package com.android.khalilcricbuzztest.model;

import java.util.List;

public class Restaurant {
  private Integer id;
  private String name;
  private String neighborhood;
  private String photograph;
  private String address;
  private Latlng latlng;
  private String cuisine_type;
  private OperatingHours operatingHours;
  private List<Review> reviews = null;
  private Menu menu;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getPhotograph() {
    return photograph;
  }

  public void setPhotograph(String photograph) {
    this.photograph = photograph;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Latlng getLatlng() {
    return latlng;
  }

  public void setLatlng(Latlng latlng) {
    this.latlng = latlng;
  }

  public String getCuisineType() {
    return cuisine_type;
  }

  public void setCuisineType(String cuisineType) {
    this.cuisine_type = cuisineType;
  }

  public OperatingHours getOperatingHours() {
    return operatingHours;
  }

  public void setOperatingHours(OperatingHours operatingHours) {
    this.operatingHours = operatingHours;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public int getColumnCount() {
    return getClass().getDeclaredFields().length;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Menu getMenu() {
    return this.menu;
  }
}
