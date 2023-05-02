package com.lqgioan.test.viewModels;

import static org.junit.Assert.assertNotNull;

import com.lqgioan.test.domains.LinkFakeUseCase;
import com.lqgioan.test.domains.MentionFakeUseCase;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MainActivityViewModelTest {
    private MainActivityViewModel mViewModel;

    @Before
    public void setup() {
        // Fake mention
        MentionFakeUseCase mentionFakeUseCase = new MentionFakeUseCase();

        // Fake link use case
        LinkFakeUseCase linkFakeUseCase = new LinkFakeUseCase();

        mViewModel =  new MainActivityViewModel();
        mViewModel.setup(mentionFakeUseCase, linkFakeUseCase);
    }

    @Test
    public void processMessage_Test() {
        mViewModel.processMessage("");

        try {
            CountDownLatch latch = new CountDownLatch(1);
            latch.await(1, TimeUnit.SECONDS);
        } catch (Exception ignored){}

        assertNotNull(mViewModel.getProcessMessageResult().getValue());
    }
}
