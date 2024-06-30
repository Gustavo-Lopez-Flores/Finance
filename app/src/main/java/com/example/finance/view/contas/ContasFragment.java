package com.example.finance.view.contas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.databinding.FragmentContasBinding;
import com.example.finance.entities.ContaBancaria;
import com.example.finance.view.contas.ContasActivity;

import java.util.List;

public class ContasFragment extends Fragment {

    private FragmentContasBinding binding;
    private ContasViewModel contasViewModel;
    private ListView listViewContas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewContas = binding.listViewContas;

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        binding.btnAddConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContasActivity.class);
                startActivity(intent);
            }
        });

        preencheContas();

        return root;
    }

    private void preencheContas() {
        contasViewModel.getContas().observe(getViewLifecycleOwner(), new Observer<List<ContaBancaria>>() {
            @Override
            public void onChanged(List<ContaBancaria> contas) {
                ArrayAdapter<ContaBancaria> contasAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, contas);
                listViewContas.setAdapter(contasAdapter);

                listViewContas.setOnItemClickListener((parent, view, position, id) -> {
                    ContaBancaria contaSelecionada = contas.get(position);
                    Intent intent = new Intent(getActivity(), ContasActivity.class);
                    intent.putExtra("CONTA_SELECIONADA_ID", contaSelecionada.getId());
                    startActivity(intent);
                });
            }
        });
    }
}