package com.example.finance.view.contas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.databinding.ActivityContasBinding;
import com.example.finance.entities.ContaBancaria;

public class ContasActivity extends AppCompatActivity {
    private ActivityContasBinding binding;
    private EditText edtNomeBanco;
    private EditText edtSaldo;
    private Button btnSalvar;
    private Button btnExcluir;

    private ContasViewModel contasViewModel;
    private int contaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        int userId = getIntent().getIntExtra("USER_ID", -1);
        if (userId == -1) {
            finish();
        }

        if (getIntent().hasExtra("CONTA_SELECIONADA_ID")) {
            int contaId = getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1);
            contasViewModel.getContaById(contaId).observe(this, conta -> {
                if (conta != null) {
                    binding.edtNomeBanco.setText(conta.getNomeBanco());
                    binding.edtSaldo.setText(String.valueOf(conta.getSaldo()));
                }
            });
        }

        binding.btnSalvar.setOnClickListener(v -> {
            String nomeBanco = binding.edtNomeBanco.getText().toString().trim();
            double saldo = Double.parseDouble(binding.edtSaldo.getText().toString().trim());

            ContaBancaria conta = new ContaBancaria(userId, nomeBanco, saldo);

            if (getIntent().hasExtra("CONTA_SELECIONADA_ID")) {
                conta.setId(getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1));
                contasViewModel.updateConta(conta);
            } else {
                contasViewModel.addConta(conta);
            }

            finish();
        });

        binding.btnExcluir.setOnClickListener(v -> {
            if (getIntent().hasExtra("CONTA_SELECIONADA_ID")) {
                int contaId = getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1);
                contasViewModel.deleteConta(contaId);
                finish();
            }
        });
    }

    private void salvarConta() {
        String nomeBanco = edtNomeBanco.getText().toString();
        double saldo = Double.parseDouble(edtSaldo.getText().toString());

        ContaBancaria conta = new ContaBancaria(contaId, nomeBanco, saldo);
        if (contaId == -1) {
            contasViewModel.addConta(conta);
        } else {
            contasViewModel.updateConta(conta);
        }
        finish();
    }

    private void excluirConta() {
        if (contaId != -1) {
            contasViewModel.deleteConta(contaId);
        }
        finish();
    }
}