package com.lqgioan.test.domains;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.lqgioan.test.models.LinkModel;
import com.lqgioan.test.repositories.ILinkRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.core.Single;

public class LinkUseCase implements ILinkUseCase {
//    private final Pattern mLinkPattern = Patterns.WEB_URL;
    private final Pattern mLinkPattern = Pattern.compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");

    private final ILinkRepository mLinkRepository;

    public LinkUseCase(ILinkRepository linkRepository) {
        mLinkRepository = linkRepository;
    }

    @NonNull
    @Override
    public List<String> extractLinks(String str) {
        Set<String> addedLinks = new HashSet<>();
        ArrayList<String> links = new ArrayList<>();
        Matcher matcher = mLinkPattern.matcher(str);

        String link;
        while (matcher.find()) {
            link = matcher.group(0);

            if (link != null && !addedLinks.contains(link)) {
                links.add(link);
                addedLinks.add(link);
            }
        }

        return links;
    }

    @NonNull
    @Override
    public Single<List<LinkModel>> getLinkDetails(List<String> links) {
        return mLinkRepository.getLinkDetail(links);
    }
}
