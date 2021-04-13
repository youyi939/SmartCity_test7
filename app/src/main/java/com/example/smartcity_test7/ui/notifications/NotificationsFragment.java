package com.example.smartcity_test7.ui.notifications;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test7.R;
import com.example.smartcity_test7.ui.home.adapter.ServiceAdapter;
import com.example.smartcity_test7.ui.home.adapter.XinwenAdapter;
import com.example.smartcity_test7.ui.home.pojo.Item;
import com.example.smartcity_test7.ui.home.pojo.ItemData;
import com.example.smartcity_test7.utils.KenUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private List<Item> itemList= new ArrayList<>();
    private TabLayout lable_home;
    private RecyclerView xinwen_home;
    private Thread xinwenThread;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        xinwen_home = root.findViewById(R.id.xinwen_home);
        lable_home = root.findViewById(R.id.lable_home);

        getXinwen();

        lable_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Message message = new Message();
                message.what = 4;
                message.obj = tab.getPosition();
                handler.sendMessage(message);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {

                case 3:
//                    Log.i("Ken", "handleMessage: "+itemList.size());
                    for (int i = 0; i < itemList.size() ; i++) {
                        lable_home.addTab(lable_home.newTab().setText(itemList.get(i).getDictLabel()).setTag(itemList.get(i).getDictCode()));
                    }
                    xinwen_home.setLayoutManager(new GridLayoutManager(getActivity(),1));
                    xinwen_home.setAdapter(new XinwenAdapter(itemList.get(0).getDataList()));
                    break;
                case 4:
                    int position = (int) msg.obj;
                    xinwen_home.setLayoutManager(new GridLayoutManager(getActivity(),1));
                    xinwen_home.setAdapter(new XinwenAdapter(itemList.get(position).getDataList()));
                    break;

            }
        }
    };

    public void getXinwen(){
        xinwenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json= KenUtils.Get("http://124.93.196.45:10002/system/dict/data/type/press_category");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int dictCode = object.getInt("dictCode");
                        String dictLabel = object.getString("dictLabel");
                        List<ItemData> dataList = new ArrayList<>();

                        String url = "http://124.93.196.45:10002/press/press/list?pageNum=1&pageSize=10&pressCategory="+dictCode;
                        String json1 = KenUtils.Get(url);
                        JSONObject jsonObject1 = new JSONObject(json1);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("rows");
                        for (int j = 0; j < jsonArray1.length() ; j++) {
                            JSONObject object1 = jsonArray1.getJSONObject(j);
                            String createTime = object1.getString("createTime");
                            String updateTime = object1.getString("updateTime");
                            int id = object1.getInt("id");
                            String title = object1.getString("title");
                            String content = object1.getString("content");
                            String imgUrl = "http://124.93.196.45:10002"+object1.getString("imgUrl");
                            int likeNumber = object1.getInt("likeNumber");
                            int viewsNumber = object1.getInt("viewsNumber");
                            dataList.add(new ItemData(createTime,updateTime,id,title,content,imgUrl,likeNumber,viewsNumber));
                        }
                        itemList.add(new Item(dictCode,dictLabel,dataList));
                    }
                    handler.sendEmptyMessage(3);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        xinwenThread.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        xinwenThread.interrupt();
    }
}