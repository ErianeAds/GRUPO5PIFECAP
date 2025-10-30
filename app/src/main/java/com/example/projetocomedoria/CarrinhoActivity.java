package com.example.projetocomedoria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocomedoria.adapters.CarrinhoAdapter;
import com.example.projetocomedoria.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCarrinho;
    private CarrinhoAdapter carrinhoAdapter;
    private List<Produto> listaCarrinho;
    private TextView txtTotal;
    private Button btnFinalizarPedido, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        inicializarComponentes();
        carregarCarrinho();
        configurarRecyclerView();
        configurarListeners();
        calcularTotal();
    }

    private void inicializarComponentes() {
        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho);
        txtTotal = findViewById(R.id.txtTotal);
        btnFinalizarPedido = findViewById(R.id.btnFinalizarPedido);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void carregarCarrinho() {
        listaCarrinho = new ArrayList<>();
        // Dados exemplo - depois virão do banco
        listaCarrinho.add(new Produto("1", "X-Burger", "Pão, hambúrguer, queijo", 12.90, "", "Lanches"));
        listaCarrinho.add(new Produto("4", "Suco Natural", "Suco de laranja", 8.00, "", "Bebidas"));
    }

    private void configurarRecyclerView() {
        carrinhoAdapter = new CarrinhoAdapter(listaCarrinho);
        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrinho.setAdapter(carrinhoAdapter);
    }

    private void configurarListeners() {
        btnFinalizarPedido.setOnClickListener(v -> {
            // Implementar finalização do pedido depois
        });

        btnVoltar.setOnClickListener(v -> {
            finish(); // Volta para a MainActivity
        });
    }

    private void calcularTotal() {
        double total = 0;
        for (Produto produto : listaCarrinho) {
            total += produto.getPreco();
        }
        txtTotal.setText(String.format("Total: R$ %.2f", total));
    }
}