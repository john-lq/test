package com.lqgioan.test.domains;

import androidx.annotation.NonNull;

import com.lqgioan.test.models.LinkModel;
import com.lqgioan.test.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class LinkFakeUseCase implements ILinkUseCase {
    @NonNull
    @Override
    public List<String> extractLinks(String str) {
        ArrayList<String> links = new ArrayList<>();
        links.add(Constants.GOOGLE_LINK);
        return links;
    }

    @NonNull
    @Override
    public Single<List<LinkModel>> getLinkDetails(List<String> links) {
        return Single.create(emitter -> {
            ArrayList<LinkModel> result = new ArrayList<>();
            result.add(new LinkModel(Constants.GOOGLE_LINK, "Title: " + Constants.GOOGLE_LINK));
            emitter.onSuccess(result);
        });
    }
}
