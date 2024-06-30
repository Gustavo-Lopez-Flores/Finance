package com.example.finance.view.transacoes;

import android.os.Bundle;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);

        spinnerCategorias = findViewById(R.id.categoria_spinner);
        editTextValor = findViewById(R.id.valor_edit_text);
        editTextData = findViewById(R.id.data_edit_text);
        editTextDescricao = findViewById(R.id.descricao_edit_text);
        buttonConfirm = findViewById(R.id.confirmar_button);
        buttonRemove = findViewById(R.id.remover_button);

        viewModel = new ViewModelProvider(this).get(TransacoesViewModel.class);
        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        categoriaViewModel.getCategorias().observe(this, categorias -> {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategorias.setAdapter(adapter);
        });

        buttonConfirm.setOnClickListener(v -> {
            // Handle confirm action
        });

        buttonRemove.setOnClickListener(v -> {
            // Handle remove action
        });

        // Additional logic to check if updating or adding a new transaction
    }
}