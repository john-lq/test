package com.lqgioan.test.repositories;

import androidx.annotation.NonNull;

import com.lqgioan.test.models.LinkModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class LinkFakeRepository implements ILinkRepository {
    @NonNull
    @Override
    public Single<List<LinkModel>> getLinkDetail(List<String> links) {
        return Single.create(emitter -> {
            ArrayList<LinkModel> linkModels = new ArrayList<>();
            links.forEach(item -> {
                linkModels.add(new LinkModel(item, "Title: " + item));
            });

            emitter.onSuccess(linkModels);
        });
    }
}
