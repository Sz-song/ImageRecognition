package com.yuanyu.imagerecognition.social;

import java.util.List;

public class TieziBean {
    private int avatar;
    private String uname;
    private String context;
    private List<Integer> images;

    public TieziBean(){

    }

    public TieziBean(int avatar, String uname, String context, List<Integer> images) {
        this.avatar = avatar;
        this.uname = uname;
        this.context = context;
        this.images = images;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
