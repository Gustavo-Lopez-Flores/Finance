package com.example.finance.view.categoria;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.finance.database.LocalDatabase;
import com.example.finance.entities.Categoria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoriaViewModel extends AndroidViewModel {
    private final LocalDatabase db;
    private final ExecutorService executorService;
    private final LiveData<List<Categoria>> categorias;

    public CategoriaViewModel(Application application) {
        super(application);
        db = LocalDatabase.getDatabase(application);
        executorService = Executors.newFixedThreadPool(2);
        categorias = db.categoriaDao().getAll();
    }

    public LiveData<List<Categoria>> getCategorias() {
        return categorias;
    }

    public LiveData<Categoria> getCategoriaById(int categoriaId) {
        return db.categoriaDao().getCategoriaById(categoriaId);
    }

    public void addCategoria(Categoria categoria) {
        executorService.execute(() -> {
            if (db.usuarioDao().getUsuario(categoria.getUsuarioId()) != null) {
                db.categoriaDao().insert(categoria);
            }
        });
    }

    public void updateCategoria(Categoria categoria) {
        executorService.execute(() -> db.categoriaDao().update(categoria));
    }

    public void deleteCategoria(int categoriaId) {
        executorService.execute(() -> db.categoriaDao().deleteCategoriaById(categoriaId));
    }
}