package com.lqgioan.test.domains;

import static org.junit.Assert.assertTrue;

import com.lqgioan.test.repositories.LinkFakeRepository;
import com.lqgioan.test.utils.Constants;

import org.junit.Before;
import org.junit.Test;

public class ProcessMessageUseCaseTest {
    private ProcessMessageUseCase mProcessMessageUseCase;

    private ProcessMessageUseCase mFakeProcessMessageUseCase;

    @Before
    public void setup() {
        // Mention use case
        MentionUseCase mentionUseCase = new MentionUseCase();

        // Link use case
        LinkFakeRepository linkFakeRepository = new LinkFakeRepository();
        LinkUseCase linkUseCase = new LinkUseCase(linkFakeRepository);

        mProcessMessageUseCase = new ProcessMessageUseCase(mentionUseCase, linkUseCase);

        // Fake process message use case
        // Fake mention
        MentionFakeUseCase mentionFakeUseCase = new MentionFakeUseCase();

        // Fake link use case
        LinkFakeUseCase linkFakeUseCase = new LinkFakeUseCase();

        mFakeProcessMessageUseCase = new ProcessMessageUseCase(mentionFakeUseCase, linkFakeUseCase);
    }

    @Test
    public void processMessage_realUseCase_Test() {
        String link = Constants.GOOGLE_LINK;
        String mention1 = Constants.MENTION_1;
        String mention2 = Constants.MENTION_2;
        String message = "These are the admins @" + mention1 + " and @" + mention2 + ". Their website is " + link;
        String json = mProcessMessageUseCase.processMessage(message).blockingGet();
        assertTrue(json.length() > 0);
        assertTrue(json.contains(mention1));
        assertTrue(json.contains(mention2));
        assertTrue(json.contains(link));
        assertTrue(json.contains("Title: " + link));
    }

    @Test
    public void processMessage_fakedUseCase_Test() {
        String link = Constants.GOOGLE_LINK;
        String mention1 = Constants.MENTION_1;
        String mention2 = Constants.MENTION_2;
        String json = mFakeProcessMessageUseCase.processMessage("").blockingGet();
        assertTrue(json.length() > 0);
        assertTrue(json.contains(mention1));
        assertTrue(json.contains(mention2));
        assertTrue(json.contains(link));
        assertTrue(json.contains("Title: " + link));
    }
}
