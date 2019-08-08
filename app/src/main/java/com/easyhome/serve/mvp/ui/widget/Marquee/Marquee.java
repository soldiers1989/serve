package com.easyhome.serve.mvp.ui.widget.Marquee;


public class Marquee {
    private String title;
    private String imgUrl;
    private String context;
    private String bgimgUrl;

    public String getBgimgUrl() {
        return bgimgUrl;
    }

    public void setBgimgUrl(String bgimgUrl) {
        this.bgimgUrl = bgimgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
