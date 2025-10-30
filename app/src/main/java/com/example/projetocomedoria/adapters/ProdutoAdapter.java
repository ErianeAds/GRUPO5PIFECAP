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

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> listaProdutos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddToCarrinhoClick(Produto produto);
    }

    public ProdutoAdapter(List<Produto> listaProdutos, OnItemClickListener listener) {
        this.listaProdutos = listaProdutos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position);
        holder.bind(produto, listener);
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public void atualizarLista(List<Produto> novaLista) {
        this.listaProdutos = novaLista;
        notifyDataSetChanged();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNomeProduto, txtDescricao, txtPreco;
        private Button btnAdicionar;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeProduto = itemView.findViewById(R.id.txtNomeProduto);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            btnAdicionar = itemView.findViewById(R.id.btnAdicionar);
        }

        public void bind(Produto produto, OnItemClickListener listener) {
            txtNomeProduto.setText(produto.getNome());
            txtDescricao.setText(produto.getDescricao());
            txtPreco.setText(String.format("R$ %.2f", produto.getPreco()));

            btnAdicionar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAddToCarrinhoClick(produto);
                }
            });
        }
    }
}
