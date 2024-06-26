package com.example.finance.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "usuarioId", onDelete = ForeignKey.CASCADE))
public class ContaBancaria {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usuarioId;
    private String nomeBanco;
    private double saldo;

    public ContaBancaria() {}

    public ContaBancaria(int usuarioId, String nomeBanco, double saldo) {
        this.usuarioId = usuarioId;
        this.nomeBanco = nomeBanco;
        this.saldo = saldo;
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

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}