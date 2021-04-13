package com.example.smartcity_test7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.smartcity_test7.utils.KenUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        sharedPreferences = getSharedPreferences("data",0);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String json = KenUtils.Get("http://124.93.196.45:10002/userinfo/rotation/lists?pageNum=1&pageSize=10&type=47");
//                    Log.i("Ken", "run: "+json);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

        if (sharedPreferences.getBoolean("guide",true)){
            Intent intent = new Intent(MainActivity.this,GuideActivity.class);
            startActivity(intent);
        }else if (sharedPreferences.getString("token","k").equals("k")){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }

}