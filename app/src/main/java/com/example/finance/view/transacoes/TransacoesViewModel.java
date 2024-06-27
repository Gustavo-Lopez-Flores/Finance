package com.example.finance.view.transacoes;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.LimiteGasto;
import com.example.finance.entities.TransacaoFinanceira;

public class TransacoesViewModel extends ViewModel {

    private final MutableLiveData<TransacaoFinanceira> transacao;
    private final LocalDatabase db;

    public TransacoesViewModel(Application application) {
        db = LocalDatabase.getDatabase(application);
        transacao = new MutableLiveData<>();
        carregarContas();
    }

    private void carregarContas() {
    }

    public LiveData<TransacaoFinanceira> getTransacao() {
        return transacao;
    }
}