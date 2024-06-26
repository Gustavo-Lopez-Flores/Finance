package com.example.finance.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.finance.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User WHERE email = :email AND senha = :senha")
    User login(String email, String senha);

    @Query("SELECT * FROM User WHERE email = :email")
    User getByEmail(String email);

    @Query("SELECT * FROM User")
    List<User> getAllUsuarios();

    @Query("SELECT * FROM User WHERE id = :id LIMIT 1")
    User getUsuario(int id);
}