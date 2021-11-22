package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dung.hohoang.doandidong.Model.FoodType;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodTypeService;
import dung.hohoang.doandidong.UI.Item.FoodTypeItems;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class FoodTypeActivity extends AppCompatActivity {

    TextView txtEditDialog,txtDeleteDialog;
    Button btnAddNewFood;
    FoodTypeService foodTypeService;
    List<FoodType> foodTypes;
    FoodTypeItems foodTypeItems;
    ListView lvFoodType;
    FoodType foodTypeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        init();

        addControls();

        addEvents();

        setAdapterView();

        updateTitleToolBar();
    }

    public void init(){
        foodTypes = new ArrayList<>();
        foodTypeItems = new FoodTypeItems(foodTypes, FoodTypeActivity.this);
    }

    private void addEvents() {
        btnAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFoodType = new Intent(FoodTypeActivity.this, EditFoodTypeActivity.class);

                iFoodType.putExtra("ACTION_CODE", Util.ACTION_CODE_ADD);

                startActivity(iFoodType);
            }
        });

        lvFoodType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodType foodType = foodTypes.get(position);

                if(foodType != null){
                    Intent iFoodType = new Intent(FoodTypeActivity.this, FoodActivity.class);

                    iFoodType.putExtra("ID_FOODTYPE", Integer.parseInt(foodType.getId()));

                    startActivity(iFoodType);
                }
            }
        });

        lvFoodType.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                foodTypeSelected = foodTypes.get(position);

                Dialog dialog = getDialog();

                dialog.show();

                return true;
            }
        });

    }

    public void addControlsByDialog(Dialog dialog){
        txtEditDialog = dialog.findViewById(R.id.txtEditDialog);
        txtDeleteDialog = dialog.findViewById(R.id.txtDeleteDialog);
    }

    public void addEventsDialog(){
        txtEditDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iFoodType = new Intent(FoodTypeActivity.this, EditFoodTypeActivity.class);

                iFoodType.putExtra("ACTION_CODE", Util.ACTION_CODE_EDIT);

                if(foodTypeSelected != null){
                    iFoodType.putExtra("FOOD_TYPE", foodTypeSelected);
                }

                startActivity(iFoodType);
            }
        });

        txtDeleteDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public Dialog getDialog(){
        Dialog dialog = new Dialog(FoodTypeActivity.this);

        dialog.setContentView(R.layout.dialog_action);

        addControlsByDialog(dialog);

        addEventsDialog();

        return dialog;
    }

    public void updateTitleToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar),
                            "Loại thức ăn");
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
        foodTypes.clear();

        foodTypes.addAll( foodTypeService.getListFoodType());

        foodTypeItems.notifyDataSetChanged();
    }

    public void addControls() {
        foodTypeService = new FoodTypeService(DBUtil.getDBManager(FoodTypeActivity.this));

        lvFoodType = findViewById(R.id.lvFoodType);
        btnAddNewFood = findViewById(R.id.btnAddNewFoodType);
    }

}