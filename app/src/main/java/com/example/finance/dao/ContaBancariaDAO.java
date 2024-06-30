package com.example.finance.dao;

import androidx.lifecycle.LiveData;
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
    void insert(ContaBancaria conta);

    @Update
    void update(ContaBancaria conta);

    @Delete
    void delete(ContaBancaria conta);

    @Query("DELETE FROM ContaBancaria WHERE id = :contaId")
    void deleteContaById(int contaId);

    @Query("SELECT * FROM ContaBancaria WHERE id = :contaId")
    ContaBancaria getContaById(int contaId);

    @Query("SELECT * FROM ContaBancaria")
    LiveData<List<ContaBancaria>> getAll();
}