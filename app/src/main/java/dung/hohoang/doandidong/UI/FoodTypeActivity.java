package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dung.hohoang.doandidong.Model.FoodType;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodTypeService;
import dung.hohoang.doandidong.UI.Item.FoodTypeItems;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.DBUtil;

public class FoodTypeActivity extends AppCompatActivity {

    FoodTypeService foodTypeService;
    List<FoodType> foodTypes;
    FoodTypeItems foodTypeItems;
    ListView lvFoodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        addControls();

        setAdapterView();

        updateTitleToolBar();
    }

    private void updateTitleToolBar() {
        ToolBarCustom toolBar = (ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar);
        toolBar.updateTitle("Loại thức ăn");
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFoodType();


    }

    public void setAdapterView() {
        if(lvFoodType != null){
            lvFoodType.setAdapter(foodTypeItems);
        }
    }

    public void loadDataFoodType() {
        foodTypes.addAll( foodTypeService.getListFoodType());
        foodTypeItems.notifyDataSetChanged();
    }

    public void addControls() {
        foodTypeService = new FoodTypeService(DBUtil.getDBManager(FoodTypeActivity.this));
        foodTypes = new ArrayList<>();
        foodTypeItems = new FoodTypeItems(foodTypes, FoodTypeActivity.this);
        lvFoodType = findViewById(R.id.lvFoodType);

    }

}