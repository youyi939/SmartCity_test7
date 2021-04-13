package com.example.smartcity_test7.ui.home.pojo;

public class ItemData {

    /**
     * createTime : 2020-10-12 16:06:58
     * updateTime : 2020-10-12 16:07:01
     * id : 5
     * title : 外交部：将妥善安排驻休斯敦总领馆人员
     * content : 汪文斌：关于中国驻休斯敦总领馆馆员的有关情况，相信你已经从媒体上看到了。我这里要说的是，对于中国驻休斯敦总领馆的人员，中方将会作出妥善的安排。
     * imgUrl : /profile/xwen3.jpg
     * likeNumber : 258
     * viewsNumber : 300
     */

    private String createTime;
    private String updateTime;
    private int id;
    private String title;
    private String content;
    private String imgUrl;
    private int likeNumber;
    private int viewsNumber;

    public ItemData(String createTime, String updateTime, int id, String title, String content, String imgUrl, int likeNumber, int viewsNumber) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.id = id;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.likeNumber = likeNumber;
        this.viewsNumber = viewsNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }
}
