package com.lqgioan.test.viewModels;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    protected CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
