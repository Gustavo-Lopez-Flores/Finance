package com.example.finance.view.contas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.ContaBancaria;

public class ContasActivity extends AppCompatActivity {

    private ContasViewModel contasViewModel;
    private EditText editTextNomeBanco;
    private EditText editTextSaldo;
    private int userId;
    private int contaSelecionadaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        editTextNomeBanco = findViewById(R.id.edtNomeBanco);
        editTextSaldo = findViewById(R.id.edtSaldo);
        Button btnSave = findViewById(R.id.btnSalvar);
        Button btnDelete = findViewById(R.id.btnExcluir);

        userId = getIntent().getIntExtra("USER_ID", -1);
        contaSelecionadaId = getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1);

        if (contaSelecionadaId != -1) {
            contasViewModel.getContaById(contaSelecionadaId).observe(this, conta -> {
                if (conta != null) {
                    editTextNomeBanco.setText(conta.getNomeBanco());
                    editTextSaldo.setText(String.valueOf(conta.getSaldo()));
                }
            });
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> {
            String nomeBanco = editTextNomeBanco.getText().toString();
            double saldo = Double.parseDouble(editTextSaldo.getText().toString());

            ContaBancaria conta = new ContaBancaria(userId, nomeBanco, saldo);

            if (contaSelecionadaId == -1) {
                contasViewModel.addConta(conta);
            } else {
                conta.setId(contaSelecionadaId);
                contasViewModel.updateConta(conta);
            }

            finish();
        });

        btnDelete.setOnClickListener(v -> {
            if (contaSelecionadaId != -1) {
                contasViewModel.deleteConta(contaSelecionadaId);
                finish();
            }
        });
    }
}