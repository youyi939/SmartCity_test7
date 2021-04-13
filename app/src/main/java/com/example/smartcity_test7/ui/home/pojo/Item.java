package com.example.smartcity_test7.ui.home.pojo;

import java.util.List;

public class Item {

    /**
     * dictCode : 36
     * dictLabel : 时政
     */

    private int dictCode;
    private String dictLabel;
    private List<ItemData> dataList;

    public Item(int dictCode, String dictLabel, List<ItemData> dataList) {
        this.dictCode = dictCode;
        this.dictLabel = dictLabel;
        this.dataList = dataList;
    }

    public List<ItemData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ItemData> dataList) {
        this.dataList = dataList;
    }

    public int getDictCode() {
        return dictCode;
    }

    public void setDictCode(int dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }
}
