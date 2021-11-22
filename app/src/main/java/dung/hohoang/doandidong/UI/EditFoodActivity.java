package dung.hohoang.doandidong.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.Service.FoodService;
import dung.hohoang.doandidong.Util.DBUtil;
import dung.hohoang.doandidong.Util.Util;

public class EditFoodActivity extends AppCompatActivity {
    private final int REQUEST_LOAD_IMAGE = 1;

    FoodService foodService;
    ImageButton btnImage;
    EditText edtTitle, edtPrice,  edtQuality;
    Uri filePath;
    Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        addControls();

        addEvents();

        initService();
    }

    private void initService() {
        foodService = new FoodService(DBUtil.getDBManager(EditFoodActivity.this));
    }

    public void addControls() {
        edtTitle = findViewById(R.id.edtTitle);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuality = findViewById(R.id.edtQuality);
        btnImage = findViewById(R.id.btnImage);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

    public void addEvents(){
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFromStorage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titile = edtTitle.getText().toString().trim();
                int price = Integer.parseInt(edtPrice.getText().toString().trim());
                int quality = Integer.parseInt(edtQuality.getText().toString().trim());

                Food newFood = new Food();

                newFood.setName(titile);
                newFood.setPrice(price);
                newFood.setQuantity(quality);
                newFood.getFoodType().setId(String.valueOf(Util.getIdFoodType(getIntent())));

                if(filePath != null){
                    newFood.setImage(Util.getRealPathFormURI(getApplicationContext(), filePath));
                }else{
                    newFood.setImage("");
                }

                if(addNewFood(newFood)){
                    Toast.makeText(EditFoodActivity.this, "Thêm mòn ăn thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditFoodActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
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

    public void chooseImageFromStorage(){
        Intent iGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        iGallery.setType("image/*");

        startActivityForResult(iGallery, REQUEST_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            filePath = data.getData();

            String link = Util.getRealPathFormURI(EditFoodActivity.this, filePath);

            File fileImageFoodType = new File(link);

            Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

            btnImage.setImageBitmap(bmImageFood);
        }
    }

    public boolean addNewFood(Food newFood){

        return foodService.addFood(newFood);
    }

    public int getActionCode(){
        Intent iFood = getIntent();

        return iFood.getIntExtra("ACTION_CODE", -1);
    }
}