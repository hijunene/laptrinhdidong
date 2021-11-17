package tin.ngolamquang.sqlitedemo.Service;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import tin.ngolamquang.sqlitedemo.DBManger.DBManager;
import tin.ngolamquang.sqlitedemo.Model.FoodType;
import tin.ngolamquang.sqlitedemo.Util.DBUtil;

public class FoodTypeService {
    private DBManager dbManager;

    public FoodTypeService(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean createDbFoodType(){
        try{
            dbManager.createOrEditData(DBUtil.CREATE_TABLE_FOOD_TYPE);

            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<FoodType> getListFoodType(){
        List<FoodType> foodTypes = new ArrayList<>();

        Cursor cursor = dbManager.getDataFromData("SELECT * FROM foodtype", null);

        while (cursor.moveToNext()){
            FoodType newFoodType = new FoodType();

            newFoodType.setId(String.valueOf(cursor.getInt(0)));
            newFoodType.setName(cursor.getString(1));

            foodTypes.add(newFoodType);
        }

        return foodTypes;

    }


}
