package com.example.finance.view.transacoes;

import android.app.Application;

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
    private final MutableLiveData<List<Categoria>> categorias;

    public TransacoesViewModel(Application application) {
        super(application);
        LocalDatabase db = LocalDatabase.getDatabase(application);
        transacaoFinanceiraDAO = db.transacaoFinanceiraDao();
        categoriaDAO = db.categoriaDao();
        executorService = Executors.newFixedThreadPool(2);
        transacoes = new MutableLiveData<>();
        categorias = new MutableLiveData<>();
        loadTransacoes();
        loadCategorias();
    }

    public LiveData<List<TransacaoFinanceira>> getTransacoes() {
        return transacoes;
    }

    public LiveData<List<Categoria>> getCategorias() {
        return categorias;
    }

    private void loadTransacoes() {
        executorService.execute(() -> {
            // Supondo que você tenha um método para obter o ID do usuário atual
            int currentUserId = getCurrentUserId();
            transacoes.postValue(transacaoFinanceiraDAO.getTransacoesByContaId(currentUserId));
        });
    }

    private void loadCategorias() {
        executorService.execute(() -> {
            categorias.postValue(categoriaDAO.getAll().getValue());
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

    public TransacaoFinanceira getTransacaoById(int id) {
        return transacaoFinanceiraDAO.getTransacaoById(id);
    }

    private int getCurrentUserId() {
        // Implementar método para obter o ID do usuário atual
        return 1; // Supondo que 1 é o ID do usuário atual
    }
}