package dung.hohoang.doandidong.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import dung.hohoang.doandidong.Model.FoodType;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodTypeService;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class EditFoodTypeActivity extends AppCompatActivity {
    FoodTypeService foodTypeService;
    Button btnSave, btnBack;
    ImageButton btnImageFoodType;
    EditText edtFoodType;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_type);

        init();

        requestPermission();

        addControls();

        addEvents();

        updateUI(Util.getActionCode(getIntent()));
    }

    public void init(){
        FoodType foodType = getFoodType();

        if(Util.getActionCode(getIntent()) == Util.ACTION_CODE_EDIT){
            filePath = Uri.parse(foodType.getImage());
        }

        foodTypeService = new FoodTypeService(DBUtil.getDBManager(EditFoodTypeActivity.this));
    }

    public void addEvents() {
        btnImageFoodType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFromStorage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.getActionCode(getIntent()) == Util.ACTION_CODE_ADD){
                    String title = edtFoodType.getText().toString().trim();

                    FoodType newFoodType = new FoodType();

                    newFoodType.setName(title);
                    newFoodType.setImage(Util.getRealPathFormURI(EditFoodTypeActivity.this, filePath));

                    boolean result = foodTypeService.addNewFoodtype(newFoodType);

                    if(result){
                        Toast.makeText(EditFoodTypeActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(EditFoodTypeActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    String title = edtFoodType.getText().toString().trim();

                    FoodType foodType = getFoodType();

                    foodType.setName(title);

                    if(Util.validateURL(foodType.getImage())){
                        foodType.setImage(foodType.getImage());
                    }else{
                        foodType.setImage(Util.getRealPathFormURI(EditFoodTypeActivity.this, filePath));
                    }

                    if(foodTypeService.updateFoodType(foodType)){
                        Toast.makeText(EditFoodTypeActivity.this, "Cập nhật loại món ăn thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditFoodTypeActivity.this, "Cập nhật loại món ăn thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void updateUI(int actionCode){
        if(actionCode == 1){
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Sửa loại món ăn");

            FoodType foodType = getFoodType();

            if(foodType != null){

                edtFoodType.setText(foodType.getName());

                loadImageFoodType(foodType.getImage(), btnImageFoodType);
            }
        }else{
            Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Thêm loại món ăn");
        }
    }

    public void loadImageFoodType(String fileNameOrUrl, ImageView btnImage){
        if(Util.validateURL(fileNameOrUrl)){
            Picasso.with(EditFoodTypeActivity.this).load(filePath).into(btnImageFoodType);
        }else{
            File fileImageFoodType = new File(fileNameOrUrl);

            Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

            btnImage.setImageBitmap(bmImageFood);
        }
    }

    public void chooseImageFromStorage(){
        Intent iGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        iGallery.setType("image/*");

        startActivityForResult(iGallery, Util.REQUEST_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Util.REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            filePath = data.getData();

            String link = Util.getRealPathFormURI(EditFoodTypeActivity.this, filePath);

            loadImageFoodType(link, btnImageFoodType);
        }
    }

    public void requestPermission(){
        if(ContextCompat.checkSelfPermission(EditFoodTypeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
           requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , Util.REQUEST_PERMISSION_CODE);
        }
    }

    public void addControls(){
        btnImageFoodType = findViewById(R.id.btnImageFoodType);
        edtFoodType = findViewById(R.id.edtTitleFoodType);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
        btnImageFoodType= findViewById(R.id.btnImageFoodType);

    }

    public FoodType getFoodType(){
        Intent iFoodType = getIntent();

        if(iFoodType.hasExtra("FOOD_TYPE")){
            return (FoodType) iFoodType.getSerializableExtra("FOOD_TYPE");
        }

        return null;
    }
}