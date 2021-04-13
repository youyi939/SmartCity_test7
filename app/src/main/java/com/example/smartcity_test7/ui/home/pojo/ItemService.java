package com.example.smartcity_test7.ui.home.pojo;

public class ItemService {

    /**
     * id : 2
     * serviceName : 城市地铁
     * imgUrl : /profile/ditie.png
     */

    private int id;
    private String serviceName;
    private String imgUrl;

    public ItemService(int id, String serviceName, String imgUrl) {
        this.id = id;
        this.serviceName = serviceName;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
