package com.example.finance.view.home;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.entities.User;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<User> user;
    private final LocalDatabase db;

    public HomeViewModel(Application application) {
        db = LocalDatabase.getDatabase(application);
        user = new MutableLiveData<>();
        carregarUser();
    }

    private void carregarUser() {
    }

    public LiveData<User> getUser() {
        return user;
    }
}