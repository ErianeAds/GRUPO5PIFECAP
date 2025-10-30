package com.example.projetocomedoria.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocomedoria.R;
import com.example.projetocomedoria.model.Produto;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private List<Produto> listaCarrinho;

    public CarrinhoAdapter(List<Produto> listaCarrinho) {
        this.listaCarrinho = listaCarrinho;
    }

    @NonNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        Produto produto = listaCarrinho.get(position);
        holder.bind(produto);
    }

    @Override
    public int getItemCount() {
        return listaCarrinho.size();
    }

    static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNomeProduto, txtPreco;
        private Button btnRemover;

        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeProduto = itemView.findViewById(R.id.txtNomeProduto);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            btnRemover = itemView.findViewById(R.id.btnRemover);
        }

        public void bind(Produto produto) {
            txtNomeProduto.setText(produto.getNome());
            txtPreco.setText(String.format("R$ %.2f", produto.getPreco()));

            btnRemover.setOnClickListener(v -> {
                // Implementar remoção do carrinho depois
            });
        }
    }
}