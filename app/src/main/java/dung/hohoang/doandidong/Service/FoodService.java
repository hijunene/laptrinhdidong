package dung.hohoang.doandidong.Service;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dung.hohoang.doandidong.DBManger.DBManager;
import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.Util.DBUtil;

public class FoodService {

    private DBManager dbManager;

    public FoodService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean createFood(){
        try{
            dbManager.createOrEditData(DBUtil.CREATE_TABLE_FOOD);

            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<Food> getListFoodByIdFoodType(int idFoodType){
        List<Food> foods = new ArrayList<>();
        Cursor cursor = dbManager.getDataFromData("SELECT * FROM food WHERE food.idfoodtype = ?", new String[] {String.valueOf(idFoodType)});

        while (cursor.moveToNext()){
            Food newFood = new Food();

            newFood.setId(String.valueOf(cursor.getInt(0)));
            newFood.setName(cursor.getString(1));
            newFood.setQuantity(cursor.getInt(2));
            newFood.setPrice(cursor.getInt(3));
            newFood.setImage(cursor.getString(4));

            foods.add(newFood);
        }

        return foods;
    }

    public List<Food> getListFood(){
        List<Food> foods = new ArrayList<>();
        Cursor cursor = dbManager.getDataFromData("SELECT * FROM food", null);

        while (cursor.moveToNext()){
            Food newFood = new Food();

            newFood.setId(String.valueOf(cursor.getInt(0)));
            newFood.setName(cursor.getString(1));
            newFood.setQuantity(cursor.getInt(2));
            newFood.setPrice(cursor.getInt(3));
            newFood.setImage(cursor.getString(4));

            foods.add(newFood);
        }

        return foods;
    }

    public boolean addFood(Food newFood){
        String sqlCommand = "\"INSERT INTO food VALUES(null, \""+ newFood.getName() + "\", "+ newFood.getQuantity() + ", "+ newFood.getPrice() + ", \"" + newFood.getImage() + "\", " + newFood.getFoodType().getId() +")";

        return  dbManager.createOrEditData(sqlCommand);
    }

    public boolean deleteFood(int idFood){
        String sqlCommand = "DELETE FROM food WHERE id = " + idFood;

        return dbManager.createOrEditData(sqlCommand);
    }

    public boolean updateFood(Food food){
        String sqlCommand = "UPDATE food SET name_food = \"" + food.getName() + "\", quantity = " + food.getQuantity() +", amount = " + food.getPrice() + ", image = \""+ food.getImage() +"\" WHERE id = " + food.getId();

        return dbManager.createOrEditData(sqlCommand);
    }

}
