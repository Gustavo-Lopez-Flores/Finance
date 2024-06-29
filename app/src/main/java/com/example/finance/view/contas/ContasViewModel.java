package com.example.finance.view.contas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;

public class ContasViewModel extends AndroidViewModel {
    private final LocalDatabase db;

    public ContasViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
    }

    public void insertConta(ContaBancaria conta) {
        new Thread(() -> db.contaBancariaDao().insert(conta)).start();
    }
}