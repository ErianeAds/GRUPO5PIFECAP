package com.example.projetocomedoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocomedoria.adapters.ProdutoAdapter;
import com.example.projetocomedoria.model.Produto;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> listaProdutos;
    private TextInputLayout edtBusca;
    private LinearLayout layoutCategorias;
    private Button btnPerfil, btnHome, btnCarrinho, btnPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        carregarProdutos();
        configurarCategorias();
        configurarRecyclerView();
        configurarListeners();
    }

    private void inicializarComponentes() {
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        edtBusca = findViewById(R.id.edtBusca);
        layoutCategorias = findViewById(R.id.layoutCategorias);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnHome = findViewById(R.id.btnHome);
        btnCarrinho = findViewById(R.id.btnCarrinho);
        btnPedidos = findViewById(R.id.btnPedidos);
    }

    private void carregarProdutos() {
        listaProdutos = new ArrayList<>();

        listaProdutos.add(new Produto("1", "X-Burger", "Pão, hambúrguer, queijo, alface, tomate", 12.90, "", "Lanches"));
        listaProdutos.add(new Produto("2", "X-Bacon", "Pão, hambúrguer, queijo, bacon", 15.90, "", "Lanches"));
        listaProdutos.add(new Produto("3", "Café Expresso", "Café espresso tradicional", 4.50, "", "Bebidas"));
        listaProdutos.add(new Produto("4", "Cappuccino", "Café com leite vaporizado e chocolate", 8.90, "", "Bebidas"));
        listaProdutos.add(new Produto("5", "Salada Caesar", "Alface, croutons, parmesão, molho caesar", 18.90, "", "Saladas"));
        listaProdutos.add(new Produto("6", "Suco Natural", "Suco de laranja natural", 8.00, "", "Bebidas"));
        listaProdutos.add(new Produto("7", "Brownie", "Brownie de chocolate com nuts", 9.90, "", "Sobremesas"));
        listaProdutos.add(new Produto("8", "Mousse de Chocolate", "Mousse cremoso de chocolate", 7.50, "", "Sobremesas"));
    }

    private void configurarCategorias() {
        String[] categorias = {"Todos", "Lanches", "Bebidas", "Saladas", "Sobremesas"};

        for (String categoria : categorias) {
            Button btnCategoria = new Button(this);
            btnCategoria.setText(categoria);

            // CORES DIRETAS - sem recursos personalizados
            btnCategoria.setBackgroundColor(0xFFFFFFFF); // Branco
            btnCategoria.setTextColor(0xFF2E7D32); // Verde escuro
            btnCategoria.setPadding(24, 12, 24, 12);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 8, 0);
            btnCategoria.setLayoutParams(params);

            btnCategoria.setOnClickListener(v -> {
                filtrarPorCategoria(categoria);

                // Mudar cor quando selecionado (opcional)
                for (int i = 0; i < layoutCategorias.getChildCount(); i++) {
                    Button btn = (Button) layoutCategorias.getChildAt(i);
                    btn.setBackgroundColor(0xFFFFFFFF); // Todos brancos
                    btn.setTextColor(0xFF2E7D32); // Todos verdes
                }

                // Botão selecionado fica verde com texto branco
                btnCategoria.setBackgroundColor(0xFF2E7D32); // Verde
                btnCategoria.setTextColor(0xFFFFFFFF); // Branco
            });

            layoutCategorias.addView(btnCategoria);
        }
    }

    private void filtrarPorCategoria(String categoria) {
        if (categoria.equals("Todos")) {
            produtoAdapter.atualizarLista(listaProdutos);
        } else {
            List<Produto> listaFiltrada = new ArrayList<>();
            for (Produto produto : listaProdutos) {
                if (produto.getCategoria().equals(categoria)) {
                    listaFiltrada.add(produto);
                }
            }
            produtoAdapter.atualizarLista(listaFiltrada);
        }
    }

    private void configurarRecyclerView() {
        produtoAdapter = new ProdutoAdapter(listaProdutos, this::onAddToCarrinhoClick);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProdutos.setAdapter(produtoAdapter);
    }

    private void onAddToCarrinhoClick(Produto produto) {
        Toast.makeText(this, produto.getNome() + " adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
    }

    private void configurarListeners() {
        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        btnCarrinho.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CarrinhoActivity.class);
            startActivity(intent);
        });

        btnPedidos.setOnClickListener(v -> {
            Toast.makeText(this, "Histórico de pedidos em desenvolvimento", Toast.LENGTH_SHORT).show();
        });
    }
}