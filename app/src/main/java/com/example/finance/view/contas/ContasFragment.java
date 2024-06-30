package com.example.finance.view.contas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.ContaBancaria;

import java.util.List;

public class ContasFragment extends Fragment {

    private ContasViewModel contasViewModel;
    private ListView listViewContas;
    private ArrayAdapter<ContaBancaria> contasAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contas, container, false);

        contasViewModel = new ViewModelProvider(this).get(ContasViewModel.class);

        Button btnAdicionarConta = view.findViewById(R.id.btnAddConta);
        listViewContas = view.findViewById(R.id.listViewContas);

        contasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listViewContas.setAdapter(contasAdapter);

        preencheContas();

        btnAdicionarConta.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContasActivity.class);
            Bundle args = getArguments();
            if (args != null) {
                int userId = args.getInt("userId", -1);
                intent.putExtra("USER_ID", userId);
            }
            startActivity(intent);
        });

        listViewContas.setOnItemClickListener((parent, view1, position, id) -> {
            ContaBancaria contaSelecionada = contasAdapter.getItem(position);
            if (contaSelecionada != null) {
                Intent intent = new Intent(getActivity(), ContasActivity.class);
                intent.putExtra("CONTA_SELECIONADA_ID", contaSelecionada.getId());
                intent.putExtra("USER_ID", contaSelecionada.getUsuarioId());
                startActivity(intent);
            }
        });

        return view;
    }

    private void preencheContas() {
        contasViewModel.getContas().observe(getViewLifecycleOwner(), new Observer<List<ContaBancaria>>() {
            @Override
            public void onChanged(List<ContaBancaria> contasList) {
                contasAdapter.clear();
                contasAdapter.addAll(contasList);
                contasAdapter.notifyDataSetChanged();
            }
        });
    }
}