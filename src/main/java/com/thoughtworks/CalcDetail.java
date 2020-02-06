package com.thoughtworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.thoughtworks.App.getHalfPriceIds;
import static com.thoughtworks.App.getItemPrices;

public class CalcDetail {
  private double sumBeforeDiscount;
  private double discountInPlanA;
  private double discountInPlanB;
  private double finalDiscount;
  private String halfPriceFood;
  private String allFood;
  private String finalPrice;
  private String discountInfo;

  public CalcDetail(String selectedItems) {
    Food[] foodList = saveFoodByArray(selectedItems);
    calcSumPriceAndDiscount(foodList);
    chooseBetterDiscount();
    finalPrice = formatDoubleNumber(sumBeforeDiscount - finalDiscount);
  }

  public Food[] saveFoodByArray(String selectedItems) {
    String[] itemArray = selectedItems.split(",");
    List<Food> foodList = new ArrayList<>();
    for (String foodInfo: itemArray) {
      String foodId = foodInfo.split(" x ")[0];
      int foodCount = Integer.parseInt(foodInfo.split(" x ")[1]);
      foodList.add(new Food(foodId, foodCount));
    }
    return foodList.toArray(new Food[0]);
  }

  public void calcSumPriceAndDiscount(Food[] foodList) {
    StringBuilder allFood = new StringBuilder();
    StringBuilder halfPriceFood = new StringBuilder();
    for (Food food : foodList) {
      double sumPriceOfThisFood = getItemPrices()[food.getIndex()] * food.getCount();
      this.sumBeforeDiscount += sumPriceOfThisFood;
      allFood.append(food.getName()).append(" x ").append(food.getCount()).append(" = ")
          .append(formatDoubleNumber(sumPriceOfThisFood)).append("元\n");
      if (Arrays.asList(getHalfPriceIds()).contains(food.getFoodID())) {
        this.discountInPlanB += 0.5 * sumPriceOfThisFood;
        halfPriceFood.append(food.getName()).append("，");
      }
    }
    this.discountInPlanA = sumBeforeDiscount >= 30 ? 6 : 0;
    this.allFood = allFood.toString();
    this.halfPriceFood = halfPriceFood.toString();
  }

  public void chooseBetterDiscount() {
    if (discountInPlanA == 0 && discountInPlanB == 0) {
      this.finalDiscount = 0;
      this.discountInfo = "";
    } else if (discountInPlanA >= discountInPlanB) {
      this.finalDiscount = discountInPlanA;
      this.discountInfo = "使用优惠:\n"
          + "满30减6元，省6元\n"
          + "-----------------------------------\n";
    } else {
      this.finalDiscount = discountInPlanB;
      this.discountInfo = "使用优惠:\n"
          + "指定菜品半价(" + halfPriceFood.substring(0, halfPriceFood.length() - 1) +")，省"
          + formatDoubleNumber(finalDiscount) + "元\n"
          + "-----------------------------------\n";
    }
  }

  /* 如果double类型的数字的数值为整数，则转换成整型输出，如果不为整数，则输出原数字 */
  public static String formatDoubleNumber(double inputNumber) {
    if(Math.round(inputNumber) - inputNumber == 0) {
      return String.valueOf((int)inputNumber);
    }
    return String.valueOf(inputNumber);
  }

  public String getAllFood() {
    return allFood;
  }

  public String getFinalPrice() {
    return finalPrice;
  }

  public String getDiscountInfo() {
    return discountInfo;
  }
}
