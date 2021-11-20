package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dung.hohoang.doandidong.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtAccount, edtPass;
    Button btnLogin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        addControls();
        
        addEvents();
    }

    public void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtAccount.getText().toString().trim();
                String password = edtPass.getText().toString().trim();

                if(LoginAction(userName, password)){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    Intent iToFoodType = new Intent(LoginActivity.this,FoodTypeActivity.class);

                    startActivity(iToFoodType);
                }else{
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addControls() {
        btnLogin = findViewById(R.id.btnLogin);
        edtAccount = findViewById(R.id.edtAccount);
        edtPass = findViewById(R.id.txtPass);
    }

    public boolean LoginAction(String userName, String password){
        if(userName.equals("hoangdung") && password.equals("12345678")){
            return true;
        }

        return false;
    }



}