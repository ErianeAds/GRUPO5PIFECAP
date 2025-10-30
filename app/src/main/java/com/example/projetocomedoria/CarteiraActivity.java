package com.example.projetocomedoria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocomedoria.adapters.TransacaoAdapter;
import com.example.projetocomedoria.model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class CarteiraActivity extends AppCompatActivity {

    private TextView txtSaldo;
    private Button btnRecarregar, btnVoltar;
    private RecyclerView recyclerViewTransacoes;
    private TransacaoAdapter transacaoAdapter;
    private List<Transacao> listaTransacoes;
    private double saldo = 50.00; // Saldo inicial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        inicializarComponentes();
        carregarTransacoes();
        configurarRecyclerView();
        configurarListeners();
        atualizarSaldo();
    }

    private void inicializarComponentes() {
        txtSaldo = findViewById(R.id.txtSaldo);
        btnRecarregar = findViewById(R.id.btnRecarregar);
        btnVoltar = findViewById(R.id.btnVoltar);
        recyclerViewTransacoes = findViewById(R.id.recyclerViewTransacoes);
    }

    private void carregarTransacoes() {
        listaTransacoes = new ArrayList<>();

        // Transações exemplo
        listaTransacoes.add(new Transacao("Recarga", "15/10/2024", "+ R$ 20,00", "#2E7D32"));
        listaTransacoes.add(new Transacao("Compra - X-Burger", "14/10/2024", "- R$ 12,90", "#FF0000"));
        listaTransacoes.add(new Transacao("Recarga", "10/10/2024", "+ R$ 30,00", "#2E7D32"));
        listaTransacoes.add(new Transacao("Compra - Café", "09/10/2024", "- R$ 4,50", "#FF0000"));
    }

    private void configurarRecyclerView() {
        transacaoAdapter = new TransacaoAdapter(listaTransacoes);
        recyclerViewTransacoes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTransacoes.setAdapter(transacaoAdapter);
    }

    private void configurarListeners() {
        btnVoltar.setOnClickListener(v -> {
            finish(); // Volta para a tela anterior
        });

        btnRecarregar.setOnClickListener(v -> {
            // Abrir tela de recarga usando startActivityForResult
            Intent intent = new Intent(CarteiraActivity.this, RecargaActivity.class);
            startActivityForResult(intent, 1); // Código de requisição 1
        });
    }

    private void atualizarSaldo() {
        txtSaldo.setText(String.format("R$ %.2f", saldo));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verificar se é o resultado da RecargaActivity
        if (requestCode == 1 && resultCode == RESULT_OK) {
            double valorRecarregado = data.getDoubleExtra("valorRecarregado", 0);
            if (valorRecarregado > 0) {
                // Atualizar o saldo
                saldo += valorRecarregado;
                atualizarSaldo();

                // Adicionar nova transação
                listaTransacoes.add(0, new Transacao("Recarga", "Hoje", "+ R$ " + valorRecarregado, "#2E7D32"));
                transacaoAdapter.notifyItemInserted(0);

                Toast.makeText(this, "Saldo atualizado!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}