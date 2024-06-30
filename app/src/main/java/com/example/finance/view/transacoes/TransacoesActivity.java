package com.example.finance.view.transacoes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.Categoria;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.view.transacoes.TransacoesViewModel;

import java.util.List;

public class TransacoesActivity extends AppCompatActivity {

    private TransacoesViewModel transacoesViewModel;
    private Spinner categoriaSpinner;
    private EditText valorEditText;
    private EditText dataEditText;
    private EditText descricaoEditText;
    private Button confirmarButton;
    private Button removerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);

        transacoesViewModel = new ViewModelProvider(this).get(TransacoesViewModel.class);

        categoriaSpinner = findViewById(R.id.categoria_spinner);
        valorEditText = findViewById(R.id.valor_edit_text);
        dataEditText = findViewById(R.id.data_edit_text);
        descricaoEditText = findViewById(R.id.descricao_edit_text);
        confirmarButton = findViewById(R.id.confirmar_button);
        removerButton = findViewById(R.id.remover_button);

        transacoesViewModel.getCategorias().observe(this, categorias -> {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoriaSpinner.setAdapter(adapter);
        });

        confirmarButton.setOnClickListener(v -> {
            int categoriaId = ((Categoria) categoriaSpinner.getSelectedItem()).getId();
            double valor = Double.parseDouble(valorEditText.getText().toString());
            String data = dataEditText.getText().toString();
            String descricao = descricaoEditText.getText().toString();

            TransacaoFinanceira transacao = new TransacaoFinanceira(1, categoriaId, valor, data, descricao); // Assumindo que contaId é 1
            transacoesViewModel.insertTransacao(transacao);
            finish();
        });

        removerButton.setOnClickListener(v -> {
            // Código para remover a transação, se aplicável
        });

        // Configurar a Activity de acordo com a ação (adicionar/atualizar)
        // ...
    }
}