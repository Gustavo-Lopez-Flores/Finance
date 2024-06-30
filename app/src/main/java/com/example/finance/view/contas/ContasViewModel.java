package com.example.finance.view.contas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;

import java.util.List;

public class ContasViewModel extends AndroidViewModel {

    private final LocalDatabase db;
    private final LiveData<List<ContaBancaria>> allContas;

    public ContasViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        allContas = db.contaBancariaDao().getAll();
    }

    public LiveData<List<ContaBancaria>> getAllContas() {
        return allContas;
    }

    public LiveData<ContaBancaria> getContaById(int contaId) {
        MutableLiveData<ContaBancaria> contaLiveData = new MutableLiveData<>();
        new Thread(() -> {
            ContaBancaria conta = db.contaBancariaDao().getContaById(contaId);
            contaLiveData.postValue(conta);
        }).start();
        return contaLiveData;
    }

    public void insertConta(ContaBancaria conta) {
        new Thread(() -> db.contaBancariaDao().insert(conta)).start();
    }

    public void updateConta(ContaBancaria conta) {
        new Thread(() -> db.contaBancariaDao().update(conta)).start();
    }

    public void deleteConta(int contaId) {
        new Thread(() -> {
            ContaBancaria conta = db.contaBancariaDao().getContaById(contaId);
            if (conta != null) {
                db.contaBancariaDao().delete(conta);
            }
        }).start();
    }
}