package com.example.dcastalia.foodpeon3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dcastalia on 2/12/18.
 */

public class Resturent {
    @SerializedName("Kitchen_id")
    @Expose
    private Integer kitchenId;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Kitchen_name")
    @Expose
    private String kitchenName;

    public Resturent(Integer kitchenId, String location, String kitchenName, String imgSrc) {
        this.kitchenId = kitchenId;
        this.location = location;
        this.kitchenName = kitchenName;
        this.imgSrc = imgSrc;
    }

    public Resturent() {
    }

    @SerializedName("img_src")
    @Expose
    private String imgSrc;

    public Integer getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(Integer kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
