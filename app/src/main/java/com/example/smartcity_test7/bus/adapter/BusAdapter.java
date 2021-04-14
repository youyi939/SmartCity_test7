package com.example.smartcity_test7.bus.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.smartcity_test7.bus.pojo.Bus;

import java.util.List;

public class BusAdapter extends BaseExpandableListAdapter {
    private List<Bus> busList;

    public BusAdapter(List<Bus> busList) {
        this.busList = busList;
    }

    @Override
    public int getGroupCount() {
        return busList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return busList.get(i).getStations().size();
    }

    @Override
    public Object getGroup(int i) {
        return busList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return busList.get(i).getStations().get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
