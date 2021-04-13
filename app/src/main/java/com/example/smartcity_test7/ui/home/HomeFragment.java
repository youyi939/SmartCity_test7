package com.example.smartcity_test7.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test7.R;
import com.example.smartcity_test7.ui.home.adapter.ServiceAdapter;
import com.example.smartcity_test7.ui.home.adapter.XinwenAdapter;
import com.example.smartcity_test7.ui.home.pojo.Img;
import com.example.smartcity_test7.ui.home.pojo.Item;
import com.example.smartcity_test7.ui.home.pojo.ItemData;
import com.example.smartcity_test7.ui.home.pojo.ItemService;
import com.example.smartcity_test7.utils.KenUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewFlipper viewFlipper_home;
    private RecyclerView recycler_service_home;
    private RecyclerView recycler_service_home2;
    private RecyclerView xinwen_home;
    private List<ItemService> serviceList = new ArrayList<>();
    private List<ItemService> serviceList2 = new ArrayList<>();
    private List<Img> imgList = new ArrayList<>();
    private List<Item> itemList= new ArrayList<>();
    private TabLayout lable_home;
    private Thread serviceThread;
    private Thread lunboThread;
    private Thread xinwenThread;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper_home = root.findViewById(R.id.viewFlipper_home);
        recycler_service_home = root.findViewById(R.id.recycler_service_home);
        recycler_service_home2 = root.findViewById(R.id.recycler_service_home2);
        xinwen_home = root.findViewById(R.id.xinwen_home);
        lable_home = root.findViewById(R.id.lable_home);

        getService();
        getLunbo();
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


    /**
     * 1:service
     * 2:lunbo
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    recycler_service_home.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                    recycler_service_home.setAdapter(new ServiceAdapter(serviceList));
                    serviceList2 = serviceList.subList(0, 4);
                    recycler_service_home2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recycler_service_home2.setAdapter(new ServiceAdapter(serviceList2));
                    break;
                case 2:
                    viewFlipper_home.removeAllViews();
                    for (int i = 0; i < imgList.size(); i++) {
                        ImageView imageView = new ImageView(getActivity());
                        Glide.with(getActivity()).load(imgList.get(i).getImgUrl()).into(imageView);
                        viewFlipper_home.addView(imageView);
                    }
                    break;
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


    public void getLunbo() {
        lunboThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtils.Get("http://124.93.196.45:10002/userinfo/rotation/list?pageNum=1&pageSize=10&type=45");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String imgUrl = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        int sort = object.getInt("sort");
                        imgList.add(new Img(imgUrl, sort));
                    }
                    handler.sendEmptyMessage(2);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        lunboThread.start();
    }

    public void getService() {
        serviceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = KenUtils.Get("http://124.93.196.45:10002/service/service/list?pageNum=1&pageSize=10");
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int id = object.getInt("id");
                        String serviceName = object.getString("serviceName");
                        String imgUrl = "http://124.93.196.45:10002" + object.getString("imgUrl");
                        serviceList.add(new ItemService(id, serviceName, imgUrl));
                    }
                    serviceList.sort(new Comparator<ItemService>() {
                        @Override
                        public int compare(ItemService itemService, ItemService t1) {
                            int a = itemService.getId();
                            int b = t1.getId();
                            return a - b;
                        }
                    });
                    serviceList.add(new ItemService(0, "更多服务", ""));
                    handler.sendEmptyMessage(1);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        serviceThread.start();
    }

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
        serviceThread.interrupt();
        lunboThread.interrupt();
        xinwenThread.interrupt();
    }
}