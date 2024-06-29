package com.example.finance.view.contas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.databinding.FragmentContasBinding;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.view.home.HomeViewModel;

public class ContasFragment extends Fragment {

    private FragmentContasBinding binding;
    private ContasViewModel contasViewModel;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding.btnCadastrarConta.setOnClickListener(v -> cadastrarConta());

        return root;
    }

    private void cadastrarConta() {
        String nomeBanco = binding.edtNomeBanco.getText().toString().trim();
        String saldoString = binding.edtSaldo.getText().toString().trim();

        if (nomeBanco.isEmpty() || saldoString.isEmpty()) {
            Toast.makeText(getContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        double saldo;
        try {
            saldo = Double.parseDouble(saldoString);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Saldo inválido.", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = homeViewModel.getUser().getValue().getUserId();
        ContaBancaria conta = new ContaBancaria(userId, nomeBanco, saldo);
        contasViewModel.insertConta(conta);

        Toast.makeText(getContext(), "Conta cadastrada com sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}