package com.example.smartcity_test7;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout btn2;
    private Button goMain;
    private RadioGroup radioGroup;
    private ViewPager viewPager2;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        sharedPreferences = getSharedPreferences("data",0);
        editor = getSharedPreferences("data",0).edit();

    }

    private void initView() {
        btn2 = (ConstraintLayout) findViewById(R.id.btn2);
        goMain = (Button) findViewById(R.id.goMain);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        goMain.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goMain:
                if (sharedPreferences.getString("ip","k").equals("k")){
                    Toast.makeText(GuideActivity.this,"请配置网络信息",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn2:
                Dialog dialog = new Dialog(GuideActivity.this);
                View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.dialog_network,null);
                dialog.setContentView(view);


                dialog.show();
                break;
        }
    }



    public void getLunbo(){

    }
}