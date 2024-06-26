package com.example.finance.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finance.entities.ContaBancaria;

import java.util.List;

@Dao
public interface ContaBancariaDAO {

    @Insert
    void insert(ContaBancaria contaBancaria);

    @Update
    void update(ContaBancaria contaBancaria);

    @Delete
    void delete(ContaBancaria contaBancaria);

    @Query("SELECT * FROM ContaBancaria WHERE usuarioId = :usuarioId")
    List<ContaBancaria> getContasByUsuarioId(int usuarioId);

    @Query("SELECT * FROM ContaBancaria WHERE id = :id LIMIT 1")
    ContaBancaria getContaById(int id);
}