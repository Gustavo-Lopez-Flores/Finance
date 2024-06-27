package com.example.finance.view.limites;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.entities.LimiteGasto;

public class LimitesViewModel extends ViewModel {

    private final MutableLiveData<LimiteGasto> limites;
    private final LocalDatabase db;

    public LimitesViewModel(Application application) {
        db = LocalDatabase.getDatabase(application);
        limites = new MutableLiveData<>();
        carregarContas();
    }

    private void carregarContas() {
    }

    public LiveData<LimiteGasto> getLimites() {
        return limites;
    }
}