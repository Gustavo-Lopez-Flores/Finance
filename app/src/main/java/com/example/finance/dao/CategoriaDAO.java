package com.example.finance.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finance.entities.Categoria;

import java.util.List;

@Dao
public interface CategoriaDAO {

    @Insert
    void insert(Categoria categoria);

    @Update
    void update(Categoria categoria);

    @Delete
    void delete(Categoria categoria);

    @Query("DELETE FROM Categoria WHERE id = :categoriaId")
    void deleteCategoriaById(int categoriaId);

    @Query("SELECT * FROM Categoria WHERE id = :id LIMIT 1")
    LiveData<Categoria> getCategoriaById(int id);

    @Query("SELECT * FROM categoria")
    LiveData<List<Categoria>> getAll();
}