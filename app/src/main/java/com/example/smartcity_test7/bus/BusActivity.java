package com.example.smartcity_test7.bus;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_test7.R;
import com.example.smartcity_test7.bus.adapter.BusAdapter;
import com.example.smartcity_test7.bus.pojo.Bus;
import com.example.smartcity_test7.bus.pojo.Station;
import com.example.smartcity_test7.utils.KenUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<Bus> busList = new ArrayList<>();
    private BusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        initView();
        getBus();

    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    public void getBus(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtils.Get("http://124.93.196.45:10002/userinfo/lines/list?pageNum=1&pageSize=10");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        String first = object.getString("first");
                        String end = object.getString("end");
                        String startTime = object.getString("startTime");
                        int price = object.getInt("price");
                        String mileage = object.getString("mileage");
                        List<Station> stationLis = new ArrayList<>();

                        String url = "http://124.93.196.45:10002/userinfo/busStop/list?pageNum=1&pageSize=10&linesId="+id;
                        String json1 = KenUtils.Get(url);
                        JSONObject jsonObject1 = new JSONObject(json1);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("rows");
                        for (int j = 0; j < jsonArray1.length() ; j++) {
                            JSONObject object1 = jsonArray1.getJSONObject(j);
                            String stepsId = object1.getString("stepsId");
                            String name1 = object1.getString("name");
                            String sequence = object1.getString("sequence");
                            stationLis.add(new Station(stepsId,name1,sequence));
                        }
                        busList.add(new Bus(id,name,first,end,startTime,price,mileage,stationLis));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("Ken", "run: "+busList.size());
                            adapter = new BusAdapter(busList);
                            expandableListView.setAdapter(adapter);
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}