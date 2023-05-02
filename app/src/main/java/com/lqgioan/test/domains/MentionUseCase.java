package com.lqgioan.test.domains;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MentionUseCase implements IMentionUseCase {
    private final Pattern mMentionPattern = Pattern.compile("@\\w+");

    @NonNull
    @Override
    public List<String> extractMentions(String str) {
        Set<String> addedMentions = new HashSet<>();
        ArrayList<String> mentions = new ArrayList<>();
        Matcher matcher = mMentionPattern.matcher(str);

        String mention;
        while (matcher.find()) {
            mention = matcher.group(0);

            if (mention != null && !addedMentions.contains(mention)) {
                mentions.add(mention.substring(1));
                addedMentions.add(mention);
            }
        }

        return mentions;
    }
}
