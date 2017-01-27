package com.example.hytham.calcounter;

import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.CustomListViewAdapter;
import data.DatabaseHandler;
import model.Food;
import util.Utilities;

public class DisplayFoodsActivity extends AppCompatActivity {
    private DatabaseHandler dba;
    private CustomListViewAdapter foodAdapter;
    private ArrayList<Food> dbFoods = new ArrayList<>();
    private ListView listView;
    private Food food;

    private TextView totalFood , totalCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_foods);
        listView =  (ListView) findViewById(R.id.list);
        totalFood = (TextView) findViewById(R.id.totalItemsTextView);
        totalCals = (TextView) findViewById(R.id.totalAmountTextView);

        refreshData();

    }

    private void refreshData() {
        dbFoods.clear();
        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<Food> foodsFromDb = dba.getFoods();

        int calsValue = dba.totalCalories();
        int totalItem = dba.getTotalItems();

        String formattedValue = Utilities.formatNumber(calsValue);
        String formattedItem = Utilities.formatNumber(totalItem);

        totalCals.setText("Total Calories: " + formattedValue);
        totalFood.setText("Total Food: " + formattedItem);

        for (int i = 0 ; i < foodsFromDb.size() ; i++){
            String name = foodsFromDb.get(i).getFoodName();
            String dateText = foodsFromDb.get(i).getRecordData();
            int cals = foodsFromDb.get(i).getCalories();
            int foodId = foodsFromDb.get(i).getFoodId();

            Log.v("FOOD ID: " , String.valueOf(foodId));

            food = new Food();
            food.setFoodName(name);
            food.setCalories(cals );
            food.setRecordData(dateText);
            food.setFoodId(foodId);

            dbFoods.add(food);

        }
        dba.close();
          // set adapter now!

        foodAdapter = new CustomListViewAdapter(DisplayFoodsActivity.this , R.layout.list_raw , dbFoods);
        listView.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();

    }
}
