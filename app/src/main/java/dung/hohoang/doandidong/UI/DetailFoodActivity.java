package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.Util;

public class DetailFoodActivity extends AppCompatActivity {

    ImageView imgFood;
    TextView txtTitleFood, txtPrice,txtQuality;
    Button btnBack, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        updateToolBar();

        addControls();

        addEvents();

        updateUIFood();
    }

    public void updateToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Chi tiết");
    }


    public void updateUIFood() {
        Food foodInfo = Util.getFood(getIntent());

        if(foodInfo != null){
            loadImageFoodType(foodInfo.getImage(), imgFood);

            txtTitleFood.setText(foodInfo.getName());
            txtQuality.setText("Số lượng: " + foodInfo.getQuantity());
            txtPrice.setText("Giá tiền: " + Util.formatCurrey(foodInfo.getPrice()));
        }
    }

    public void loadImageFoodType(String pathOrUrl, ImageView imgFoodType){
        if(Util.validateURL(pathOrUrl)){
            Picasso.with(DetailFoodActivity.this).load(pathOrUrl).into(imgFoodType);
        }else{
            File fileImageFoodType = new File(pathOrUrl);

            Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

            imgFoodType.setImageBitmap(bmImageFood);
        }
    }

    public void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetailFood = new Intent(DetailFoodActivity.this, EditFoodActivity.class);

                iDetailFood.putExtra("FOOD", Util.getFood(getIntent()));

                startActivity(iDetailFood);
            }
        });
    }

    public void addControls() {
        imgFood = findViewById(R.id.imgFood);
        txtTitleFood = findViewById(R.id.txtTitleFood);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuality = findViewById(R.id.txtQuality);

        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
    }
}