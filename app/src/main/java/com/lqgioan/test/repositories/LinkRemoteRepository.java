package com.lqgioan.test.repositories;

import androidx.annotation.NonNull;

import com.lqgioan.test.models.LinkModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class LinkRemoteRepository implements ILinkRepository{
    @NonNull
    @Override
    public Single<List<LinkModel>> getLinkDetail(List<String> links) {
        return Observable.fromIterable(links)
                .map(url -> {
                    String title = "";
                    try {
                        Document doc = Jsoup.connect(url).get();
                        title = doc.title();
                    } catch (Exception ignored) {}

                    return new LinkModel(url, title);
                })
                .toList();
    }
}
