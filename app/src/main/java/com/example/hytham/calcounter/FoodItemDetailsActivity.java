package com.example.hytham.calcounter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class FoodItemDetailsActivity extends AppCompatActivity {
    private TextView foodName , calories , dateTaken;
    private Button shareButton;
    private int foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_food_item_details);

        foodName =(TextView) findViewById(R.id.detsFoodName);
        calories = (TextView) findViewById(R.id.detsCaloriesValue);
        dateTaken = (TextView) findViewById(R.id.detsDateText);

        shareButton = (Button) findViewById(R.id.detsShareButton);

        Food food = (Food) getIntent().getSerializableExtra("obj");

        foodName.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordData());

        foodId = food.getFoodId();

        calories.setTextSize(32.5f);
        calories.setTextColor(Color.RED);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCals();
            }
        });


    }
    public void shareCals(){
        StringBuilder dataBuilder = new StringBuilder();

        String name = foodName.getText().toString();
        String cals = calories.getText().toString();
        String date = dateTaken.getText().toString();

        dataBuilder.append(" Food: " + name + "\n");
        dataBuilder.append( " Calories: " + cals + "\n");
        dataBuilder.append( "Eaten on: " + date);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT , "My caloric Intake");
        i.putExtra(Intent.EXTRA_EMAIL , new String[] {"recipient@example.com"});
        i.putExtra(Intent.EXTRA_TEXT , dataBuilder.toString());

        try {
            startActivity(Intent.createChooser(i , "Send mail..."));

        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext() ,"Plz install email client before sending" ,Toast.LENGTH_LONG).show();

        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_items_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.deleteItem){
            AlertDialog.Builder builder = new AlertDialog.Builder(FoodItemDetailsActivity.this);
            builder.setTitle("Delete?!");
            builder.setMessage("Are You sure you want to delete this item ?!");
            builder.setNegativeButton("No" , null);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                    dba.deleteFood(foodId);

                    Toast.makeText(getApplicationContext() , "This item is deleted now! " ,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FoodItemDetailsActivity.this ,DisplayFoodsActivity.class));
                    //remove it from stack
                    FoodItemDetailsActivity.this.finish();
                }
            });
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
