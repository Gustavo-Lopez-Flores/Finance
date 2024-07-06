package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finance.view.user.UserCreateActivity;
import com.example.finance.database.LocalDatabase;
import com.example.finance.databinding.ActivityMainBinding;
import com.example.finance.entities.User;
import com.example.finance.view.HomeActivity;

public class MainActivity extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

        binding.btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        });

        binding.btnFazerCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, UserCreateActivity.class);
                startActivity(it);
            }
        });
    }

    private void fazerLogin() {
        String email = binding.edtEmail.getText().toString().trim();
        String senha = binding.edtSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("E-mail inválido.");
            return;
        } else {
            binding.tilEmail.setError(null);
        }

        User user = db.usuarioDao().login(email, senha);
        if (user != null) {
            Toast.makeText(MainActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("FinanceApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("currentUserId", user.getUserId());
            editor.apply();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show();
        }
    }
}