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

    @Query("SELECT * FROM Categoria")
    LiveData<List<Categoria>> getAll();

    @Query("SELECT * FROM Categoria WHERE id = :id")
    LiveData<Categoria> getCategoriaById(int id);

    @Insert
    void insert(Categoria categoria);

    @Update
    void update(Categoria categoria);

    @Delete
    void delete(Categoria categoria);

    @Query("DELETE FROM Categoria WHERE id = :id")
    void deleteCategoriaById(int id);
}