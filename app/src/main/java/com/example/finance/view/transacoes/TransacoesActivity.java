package com.example.finance.view.transacoes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.Categoria;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.view.categoria.CategoriaViewModel;

import java.util.List;

public class TransacoesActivity extends AppCompatActivity {

    private TransacoesViewModel viewModel;
    private CategoriaViewModel categoriaViewModel;
    private Spinner spinnerCategorias;
    private EditText editTextValor, editTextData, editTextDescricao;
    private Button buttonConfirm, buttonRemove;
    private TransacaoFinanceira transacaoAtual;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);

        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        editTextValor = findViewById(R.id.editTextValor);
        editTextData = findViewById(R.id.editTextData);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonRemove = findViewById(R.id.buttonRemove);

        viewModel = new ViewModelProvider(this).get(TransacoesViewModel.class);
        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        categoriaViewModel.getCategorias().observe(this, categorias -> {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategorias.setAdapter(adapter);
        });

        buttonConfirm.setOnClickListener(v -> {
            int categoriaId = ((Categoria) spinnerCategorias.getSelectedItem()).getId();
            double valor = Double.parseDouble(editTextValor.getText().toString());
            String data = editTextData.getText().toString();
            String descricao = editTextDescricao.getText().toString();

            if (transacaoAtual == null) {
                TransacaoFinanceira novaTransacao = new TransacaoFinanceira(1, categoriaId, valor, data, descricao); // Supomos contaId = 1
                viewModel.insertTransacao(novaTransacao);
            } else {
                transacaoAtual.setCategoriaId(categoriaId);
                transacaoAtual.setValor(valor);
                transacaoAtual.setData(data);
                transacaoAtual.setDescricao(descricao);
                viewModel.updateTransacao(transacaoAtual);
            }

            finish();
        });

        buttonRemove.setOnClickListener(v -> {
            if (transacaoAtual != null) {
                viewModel.deleteTransacao(transacaoAtual);
                finish();
            }
        });

        int transacaoId = getIntent().getIntExtra("TRANSACAO_ID", -1);
        if (transacaoId != -1) {
            viewModel.getTransacaoById(transacaoId).observe(this, transacao -> {
                if (transacao != null) {
                    transacaoAtual = transacao;
                    preencherDadosTransacao(transacao);
                    buttonRemove.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void preencherDadosTransacao(TransacaoFinanceira transacao) {
        editTextValor.setText(String.valueOf(transacao.getValor()));
        editTextData.setText(transacao.getData());
        editTextDescricao.setText(transacao.getDescricao());

        ArrayAdapter<Categoria> adapter = (ArrayAdapter<Categoria>) spinnerCategorias.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == transacao.getCategoriaId()) {
                spinnerCategorias.setSelection(i);
                break;
            }
        }
    }
}
