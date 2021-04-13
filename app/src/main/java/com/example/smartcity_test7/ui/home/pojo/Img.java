package com.example.smartcity_test7.ui.home.pojo;

public class Img {
    private String imgUrl;
    private int sort;

    public Img(String imgUrl, int sort) {
        this.imgUrl = imgUrl;
        this.sort = sort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
