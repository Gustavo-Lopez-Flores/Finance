package com.example.finance.view.limites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finance.R;
import com.example.finance.entities.LimiteGasto;

import java.util.List;

public class LimitesFragment extends Fragment {

    private LimitesViewModel limitesViewModel;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_limites, container, false);
        listView = root.findViewById(R.id.limites_list);
        root.findViewById(R.id.add_limite_button).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LimitesActivity.class);
            startActivity(intent);
        });
        limitesViewModel = new ViewModelProvider(this).get(LimitesViewModel.class);
        limitesViewModel.getLimites().observe(getViewLifecycleOwner(), this::updateList);
        return root;
    }

    private void updateList(List<LimiteGasto> limites) {
        ArrayAdapter<LimiteGasto> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_2, android.R.id.text1, limites) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);
                LimiteGasto limite = getItem(position);
                text1.setText("Categoria ID: " + limite.getCategoriaId() + " - Valor Limite: " + limite.getValorLimite());
                text2.setText("Usuário ID: " + limite.getUsuarioId());
                view.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), LimitesActivity.class);
                    intent.putExtra("limiteId", limite.getId());
                    startActivity(intent);
                });
                return view;
            }
        };
        listView.setAdapter(adapter);
    }
}