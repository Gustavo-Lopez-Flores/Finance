package com.example.finance.view.transacoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.TransacaoFinanceira;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransacoesFragment extends Fragment {

    private TransacoesViewModel viewModel;
    private ArrayAdapter<TransacaoFinanceira> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transacoes, container, false);

        ListView listView = view.findViewById(R.id.listViewTransacoes);
        FloatingActionButton fab = view.findViewById(R.id.fabAddTransacao);

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TransacoesViewModel.class);
        viewModel.getTransacoes().observe(getViewLifecycleOwner(), transacoes -> {
            adapter.clear();
            adapter.addAll(transacoes);
            adapter.notifyDataSetChanged();
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TransacoesActivity.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            TransacaoFinanceira transacao = adapter.getItem(position);
            Intent intent = new Intent(getActivity(), TransacoesActivity.class);
            intent.putExtra("TRANSACAO_ID", transacao.getId());
            startActivity(intent);
        });

        return view;
    }
}