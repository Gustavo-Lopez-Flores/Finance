package com.example.finance.view.transacoes;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.TransacaoFinanceira;

public class TransacoesViewModel extends AndroidViewModel {

    private final MutableLiveData<TransacaoFinanceira> transacao =  new MutableLiveData<>();
    private final LocalDatabase db;

    public TransacoesViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
    }

    private void carregarContas() {
    }

    public LiveData<TransacaoFinanceira> getTransacao() {
        return transacao;
    }
}