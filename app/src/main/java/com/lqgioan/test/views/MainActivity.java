package com.lqgioan.test.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lqgioan.test.databinding.ActivityMainBinding;
import com.lqgioan.test.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Using view binding
        // https://developer.android.com/topic/libraries/view-binding#groovy
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initViewModel();

        setupViewModel();

        setupViews();
    }

    private void initViewModel() {
        mViewModel = MainActivityViewModel.init(this);
    }

    private void setupViews() {
        mBinding.btnClick.setOnClickListener(v -> {
            String message = mBinding.etMessage.getText().toString();
            if (message.isEmpty()) return;

            mViewModel.processMessage(message);
        });
    }

    private void setupViewModel() {
        mViewModel.getProcessMessageResult().observe(this, json -> {
            mBinding.tvResult.setText(json);
        });

        mViewModel.getProcessingMessage().observe(this, isProcessingMessage -> {
            mBinding.btnClick.setEnabled(!isProcessingMessage);
        });
    }
}