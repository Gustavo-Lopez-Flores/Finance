package com.example.finance.view.transacoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.TransacaoFinanceira;
import com.example.finance.view.transacoes.TransacoesViewModel;

public class TransacoesFragment extends Fragment {

    private TransacoesViewModel transacoesViewModel;
    private ListView listView;
    private Button adicionarButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transacoes, container, false);

        listView = root.findViewById(R.id.transacoes_list_view);
        adicionarButton = root.findViewById(R.id.adicionar_transacao_button);

        transacoesViewModel = new ViewModelProvider(this).get(TransacoesViewModel.class);

        transacoesViewModel.getTransacoes().observe(getViewLifecycleOwner(), transacoes -> {
            ArrayAdapter<TransacaoFinanceira> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, transacoes);
            listView.setAdapter(adapter);
        });

        adicionarButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TransacoesActivity.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            TransacaoFinanceira transacao = (TransacaoFinanceira) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), TransacoesActivity.class);
            intent.putExtra("transacaoId", transacao.getId());
            startActivity(intent);
        });

        return root;
    }
}