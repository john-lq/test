package com.lqgioan.test.domains;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MentionUseCaseTest {
    private MentionUseCase mMentionUseCase;

    @Before
    public void setup() {
        mMentionUseCase = new MentionUseCase();
    }

    @Test
    public void extractMentions_emptyString_Test() {
        List<String> result = mMentionUseCase.extractMentions("");
        assertEquals(0, result.size());
    }

    @Test
    public void extractMentions_nonEmptyStringWithMentions_Test() {
        List<String> result = mMentionUseCase.extractMentions("This is the new @user. His name is @john");
        assertEquals(2, result.size());
        assertEquals("user", result.get(0));
        assertEquals("john", result.get(1));
    }
}
