package dung.hohoang.doandidong.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;
import dung.hohoang.doandidong.Util.Util;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        updateTitleToolBar();
    }

    public void updateTitleToolBar() {
        Util.updateTitleToolBar((ToolBarCustom) getSupportFragmentManager().findFragmentById(R.id.fragToolbar), "Giới thiệu");
    }
}