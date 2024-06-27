package com.example.finance.view.contas;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;

import java.util.List;

public class ContasViewModel extends ViewModel {

    private final MutableLiveData<ContaBancaria> conta;
    private final LocalDatabase db;

    public ContasViewModel(Application application) {
        db = LocalDatabase.getDatabase(application);
        conta = new MutableLiveData<>();
        carregarContas();
    }

    private void carregarContas() {
    }

    public LiveData<ContaBancaria> getContas() {
        return conta;
    }
}