package com.example.finance.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finance.entities.LimiteGasto;

import java.util.List;

@Dao
public interface LimiteGastoDAO {

    @Insert
    void insert(LimiteGasto limiteGasto);

    @Update
    void update(LimiteGasto limiteGasto);

    @Delete
    void delete(LimiteGasto limiteGasto);

    @Query("SELECT * FROM LimiteGasto WHERE usuarioId = :usuarioId")
    List<LimiteGasto> getLimitesByUsuarioId(int usuarioId);

    @Query("SELECT * FROM LimiteGasto WHERE categoriaId = :categoriaId")
    List<LimiteGasto> getLimitesByCategoriaId(int categoriaId);

    @Query("SELECT * FROM LimiteGasto WHERE id = :id LIMIT 1")
    LimiteGasto getLimiteById(int id);
}