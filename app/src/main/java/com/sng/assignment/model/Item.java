package com.sng.assignment.model;


import android.net.Uri;

import java.io.Serializable;

public class Item implements Serializable {

    String img_path;
    String lable,Description;


    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
