package com.thoughtworks;

import java.util.Arrays;

import static com.thoughtworks.App.getItemIds;
import static com.thoughtworks.App.getItemNames;

public class Food {
  private String foodName;
  private String foodID;
  private int count;
  private int indexInFoodList;

  public Food(String foodID, int count) {
    this.foodID = foodID;
    this.count = count;
    this.indexInFoodList =  Arrays.asList(getItemIds()).indexOf(foodID);
    this.foodName = getItemNames()[indexInFoodList];
  }

  public String getName() {
    return foodName;
  }

  public int getCount() {
    return count;
  }

  public String getFoodID() {
    return foodID;
  }

  public int getIndex() {
    return indexInFoodList;
  }
}
