package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodService;
import dung.hohoang.doandidong.UI.Item.FoodItems;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class FoodActivity extends AppCompatActivity {

    FoodService foodService;
    List<Food> foods;
    FoodItems foodItems;
    ListView lvFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        init();

        addControls();

        addEvents();

        setAdapterView();
    }

    public void addEvents() {
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = foods.get(position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFood();
    }

    public void loadDataFood() {
        foods.clear();

        foods.addAll(foodService.getListFoodByIdFoodType(Util.getIdFoodType(getIntent())));

        foodItems.notifyDataSetChanged();
    }

    public void setAdapterView() {
        if(lvFood != null && foodItems != null){
            lvFood.setAdapter(foodItems);
        }
    }

    public void init(){
        foodService = new FoodService(DBUtil.getDBManager(FoodActivity.this));
        foods = new ArrayList<>();
        foodItems = new FoodItems( foods,FoodActivity.this);
    }

    public void addControls() {
        lvFood = findViewById(R.id.lvFood);
    }


}