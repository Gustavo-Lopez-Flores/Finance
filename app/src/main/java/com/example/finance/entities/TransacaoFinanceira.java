package com.example.finance.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = ContaBancaria.class, parentColumns = "id", childColumns = "contaId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Categoria.class, parentColumns = "id", childColumns = "categoriaId", onDelete = ForeignKey.CASCADE)
})
public class TransacaoFinanceira {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int contaId;
    private int categoriaId;
    private double valor;
    private String data;
    private String descricao;

    public TransacaoFinanceira() {}

    public TransacaoFinanceira(int contaId, int categoriaId, double valor, String data, String descricao) {
        this.contaId = contaId;
        this.categoriaId = categoriaId;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}