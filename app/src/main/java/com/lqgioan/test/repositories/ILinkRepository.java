package com.lqgioan.test.repositories;

import com.lqgioan.test.models.LinkModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ILinkRepository {
    public @NotNull Single<List<LinkModel>> getLinkDetail(List<String> links);
}
