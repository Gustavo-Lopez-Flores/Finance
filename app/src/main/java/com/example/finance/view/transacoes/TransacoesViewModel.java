package com.example.finance.view.transacoes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransacoesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TransacoesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}