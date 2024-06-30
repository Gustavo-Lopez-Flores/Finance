package com.example.finance.view.limites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.LimiteGasto;

public class LimitesActivity extends AppCompatActivity {

    private LimitesViewModel limitesViewModel;
    private EditText categoriaIdEditText;
    private EditText valorLimiteEditText;
    private Button confirmarButton;
    private Button removerButton;
    private LimiteGasto currentLimite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limites);

        categoriaIdEditText = findViewById(R.id.categoria_id);
        valorLimiteEditText = findViewById(R.id.valor_limite);
        confirmarButton = findViewById(R.id.confirmar_button);
        removerButton = findViewById(R.id.remover_button);

        limitesViewModel = new ViewModelProvider(this).get(LimitesViewModel.class);

        int limiteId = getIntent().getIntExtra("limiteId", -1);
        if (limiteId != -1) {
            // Load the existing limite
            currentLimite = limitesViewModel.getLimiteById(limiteId);
            categoriaIdEditText.setText(String.valueOf(currentLimite.getCategoriaId()));
            valorLimiteEditText.setText(String.valueOf(currentLimite.getValorLimite()));
            confirmarButton.setOnClickListener(v -> updateLimite());
            removerButton.setOnClickListener(v -> removeLimite());
            removerButton.setVisibility(View.VISIBLE);
        } else {
            currentLimite = null;
            confirmarButton.setOnClickListener(v -> addLimite());
            removerButton.setVisibility(View.GONE);
        }
    }

    private void updateLimite() {
        if (currentLimite != null) {
            currentLimite.setCategoriaId(Integer.parseInt(categoriaIdEditText.getText().toString()));
            currentLimite.setValorLimite(Double.parseDouble(valorLimiteEditText.getText().toString()));
            limitesViewModel.updateLimite(currentLimite);
            finish();
        }
    }

    private void addLimite() {
        LimiteGasto limite = new LimiteGasto();
        limite.setCategoriaId(Integer.parseInt(categoriaIdEditText.getText().toString()));
        limite.setValorLimite(Double.parseDouble(valorLimiteEditText.getText().toString()));
        limitesViewModel.insertLimite(limite);
        finish();
    }

    private void removeLimite() {
        if (currentLimite != null) {
            limitesViewModel.deleteLimite(currentLimite);
            finish();
        }
    }
}