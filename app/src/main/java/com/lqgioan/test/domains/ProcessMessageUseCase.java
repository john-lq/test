package com.lqgioan.test.domains;

import androidx.core.util.Pair;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lqgioan.test.models.ProcessMessageResultModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class ProcessMessageUseCase implements IProcessMessageUseCase {
    private final IMentionUseCase mMentionUseCase;
    private final ILinkUseCase mLinkUseCase;

    public ProcessMessageUseCase(IMentionUseCase mentionUseCase, ILinkUseCase linkUseCase) {
        mMentionUseCase = mentionUseCase;
        mLinkUseCase = linkUseCase;
    }

    @NonNull
    @Override
    public Single<String> processMessage(String message) {
        return Single.create((SingleOnSubscribe<Pair<List<String>, List<String>>>) emitter -> {
            List<String> mentions = mMentionUseCase.extractMentions(message);
            List<String> links = mLinkUseCase.extractLinks(message);
            emitter.onSuccess(new Pair<>(mentions, links));
        }).flatMap(pair -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<String> mentions = pair.first;
            List<String> links = pair.second;

            if (links.isEmpty()) {
                return Single.create(emitter -> {
                    emitter.onSuccess(gson.toJson(new ProcessMessageResultModel(mentions, new ArrayList<>())));
                });
            }

            return mLinkUseCase.getLinkDetails(links).map(linkModels -> {
                return gson.toJson(new ProcessMessageResultModel(mentions, linkModels));
            });
        });
    }
}
