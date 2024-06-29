package com.example.finance.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.databinding.FragmentHomeBinding;
import com.example.finance.entities.User;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        Bundle args = getArguments();
        if (args != null) {
            int userId = args.getInt("userId", -1);
            if (userId != -1) {
                homeViewModel.carregarUser(userId);
            }
        }

        homeViewModel.getUser().observe(getViewLifecycleOwner(), this::atualizarUI);

        return root;
    }

    private void atualizarUI(User user) {
        if (user != null) {
            binding.txtUsuario.setText(user.getNome());
            binding.textName.setText(user.getNome());
            binding.textEmail.setText(user.getEmail());

            if (user.getFotoPerfil() != null && !user.getFotoPerfil().isEmpty()) {
                Picasso.get().load(user.getFotoPerfil()).into(binding.imageUser);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}