package com.example.finance.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "usuarioId", onDelete = ForeignKey.CASCADE))
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usuarioId;
    private String nomeCategoria;

    public Categoria() {}

    public Categoria(int usuarioId, String nomeCategoria) {
        this.usuarioId = usuarioId;
        this.nomeCategoria = nomeCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}