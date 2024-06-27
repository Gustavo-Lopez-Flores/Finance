package com.example.finance.view.limites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.LimiteGasto;

public class LimitesViewModel extends AndroidViewModel {

    private final MutableLiveData<LimiteGasto> limites = new MutableLiveData<>();
    private final LocalDatabase db;

    public LimitesViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
    }

    private void carregarContas() {
    }

    public LiveData<LimiteGasto> getLimites() {
        return limites;
    }
}