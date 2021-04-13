package com.example.smartcity_test7.ui.personal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity_test7.R;
import com.example.smartcity_test7.utils.KenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PersonalFragment extends Fragment {

    private PersonalViewModel mViewModel;
    private Button userinfo;
    private Button change;
    private Button order;
    private Button feedback;
    private Button logout;
    private TextView textView2;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment, container, false);
        userinfo = view.findViewById(R.id.userinfo);
        change = view.findViewById(R.id.change);
        order = view.findViewById(R.id.order);
        feedback = view.findViewById(R.id.feedback);
        logout = view.findViewById(R.id.logout);
        textView2 = view.findViewById(R.id.textView2);
        imageView = view.findViewById(R.id.imageView);
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = getActivity().getSharedPreferences("data",0).edit();

        getUserInfo();
        return view;
    }


    public void getUserInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token = sharedPreferences.getString("token","k");
                    String json = KenUtils.get_t("http://124.93.196.45:10002/getInfo",token);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject object = jsonObject.getJSONObject("user");
                    String img = "http://124.93.196.45:10002"+object.getString("avatar");
                    String nike = object.getString("nickName");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(nike);
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        // TODO: Use the ViewModel
    }

}