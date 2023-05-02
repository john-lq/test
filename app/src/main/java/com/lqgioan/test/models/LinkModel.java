package com.lqgioan.test.models;

import com.google.gson.annotations.SerializedName;

public class LinkModel {
    @SerializedName("url")
    private String mLink;

    @SerializedName("title")
    private String mTitle;

    public LinkModel(String link, String title) {
        mLink = link;
        mTitle = title;
    }

    public String getLink() {
        return mLink;
    }

    public String getTitle() {
        return mTitle;
    }
}
