package com.lqgioan.test.domains;

import com.lqgioan.test.models.LinkModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ILinkUseCase {
    @NotNull
    public List<String> extractLinks(String str);

    @NotNull
    public Single<List<LinkModel>> getLinkDetails(List<String> links);
}
