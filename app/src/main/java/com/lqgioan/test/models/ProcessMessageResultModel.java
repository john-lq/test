package com.lqgioan.test.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProcessMessageResultModel {
    @SerializedName("mentions")
    private List<String> mMentions;

    @SerializedName("links")
    private List<LinkModel> mLinks;

    public ProcessMessageResultModel(List<String> mentions, List<LinkModel> links) {
        mMentions = mentions;
        mLinks = links;
    }

    public List<String> getMentions() {
        return mMentions;
    }

    public List<LinkModel> getLinks() {
        return mLinks;
    }
}
