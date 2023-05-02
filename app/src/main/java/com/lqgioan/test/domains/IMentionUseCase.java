package com.lqgioan.test.domains;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IMentionUseCase {
    @NotNull
    public List<String> extractMentions(String str);
}
