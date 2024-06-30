package com.example.finance.view.contas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.ContaBancaria;

public class ContasActivity extends AppCompatActivity {

    private EditText edtNomeBanco;
    private EditText edtSaldo;
    private Button btnSalvar;
    private Button btnExcluir;

    private ContasViewModel contasViewModel;
    private int contaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);

        edtNomeBanco = findViewById(R.id.edtNomeBanco);
        edtSaldo = findViewById(R.id.edtSaldo);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        contaId = getIntent().getIntExtra("CONTA_SELECIONADA_ID", -1);

        if (contaId != -1) {
            contasViewModel.getContaById(contaId).observe(this, conta -> {
                if (conta != null) {
                    edtNomeBanco.setText(conta.getNomeBanco());
                    edtSaldo.setText(String.valueOf(conta.getSaldo()));
                }
            });
        }

        btnSalvar.setOnClickListener(v -> salvarConta());
        btnExcluir.setOnClickListener(v -> excluirConta());
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