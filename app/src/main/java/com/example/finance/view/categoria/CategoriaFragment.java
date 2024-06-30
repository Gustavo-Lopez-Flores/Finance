package com.example.finance.view.categoria;

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
import com.example.finance.entities.Categoria;
import com.example.finance.view.categoria.CategoriaViewModel;

import java.util.List;

public class CategoriaFragment extends Fragment {

    private CategoriaViewModel categoriaViewModel;
    private ListView listViewCategorias;
    private ArrayAdapter<Categoria> categoriasAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        Button btnAdicionarCategoria = view.findViewById(R.id.btnAddCategoria);
        listViewCategorias = view.findViewById(R.id.listViewCategorias);

        categoriasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        listViewCategorias.setAdapter(categoriasAdapter);

        preencheCategorias();

        btnAdicionarCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.example.finance.view.categoria.CategoriaActivity.class);
            Bundle args = getArguments();
            if (args != null) {
                int userId = args.getInt("userId", -1);
                intent.putExtra("USER_ID", userId);
            }
            startActivity(intent);
        });

        listViewCategorias.setOnItemClickListener((parent, view1, position, id) -> {
            Categoria categoriaSelecionada = categoriasAdapter.getItem(position);
            if (categoriaSelecionada != null) {
                Intent intent = new Intent(getActivity(), com.example.finance.view.categoria.CategoriaActivity.class);
                intent.putExtra("CATEGORIA_SELECIONADA_ID", categoriaSelecionada.getId());
                intent.putExtra("USER_ID", categoriaSelecionada.getUsuarioId());
                startActivity(intent);
            }
        });

        return view;
    }

    private void preencheCategorias() {
        categoriaViewModel.getCategorias().observe(getViewLifecycleOwner(), new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categoriasList) {
                categoriasAdapter.clear();
                categoriasAdapter.addAll(categoriasList);
                categoriasAdapter.notifyDataSetChanged();
            }
        });
    }
}