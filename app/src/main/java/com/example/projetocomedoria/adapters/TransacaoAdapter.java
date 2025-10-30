package com.example.projetocomedoria.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocomedoria.R;
import com.example.projetocomedoria.model.Transacao;

import java.util.List;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> {

    private List<Transacao> listaTransacoes;

    public TransacaoAdapter(List<Transacao> listaTransacoes) {
        this.listaTransacoes = listaTransacoes;
    }

    @NonNull
    @Override
    public TransacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transacao, parent, false);
        return new TransacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoViewHolder holder, int position) {
        Transacao transacao = listaTransacoes.get(position);
        holder.bind(transacao);
    }

    @Override
    public int getItemCount() {
        return listaTransacoes.size();
    }

    static class TransacaoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDescricao, txtData, txtValor;

        public TransacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtData = itemView.findViewById(R.id.txtData);
            txtValor = itemView.findViewById(R.id.txtValor);
        }

        public void bind(Transacao transacao) {
            txtDescricao.setText(transacao.getDescricao());
            txtData.setText(transacao.getData());
            txtValor.setText(transacao.getValor());
            txtValor.setTextColor(android.graphics.Color.parseColor(transacao.getCor()));
        }
    }
}