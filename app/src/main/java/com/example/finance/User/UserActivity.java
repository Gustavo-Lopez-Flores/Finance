package com.example.finance.User;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.finance.R;
import com.example.finance.database.LocalDatabase;
import com.example.finance.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_PERMISSION = 2;

    private LocalDatabase db;
    private ActivityUserBinding binding;
    private Uri fotoPerfilUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());

    }
}