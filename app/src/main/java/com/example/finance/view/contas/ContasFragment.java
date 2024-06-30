package com.example.finance.view.contas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.ContaBancaria;

import java.util.List;

public class ContasFragment extends Fragment {

    private ContasViewModel contasViewModel;
    private ListView listViewContas;
    private List<ContaBancaria> contas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contas, container, false);

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        Button btnAdicionarConta = view.findViewById(R.id.btnAddConta);
        listViewContas = view.findViewById(R.id.listViewContas);

        preencheContas();

        btnAdicionarConta.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContasActivity.class);
            int userId = getArguments().getInt("userId");
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });

        listViewContas.setOnItemClickListener((parent, view1, position, id) -> {
            ContaBancaria contaSelecionada = contas.get(position);
            Intent intent = new Intent(getActivity(), ContasActivity.class);
            intent.putExtra("CONTA_SELECIONADA_ID", contaSelecionada.getId());
            intent.putExtra("USER_ID", contaSelecionada.getUsuarioId());
            startActivity(intent);
        });

        return view;
    }

    private void preencheContas() {
        contasViewModel.getContas().observe(getViewLifecycleOwner(), new Observer<List<ContaBancaria>>() {
            @Override
            public void onChanged(List<ContaBancaria> contasList) {
                contas = contasList;

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