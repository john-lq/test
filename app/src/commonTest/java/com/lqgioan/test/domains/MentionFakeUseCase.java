package com.lqgioan.test.domains;

import androidx.annotation.NonNull;

import com.lqgioan.test.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MentionFakeUseCase implements IMentionUseCase {
    @NonNull
    @Override
    public List<String> extractMentions(String str) {
        ArrayList<String> mentions = new ArrayList<>();
        mentions.add(Constants.MENTION_1);
        mentions.add(Constants.MENTION_2);
        return mentions;
    }
}
