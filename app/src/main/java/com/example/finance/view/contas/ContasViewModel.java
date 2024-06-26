package com.example.finance.view.contas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContasViewModel extends AndroidViewModel {
    private final LocalDatabase db;
    private final ExecutorService executorService;
    private final MutableLiveData<ContaBancaria> contaSelecionada = new MutableLiveData<>();
    private final LiveData<List<ContaBancaria>> contas;

    public ContasViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        executorService = Executors.newFixedThreadPool(2);
        contas = db.contaBancariaDao().getAll();
    }

    public LiveData<List<ContaBancaria>> getContas() {
        return contas;
    }

    public LiveData<ContaBancaria> getContaById(int contaId) {
        MutableLiveData<ContaBancaria> liveData = new MutableLiveData<>();
        executorService.execute(() -> {
            ContaBancaria conta = db.contaBancariaDao().getContaById(contaId);
            liveData.postValue(conta);
        });
        return liveData;
    }

    public void addConta(ContaBancaria conta) {
        executorService.execute(() -> {
            if (db.usuarioDao().getUsuario(conta.getUsuarioId()) != null) {
                db.contaBancariaDao().insert(conta);
            }
        });
    }

    public void updateConta(ContaBancaria conta) {
        executorService.execute(() -> db.contaBancariaDao().update(conta));
    }

    public void deleteConta(int contaId) {
        executorService.execute(() -> db.contaBancariaDao().deleteContaById(contaId));
    }
}