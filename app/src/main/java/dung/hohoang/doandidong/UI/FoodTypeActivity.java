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
import dung.hohoang.doandidong.Model.FoodType;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodService;
import dung.hohoang.doandidong.Service.FoodTypeService;
import dung.hohoang.doandidong.UI.Item.FoodTypeItems;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class FoodTypeActivity extends AppCompatActivity {

    final int TYPE_DIALOG_NOTIFY = 1;
    final int TYPE_DIALOG_NORMAL = 0;


    ImageView imgFoodTypeDialog;
    TextView txtEditDialog,txtDeleteDialog, txtMessTitleDialog;
    Button btnAddNewFoodType, btnAcceptDialog, btnDetroyDialog;
    FoodTypeService foodTypeService;
    FoodService foodService;
    List<FoodType> foodTypes;
    FoodTypeItems foodTypeItems;
    ListView lvFoodType;
    FoodType foodTypeSelected;

    Dialog dialog, dialogNotifyDelete;

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
        foodTypeService = new FoodTypeService(DBUtil.getDBManager(FoodTypeActivity.this));
        foodService = new FoodService(DBUtil.getDBManager(FoodTypeActivity.this));
    }

    private void addEvents() {
        btnAddNewFoodType.setOnClickListener(new View.OnClickListener() {
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

                 dialog = getDialog(TYPE_DIALOG_NORMAL);

                dialog.show();

                return true;
            }
        });

    }

    public void addControlsByDialog(Dialog dialog, int typeDialog){
        if (typeDialog == TYPE_DIALOG_NORMAL) {
            txtEditDialog = dialog.findViewById(R.id.txtEditDialog);
            txtDeleteDialog = dialog.findViewById(R.id.txtDeleteDialog);
        } else {
            btnAcceptDialog = dialog.findViewById(R.id.btnAccept);
            btnDetroyDialog = dialog.findViewById(R.id.btnDetroy);
            imgFoodTypeDialog = dialog.findViewById(R.id.imgFood);
            txtMessTitleDialog = dialog.findViewById(R.id.txtMessTitle);
        }
    }


    public void addEventsDialog(int typeDialog){
        if(typeDialog == 0){
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
                    if (foodTypeSelected != null) {
                        dialogNotifyDelete = getDialog(TYPE_DIALOG_NOTIFY);

                        updateUIDialog(TYPE_DIALOG_NOTIFY);

                        dialogNotifyDelete.show();

                        dimissDialog(TYPE_DIALOG_NORMAL);
                    }
                }
            });
        }else{
            btnAcceptDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Food> foods = foodService.getListFoodByIdFoodType(Integer.parseInt(foodTypeSelected.getId()));

                    if(foods.size() == 0){
                        boolean result = foodTypeService.deleteFoodType(Integer.parseInt(foodTypeSelected.getId()));

                        if (result) {
                            foodTypes.remove(foodTypeSelected);

                            foodTypeItems.notifyDataSetChanged();

                            Toast.makeText(FoodTypeActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FoodTypeActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(FoodTypeActivity.this, "Bạn không thể xóa loại thức ăn này", Toast.LENGTH_SHORT).show();
                    }

                    dimissDialog(TYPE_DIALOG_NOTIFY);
                }
            });

            btnDetroyDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dimissDialog(TYPE_DIALOG_NOTIFY);
                }
            });
        }
    }

    public void updateUIDialog(int typeDialog){
        if(typeDialog == TYPE_DIALOG_NOTIFY){
            if(foodTypeSelected != null){
                loadImageFoodType(foodTypeSelected.getImage(), imgFoodTypeDialog);

                txtMessTitleDialog.setText("Bạn đồng ý xóa " + foodTypeSelected.getName());
            }
        }
    }

    public void loadImageFoodType(String pathOrUrl, ImageView imgFoodType){
        if(pathOrUrl.isEmpty()){
            imgFoodType.setImageResource(R.drawable.no_image);
        }else{
            if(Util.validateURL(pathOrUrl)){
                Picasso.with(FoodTypeActivity.this).load(pathOrUrl).into(imgFoodType);
            }else{
                File fileImageFoodType = new File(pathOrUrl);

                Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

                imgFoodType.setImageBitmap(bmImageFood);
            }
        }

    }

    public void dimissDialog(int typeDialog){
        if(typeDialog == 0){
            if( dialog != null && dialog.isShowing() ){
                dialog.dismiss();
            }
        }else{
            if(dialogNotifyDelete != null && dialogNotifyDelete.isShowing() ){
                dialogNotifyDelete.dismiss();
            }
        }
    }

    public Dialog getDialog(int typeDialog){
        Dialog dialog = new Dialog(FoodTypeActivity.this);

        if(typeDialog == 1){
            dialog.setContentView(R.layout.dialog_notify);
        }else{
            dialog.setContentView(R.layout.dialog_action);
        }

        addControlsByDialog(dialog, typeDialog);

        addEventsDialog(typeDialog);

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


        lvFoodType = findViewById(R.id.lvFoodType);
        btnAddNewFoodType = findViewById(R.id.btnAddNewFoodType);
    }

}