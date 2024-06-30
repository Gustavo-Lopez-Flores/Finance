package com.example.finance.view.categoria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.Categoria;
import com.example.finance.view.categoria.CategoriaViewModel;

public class CategoriaActivity extends AppCompatActivity {

    private CategoriaViewModel categoriaViewModel;
    private EditText editTextNomeCategoria;
    private int userId;
    private int categoriaSelecionadaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        editTextNomeCategoria = findViewById(R.id.edtNomeCategoria);
        Button btnSave = findViewById(R.id.btnSalvarCategoria);
        Button btnDelete = findViewById(R.id.btnExcluirCategoria);

        userId = getIntent().getIntExtra("USER_ID", -1);
        categoriaSelecionadaId = getIntent().getIntExtra("CATEGORIA_SELECIONADA_ID", -1);

        if (categoriaSelecionadaId != -1) {
            categoriaViewModel.getCategoriaById(categoriaSelecionadaId).observe(this, categoria -> {
                if (categoria != null) {
                    editTextNomeCategoria.setText(categoria.getNomeCategoria());
                }
            });
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> {
            String nomeCategoria = editTextNomeCategoria.getText().toString();

            Categoria categoria = new Categoria(userId, nomeCategoria);

            if (categoriaSelecionadaId == -1) {
                categoriaViewModel.addCategoria(categoria);
            } else {
                categoria.setId(categoriaSelecionadaId);
                categoriaViewModel.updateCategoria(categoria);
            }

            finish();
        });

        btnDelete.setOnClickListener(v -> {
            if (categoriaSelecionadaId != -1) {
                categoriaViewModel.deleteCategoria(categoriaSelecionadaId);
                finish();
            }
        });
    }
}