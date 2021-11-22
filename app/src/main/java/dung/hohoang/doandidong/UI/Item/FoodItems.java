package dung.hohoang.doandidong.UI.Item;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.UI.Item.ViewHolder.FoodViewHolder;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Util.Util;

public class FoodItems extends BaseAdapter {

    List<Food> foods;
    Context context;

    public FoodItems(List<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodViewHolder foodViewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, null);

            foodViewHolder = new FoodViewHolder();

            foodViewHolder.setTxtPrice(convertView.findViewById(R.id.txtPrice));
            foodViewHolder.setTxtTitle(convertView.findViewById(R.id.txtTitle));
            foodViewHolder.setTxtQuality(convertView.findViewById(R.id.txtQuality));
            foodViewHolder.setImgFood(convertView.findViewById(R.id.imgFood));

            convertView.setTag(foodViewHolder);
        }else{
            foodViewHolder = (FoodViewHolder) convertView.getTag();
        }

        Food food = (Food) getItem(position);

        foodViewHolder.getTxtTitle().setText(food.getName());
        foodViewHolder.getTxtPrice().setText(food.getPrice() + "");
        foodViewHolder.getTxtQuality().setText(food.getQuantity() + "");

        if(Util.validateURL(food.getImage())){
            Picasso.with(context).load(food.getImage()).into(foodViewHolder.getImgFood());
        }else{
            File fileImageFoodType = new File(food.getImage());

            Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

            foodViewHolder.getImgFood().setImageBitmap(bmImageFood);
        }

        return convertView;
    }
}
