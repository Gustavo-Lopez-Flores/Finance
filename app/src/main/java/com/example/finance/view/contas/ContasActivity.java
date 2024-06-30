package com.example.finance.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.databinding.ActivityContasBinding;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.view.contas.ContasViewModel;

public class ContasActivity extends AppCompatActivity {

    private ActivityContasBinding binding;
    private ContasViewModel contasViewModel;
    private int contaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        contaId = getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1);
        if (contaId != -1) {
            contasViewModel.getContaById(contaId).observe(this, this::preencherDadosConta);
            binding.btnExcluir.setVisibility(View.VISIBLE);
        }

        binding.btnSalvar.setOnClickListener(v -> salvarConta());
        binding.btnExcluir.setOnClickListener(v -> excluirConta());
    }

    private void preencherDadosConta(ContaBancaria conta) {
        if (conta != null) {
            binding.edtNomeBanco.setText(conta.getNomeBanco());
            binding.edtSaldo.setText(String.valueOf(conta.getSaldo()));
        }
    }

    private void salvarConta() {
        String nomeBanco = binding.edtNomeBanco.getText().toString().trim();
        double saldo = Double.parseDouble(binding.edtSaldo.getText().toString().trim());

        if (nomeBanco.isEmpty() || saldo < 0) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        ContaBancaria conta = new ContaBancaria(contaId, nomeBanco, saldo);
        if (contaId == -1) {
            contasViewModel.insertConta(conta);
        } else {
            contasViewModel.updateConta(conta);
        }

        finish();
    }

    private void excluirConta() {
        if (contaId != -1) {
            contasViewModel.deleteConta(contaId);
            finish();
        }
    }
}