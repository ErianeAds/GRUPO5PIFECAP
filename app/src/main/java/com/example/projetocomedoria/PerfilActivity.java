package com.example.projetocomedoria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtNome, txtEmail, txtMatricula;
    private Button btnVoltar, btnEditarPerfil, btnCarteira; // Adicione btnCarteira aqui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();
        configurarListeners();
        carregarDadosUsuario();
    }

    private void inicializarComponentes() {
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtMatricula = findViewById(R.id.txtMatricula);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnEditarPerfil = findViewById(R.id.btnEditarPerfil);
        btnCarteira = findViewById(R.id.btnCarteira); // Agora está correto
    }

    private void carregarDadosUsuario() {
        // Dados mock - depois virão do banco
        txtNome.setText("João Silva");
        txtEmail.setText("joao.silva@fecap.com");
        txtMatricula.setText("20230001");
    }

    private void configurarListeners() {
        btnVoltar.setOnClickListener(v -> {
            finish(); // Volta para a MainActivity
        });

        btnEditarPerfil.setOnClickListener(v -> {
            // Implementar edição de perfil depois
        });

        btnCarteira.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, CarteiraActivity.class);
            startActivity(intent);
        });
    }
}