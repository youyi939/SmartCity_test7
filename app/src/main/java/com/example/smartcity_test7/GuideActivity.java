package com.example.smartcity_test7;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.smartcity_test7.utils.KenUtils;
import com.google.android.material.shadow.ShadowViewDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout btn2;
    private Button goMain;
    private RadioGroup radioGroup;
    private ViewPager viewPager2;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        sharedPreferences = getSharedPreferences("data",0);
        editor = getSharedPreferences("data",0).edit();
        getLunbo();
        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.one);
                        break;
                    case 1:
                        radioGroup.check(R.id.two);
                        break;
                    case 2:
                        radioGroup.check(R.id.three);
                        break;
                    case 3:
                        radioGroup.check(R.id.four);
                        break;
                    case 4:
                        radioGroup.check(R.id.five);
                        break;
                }
                if (position<4){
                    btn2.setVisibility(View.GONE);
                    goMain.setVisibility(View.GONE);
                }else {
                    btn2.setVisibility(View.VISIBLE);
                    goMain.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                }else {
                    Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.btn2:
                Dialog dialog = new Dialog(GuideActivity.this);
                View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.dialog_network,null);
                dialog.setContentView(view);
                EditText ip = view.findViewById(R.id.ip);
                EditText port = view.findViewById(R.id.port);
                Button test = view.findViewById(R.id.test);
                test.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(ip.getText()) || TextUtils.isEmpty(port.getText())){
                            Toast.makeText(GuideActivity.this,"输入不得为空",Toast.LENGTH_SHORT).show();
                        }else {
                            String ip_s = ip.getText().toString();
                            String port_s = port.getText().toString();
                            String url = "http://"+ip_s+":"+port_s;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String json = KenUtils.Get(url);
                                        JSONObject jsonObject = new JSONObject(json);
                                        int code = jsonObject.getInt("code");
                                        if (code == 401){
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(GuideActivity.this,"段端校验成功",Toast.LENGTH_SHORT).show();
                                                    editor.putString("ip",ip_s);
                                                    editor.putString("port",port_s);
                                                    editor.commit();
                                                    dialog.dismiss();
                                                }
                                            });
                                        }else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(GuideActivity.this,"端口校验失败",Toast.LENGTH_SHORT).show();
                                                    ip.setText("");
                                                    port.setText("");
                                                }
                                            });
                                        }
                                    } catch (Exception e){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(GuideActivity.this,"端口校验失败",Toast.LENGTH_SHORT).show();
                                                ip.setText("");
                                                port.setText("");
                                            }
                                        });

                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                        }
                    }
                });
                dialog.show();
                break;
        }
    }



    public void getLunbo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtils.Get("http://124.93.196.45:10002/userinfo/rotation/lists?pageNum=1&pageSize=10&type=47");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i <  jsonArray.length(); i++) {
                        JSONObject object =jsonArray.getJSONObject(i);
                        String imgUrl = "http://124.93.196.45:10002"+object.getString("imgUrl");
                        urlList.add(imgUrl);
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 1:lunbo
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    viewPager2.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return urlList.size();
                        }

                        @Override
                        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                            return view == object;
                        }

                        @Override
                        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                            container.removeView((View) object);
                        }

                        @NonNull
                        @Override
                        public Object instantiateItem(@NonNull ViewGroup container, int position) {
                            ImageView imageView = new ImageView(GuideActivity.this);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            Glide.with(GuideActivity.this).load(urlList.get(position)).into(imageView);
                            container.addView(imageView);
                            return imageView;
                        }
                    });

                    break;
            }
        }
    };
}