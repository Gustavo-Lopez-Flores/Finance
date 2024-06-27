package com.example.finance.view.contas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ContasViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}