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
            // Handle confirm action
        });

        buttonRemove.setOnClickListener(v -> {
            // Handle remove action
        });

        // Additional logic to check if updating or adding a new transaction
    }
}