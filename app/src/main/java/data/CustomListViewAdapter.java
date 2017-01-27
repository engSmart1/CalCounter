package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hytham.calcounter.FoodItemDetailsActivity;
import com.example.hytham.calcounter.R;

import java.util.ArrayList;
import java.util.List;

import model.Food;

/**
 * Created by Hytham on 1/26/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<Food> {
    private Activity activity;
    private int layoutResource;
    private ArrayList<Food> foodList = new ArrayList<>();

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        activity = act;
        layoutResource = resource;
        foodList = data;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null || (row.getTag() == null) ){
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource , null);

            holder = new ViewHolder();

            holder.foodName = (TextView) row.findViewById(R.id.name);
            holder.foodCalories = (TextView) row.findViewById(R.id.calories);
            holder.foodDate = (TextView) row.findViewById(R.id.dateText);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.food = getItem(position);
        holder.foodName.setText(holder.food.getFoodName());
        holder.foodCalories.setText(String.valueOf(holder.food.getCalories()));
        holder.foodDate.setText(holder.food.getRecordData());

        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity , FoodItemDetailsActivity.class);


                Bundle mBundle = new Bundle();
                mBundle.putSerializable("obj" , finalHolder.food);
                intent.putExtras(mBundle);

                activity.startActivity(intent);

            }
        });



        return row;
    }
    public class ViewHolder{
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
        int mId ;

    }
}
