package com.example.finance.view.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.databinding.ActivityUserUpdateBinding;
import com.example.finance.entities.User;
import com.example.finance.view.home.HomeViewModel;
import com.squareup.picasso.Picasso;

public class UserUpdateActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityUserUpdateBinding binding;
    private HomeViewModel homeViewModel;
    private User user;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        int userId = getIntent().getIntExtra("userId", -1);
        if (userId != -1) {
            homeViewModel.carregarUser(userId);
        }

        homeViewModel.getUser().observe(this, this::atualizarUI);

        binding.btnConfirmar.setOnClickListener(v -> {
            if (user != null) {
                user.setEmail(binding.edtEmail.getText().toString());
                user.setSenha(binding.edtSenha.getText().toString());
                if (selectedImageUri != null) {
                    user.setFotoPerfil(selectedImageUri.toString());
                }
                homeViewModel.updateUser(user);
                finish();
            }
        });

        binding.btnVoltar.setOnClickListener(v -> finish());

        binding.btnSelecionarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            Picasso.get().load(selectedImageUri).into(binding.imgFotoPerfil);
        }
    }

    private void atualizarUI(User user) {
        this.user = user;
        if (user != null) {
            binding.edtEmail.setText(user.getEmail());
            binding.edtSenha.setText(user.getSenha());
            binding.edtConfirmaSenha.setText(user.getSenha());
            if (user.getFotoPerfil() != null && !user.getFotoPerfil().isEmpty()) {
                Picasso.get().load(user.getFotoPerfil()).into(binding.imgFotoPerfil);
            }
        }
    }
}