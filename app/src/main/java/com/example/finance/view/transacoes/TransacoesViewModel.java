package com.example.finance.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.dao.TransacaoFinanceiraDAO;
import com.example.finance.entities.Categoria;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.dao.CategoriaDAO;

import java.util.List;

public class TransacoesViewModel extends ViewModel {

    private MutableLiveData<List<TransacaoFinanceira>> transacoes;
    private MutableLiveData<List<Categoria>> categorias;
    private TransacaoFinanceiraDAO transacaoFinanceiraDAO;

    public TransacoesViewModel() {
        transacoes = new MutableLiveData<>();
        categorias = new MutableLiveData<>();
        // Assumindo que o DAO é obtido através de um repositório
        // transacaoFinanceiraDAO = repository.getTransacaoFinanceiraDAO();
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
        transacoes.setValue(transacaoFinanceiraDAO.getTransacoesByContaId(1)); // Assumindo contaId = 1
    }

    private void loadCategorias() {
        // Carregar categorias do DAO
         categorias.setValue(categoriaDao.getAllCategorias());
    }

    public void insertTransacao(TransacaoFinanceira transacao) {
        transacaoFinanceiraDAO.insert(transacao);
        loadTransacoes();
    }

    public void updateTransacao(TransacaoFinanceira transacao) {
        transacaoFinanceiraDAO.update(transacao);
        loadTransacoes();
    }

    public void deleteTransacao(TransacaoFinanceira transacao) {
        transacaoFinanceiraDAO.delete(transacao);
        loadTransacoes();
    }

    public TransacaoFinanceira getTransacaoById(int id) {
        return transacaoFinanceiraDAO.getTransacaoById(id);
    }
}