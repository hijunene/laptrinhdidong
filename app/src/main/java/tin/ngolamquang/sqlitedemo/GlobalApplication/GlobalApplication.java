package tin.ngolamquang.sqlitedemo.GlobalApplication;

import android.app.Application;

import java.util.List;

import tin.ngolamquang.sqlitedemo.Model.Food;
import tin.ngolamquang.sqlitedemo.Model.FoodType;
import tin.ngolamquang.sqlitedemo.Service.FoodTypeService;
import tin.ngolamquang.sqlitedemo.Service.FoodService;
import tin.ngolamquang.sqlitedemo.Util.DBUtil;
import tin.ngolamquang.sqlitedemo.Util.FakeData;

public class GlobalApplication extends Application {

    private FoodTypeService foodTypeService;
    private FoodService foodService;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

        foodTypeService.createDbFoodType();

        foodService.createFood();

        createFakeData();
    }

    public void createFakeData(){
        List<FoodType> foodTypes = foodTypeService.getListFoodType();
        List<Food> foods = foodService.getListFood();

        if(foodTypes.size() == 0 && foods.size() == 0){
            FakeData fakeData = new FakeData();
            fakeData.addDataFoodType(getApplicationContext());

            fakeData.addDataFoood(getApplicationContext());
        }else{
           return;
        }

        return;
    }


    public void init(){
        foodService = new FoodService(DBUtil.getDBManager(getApplicationContext()));
        foodTypeService = new FoodTypeService(DBUtil.getDBManager(getApplicationContext()));
    }
}
