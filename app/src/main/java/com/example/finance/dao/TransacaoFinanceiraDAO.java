package com.example.finance.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finance.entities.TransacaoFinanceira;

import java.util.List;

@Dao
public interface TransacaoFinanceiraDAO {

    @Insert
    void insert(TransacaoFinanceira transacaoFinanceira);

    @Update
    void update(TransacaoFinanceira transacaoFinanceira);

    @Delete
    void delete(TransacaoFinanceira transacaoFinanceira);

    @Query("SELECT * FROM TransacaoFinanceira WHERE contaId = :contaId")
    List<TransacaoFinanceira> getTransacoesByContaId(int contaId);

    @Query("SELECT * FROM TransacaoFinanceira WHERE categoriaId = :categoriaId")
    List<TransacaoFinanceira> getTransacoesByCategoriaId(int categoriaId);

    @Query("SELECT * FROM TransacaoFinanceira WHERE id = :id LIMIT 1")
    TransacaoFinanceira getTransacaoById(int id);
}