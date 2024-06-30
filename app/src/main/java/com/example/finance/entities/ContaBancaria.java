package com.example.finance.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "usuarioId", onDelete = ForeignKey.CASCADE))
public class ContaBancaria {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usuarioId;
    private String nomeBanco;
    private double saldo;

    public ContaBancaria() {
        // Construtor vazio necessário para Room
    }

    public ContaBancaria(int usuarioId, String nomeBanco, double saldo) {
        this.usuarioId = usuarioId;
        this.nomeBanco = nomeBanco;
        this.saldo = saldo;
    }

    // Getters e setters
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