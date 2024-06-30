package com.example.finance.view.limites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.entities.LimiteGasto;
import com.example.finance.dao.LimiteGastoDAO;

import java.util.List;

public class LimitesViewModel extends ViewModel {

    private MutableLiveData<List<LimiteGasto>> limites;
    private LimiteGastoDAO limiteGastoDAO;
    private int currentUserId;

    public LimitesViewModel() {
        limites = new MutableLiveData<>();
        // limiteGastoDAO = repository.getLimiteGastoDAO();
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        loadLimites();
    }

    public LiveData<List<LimiteGasto>> getLimites() {
        return limites;
    }

    private void loadLimites() {
        if (limiteGastoDAO != null && currentUserId > 0) {
            limites.setValue(limiteGastoDAO.getLimitesByUsuarioId(currentUserId));
        }
    }

    public void insertLimite(LimiteGasto limite) {
        if (limiteGastoDAO != null) {
            limiteGastoDAO.insert(limite);
            loadLimites();
        }
    }

    public void updateLimite(LimiteGasto limite) {
        if (limiteGastoDAO != null) {
            limiteGastoDAO.update(limite);
            loadLimites();
        }
    }

    public void deleteLimite(LimiteGasto limite) {
        if (limiteGastoDAO != null) {
            limiteGastoDAO.delete(limite);
            loadLimites();
        }
    }

    public LimiteGasto getLimiteById(int id) {
        if (limiteGastoDAO != null) {
            return limiteGastoDAO.getLimiteById(id);
        }
        return null;
    }
}