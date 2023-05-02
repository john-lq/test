package com.lqgioan.test.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.lqgioan.test.domains.ILinkUseCase;
import com.lqgioan.test.domains.IMentionUseCase;
import com.lqgioan.test.domains.IProcessMessageUseCase;
import com.lqgioan.test.domains.LinkUseCase;
import com.lqgioan.test.domains.MentionUseCase;
import com.lqgioan.test.domains.ProcessMessageUseCase;
import com.lqgioan.test.repositories.LinkRemoteRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends BaseViewModel {
    public static MainActivityViewModel init(ViewModelStoreOwner owner) {
        MainActivityViewModel vm = new ViewModelProvider(owner).get(MainActivityViewModel.class);
        MentionUseCase mentionUseCase = new MentionUseCase();
        LinkUseCase linkUseCase = new LinkUseCase(new LinkRemoteRepository());
        vm.setup(mentionUseCase, linkUseCase);
        return vm;
    }

    private IProcessMessageUseCase mProcessMessageUseCase;

    private final MutableLiveData<String> mProcessMessageResult = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mProcessingMessage = new MutableLiveData<>(false);

    public MutableLiveData<String> getProcessMessageResult() {
        return mProcessMessageResult;
    }

    public MutableLiveData<Boolean> getProcessingMessage() {
        return mProcessingMessage;
    }

    public void setup(IMentionUseCase mentionUseCase, ILinkUseCase linkUseCase) {
        mProcessMessageUseCase = new ProcessMessageUseCase(mentionUseCase, linkUseCase);
    }

    public void processMessage(String message) {
        mProcessingMessage.postValue(true);

        Disposable disposable = mProcessMessageUseCase.processMessage(message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doAfterTerminate(() -> {
                    mProcessingMessage.setValue(false);
                })
                .subscribe(result -> {
                    mProcessMessageResult.setValue(result);
                }, error -> {
                    // TODO show error if needed
                });
        mDisposable.add(disposable);
    }
}
