package com.example.finance.view.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.User;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<User> user = new MutableLiveData<>();
    private final LocalDatabase db;

    public HomeViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
    }

    public void carregarUser(int userId) {
        new Thread(() -> {
            User usuario = db.usuarioDao().getUsuario(userId);
            user.postValue(usuario);
        }).start();
    }

    public void deleteUser(User user) {
        new Thread(() -> {
            db.usuarioDao().delete(user);
        }).start();
    }

    public LiveData<User> getUser() {
        return user;
    }
}