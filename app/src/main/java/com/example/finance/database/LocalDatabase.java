package com.example.finance.database;

import com.example.finance.dao.ContaBancariaDAO;
import com.example.finance.dao.TransacaoFinanceiraDAO;
import com.example.finance.dao.LimiteGastoDAO;
import com.example.finance.dao.CategoriaDAO;
import com.example.finance.dao.UserDAO;
import com.example.finance.entities.User;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.entities.LimiteGasto;
import com.example.finance.entities.Categoria;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, ContaBancaria.class, TransacaoFinanceira.class, LimiteGasto.class, Categoria.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static volatile LocalDatabase INSTANCE;

    public abstract UserDAO usuarioDao();
    public abstract ContaBancariaDAO contaBancariaDao();
    public abstract TransacaoFinanceiraDAO transacaoFinanceiraDao();
    public abstract LimiteGastoDAO limiteGastoDao();
    public abstract CategoriaDAO categoriaDao();
    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "MeuBD").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
