package com.example.finance.view.contas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;

public class ContasViewModel extends AndroidViewModel {

    private final MutableLiveData<ContaBancaria> conta = new MutableLiveData<>();
    private final LocalDatabase db;

    public ContasViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
    }

    private void carregarContas() {
    }

    public LiveData<ContaBancaria> getContas() {
        return conta;
    }
}