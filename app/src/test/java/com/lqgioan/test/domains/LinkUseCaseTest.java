package com.lqgioan.test.domains;

import static org.junit.Assert.assertEquals;

import com.lqgioan.test.models.LinkModel;
import com.lqgioan.test.repositories.LinkFakeRepository;
import com.lqgioan.test.utils.Constants;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinkUseCaseTest {
    private LinkUseCase mLinkUseCase;

    @Before
    public void setup() {
        mLinkUseCase = new LinkUseCase(new LinkFakeRepository());
    }

    @Test
    public void extractLink_emptyString_Test() {
        List<String> result = mLinkUseCase.extractLinks("");
        assertEquals(0, result.size());
    }

    @Test
    public void extractLink_nonEmptyStringWithLink_Test() {
        List<String> result = mLinkUseCase.extractLinks("These are the links. https://vnexpress.net, http://example.com");
        assertEquals(2, result.size());
    }

    @Test
    public void getLinkDetails_withLinkList_Test() {
        String link = Constants.GOOGLE_LINK;
        ArrayList<String> links = new ArrayList<>();
        links.add(link);

        List<LinkModel> linkModels = mLinkUseCase.getLinkDetails(links).blockingGet();

        assertEquals(1, linkModels.size());
        assertEquals(link, linkModels.get(0).getLink());
        assertEquals("Title: " + link, linkModels.get(0).getTitle());
    }
}
