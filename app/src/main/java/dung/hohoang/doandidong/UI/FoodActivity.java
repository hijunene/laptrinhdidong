package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodService;
import dung.hohoang.doandidong.UI.Item.FoodItems;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class FoodActivity extends AppCompatActivity {

    TextView txtMessDialog;
    ImageView imgFoodDialog;
    Button btnAddNewFood, btnAcceptDialog, btnDetroyDialog;
    FoodService foodService;
    List<Food> foods;
    FoodItems foodItems;
    ListView lvFood;
    Dialog dialogNotifyDelete;
    Food foodSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        init();

        updateToolBar();

        addControls();

        addEvents();

        setAdapterView();
    }

    public void updateToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Thức ăn");
    }

    public void addEvents() {
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food foodInfo = foods.get(position);

                if(foodInfo != null){
                    Intent iFood = new Intent(FoodActivity.this, DetailFoodActivity.class);

                    iFood.putExtra("FOOD", foodInfo);

                    startActivity(iFood);
                }
            }
        });

        btnAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFood = new Intent(FoodActivity.this, EditFoodActivity.class);

                iFood.putExtra("ACTION_CODE", Util.ACTION_CODE_ADD);

                iFood.putExtra("ID_FOODTYPE", Util.getIdFoodType(getIntent()));

                startActivity(iFood);
            }
        });

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                foodSelected = foods.get(position);

                if(foodSelected != null){
                    dialogNotifyDelete = getDialog();

                    addControlsDialog();

                    addEventDialog();

                    updateInfoFoodDialog(foodSelected);

                    dialogNotifyDelete.show();

                }

                return true;
            }
        });
    }

    public Dialog getDialog(){
        Dialog dialog = new Dialog(FoodActivity.this);

        dialog.setContentView(R.layout.dialog_notify);

        return dialog;
    }

    public void addControlsDialog(){
        if(dialogNotifyDelete != null){
            btnAcceptDialog = dialogNotifyDelete.findViewById(R.id.btnAccept);
            btnDetroyDialog = dialogNotifyDelete.findViewById(R.id.btnDetroy);
            imgFoodDialog = dialogNotifyDelete.findViewById(R.id.imgFood);
            txtMessDialog = dialogNotifyDelete.findViewById(R.id.txtMessTitle);
        }
    }

    public void updateInfoFoodDialog(Food food){
        txtMessDialog.setText("Bạn có muốn xóa " + food.getName());

        loadImageFood(food.getImage(), imgFoodDialog);
    }

    public void loadImageFood(String pathOrUrl, ImageView imgFoodType){
        if(pathOrUrl.isEmpty()){
            imgFoodType.setImageResource(R.drawable.no_image);
        }else{
            if(Util.validateURL(pathOrUrl)){
                Picasso.with(FoodActivity.this).load(pathOrUrl).into(imgFoodType);
            }else{
                File fileImageFoodType = new File(pathOrUrl);

                Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

                imgFoodType.setImageBitmap(bmImageFood);
            }
        }
    }

    public void addEventDialog(){
        btnAcceptDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodSelected != null){
                    if(foodService.deleteFood(Integer.parseInt(foodSelected.getId()))){

                        foods.remove(foodSelected);

                        foodItems.notifyDataSetChanged();

                        Toast.makeText(FoodActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(FoodActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                dimissDialog();
            }
        });

        btnDetroyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDialog();
            }
        });
    }

    public void dimissDialog(){
        if(dialogNotifyDelete != null && dialogNotifyDelete.isShowing()){
            dialogNotifyDelete.dismiss();
        }
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
        btnAddNewFood = findViewById(R.id.btnAddNewFood);
    }


}