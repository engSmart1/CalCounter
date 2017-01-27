package model;

import java.io.Serializable;

/**
 * Created by Hytham on 1/26/2017.
 */

public class Food implements Serializable {
    private static final long serialVersionUID = 10L;
    private String foodName;
    private int calories;
    private int foodId;
    private String recordData;

    public Food(String food , int cals , int id , String data){
        foodName = food;
        calories = cals;
        foodId = id;
        recordData = data;
    }
    public Food(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
