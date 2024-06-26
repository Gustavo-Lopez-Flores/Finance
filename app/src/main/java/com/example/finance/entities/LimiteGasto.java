package com.example.finance.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "usuarioId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Categoria.class, parentColumns = "id", childColumns = "categoriaId", onDelete = ForeignKey.CASCADE)
})
public class LimiteGasto {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usuarioId;
    private int categoriaId;
    private double valorLimite;

    public LimiteGasto() {}

    public LimiteGasto(int usuarioId, int categoriaId, double valorLimite) {
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.valorLimite = valorLimite;
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

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(double valorLimite) {
        this.valorLimite = valorLimite;
    }
}