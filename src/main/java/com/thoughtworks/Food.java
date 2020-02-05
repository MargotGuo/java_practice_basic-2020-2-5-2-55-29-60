package com.thoughtworks;

import java.util.Arrays;

import static com.thoughtworks.App.*;

public class Food {
  private String foodName;
  private int count;
  private double sumPrice;
  private double discount;

  public Food(String foodID, int count) {
    this.count = count;
    int indexInFoodList = getIndex(foodID);
    this.foodName = getItemNames()[indexInFoodList];
    this.sumPrice = count * getItemPrices()[indexInFoodList];
    this.discount = Arrays.asList(getHalfPriceIds()).contains(foodID) ? 0.5 * sumPrice : 0;
  }

  public static int getIndex(String id) {
    return Arrays.asList(getItemIds()).indexOf(id);
  }

  public String getName() {
    return foodName;
  }

  public double getSumPrice() {
    return sumPrice;
  }

  public double getDiscount() {
    return discount;
  }

  public int getCount() {
    return count;
  }
}
