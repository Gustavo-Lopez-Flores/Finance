package com.example.finance.view.transacoes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.Categoria;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.dao.TransacaoFinanceiraDAO;
import com.example.finance.dao.CategoriaDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransacoesViewModel extends AndroidViewModel {
    private final TransacaoFinanceiraDAO transacaoFinanceiraDAO;
    private final CategoriaDAO categoriaDAO;
    private final ExecutorService executorService;
    private final MutableLiveData<List<TransacaoFinanceira>> transacoes;
    private final LiveData<List<Categoria>> categorias;

    public TransacoesViewModel(Application application) {
        super(application);
        LocalDatabase db = LocalDatabase.getDatabase(application);
        transacaoFinanceiraDAO = db.transacaoFinanceiraDao();
        categoriaDAO = db.categoriaDao();
        executorService = Executors.newFixedThreadPool(2);
        transacoes = new MutableLiveData<>();
        categorias = categoriaDAO.getAll();
        loadTransacoes();
    }

    public LiveData<List<TransacaoFinanceira>> getTransacoes() {
        return transacoes;
    }

    public LiveData<List<Categoria>> getCategorias() {
        return categorias;
    }

    private void loadTransacoes() {
        executorService.execute(() -> {
            int currentUserId = getCurrentUserId();
            List<TransacaoFinanceira> transacaoList = transacaoFinanceiraDAO.getTransacoesByContaId(currentUserId);
            transacoes.postValue(transacaoList);
        });
    }

    public void insertTransacao(TransacaoFinanceira transacao) {
        executorService.execute(() -> {
            transacaoFinanceiraDAO.insert(transacao);
            loadTransacoes();
        });
    }

    public void updateTransacao(TransacaoFinanceira transacao) {
        executorService.execute(() -> {
            transacaoFinanceiraDAO.update(transacao);
            loadTransacoes();
        });
    }

    public void deleteTransacao(TransacaoFinanceira transacao) {
        executorService.execute(() -> {
            transacaoFinanceiraDAO.delete(transacao);
            loadTransacoes();
        });
    }

    public LiveData<TransacaoFinanceira> getTransacaoById(int id) {
        MutableLiveData<TransacaoFinanceira> transacao = new MutableLiveData<>();
        executorService.execute(() -> {
            TransacaoFinanceira transacaoFinanceira = transacaoFinanceiraDAO.getTransacaoById(id);
            transacao.postValue(transacaoFinanceira);
        });
        return transacao;
    }

    private int getCurrentUserId() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("FinanceApp", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("currentUserId", -1); // Retorna -1 se o ID do usuário não for encontrado
    }
}