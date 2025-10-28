package com.example.projetocomedoria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        txtWelcome = findViewById(R.id.txtWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        // Mensagem de boas-vindas simples
        txtWelcome.setText("Bem-vindo à Comedoria da Tia!");
    }

    private void configurarListeners() {
        btnLogout.setOnClickListener(v -> {
            // Voltar para a tela de login
            finish();
        });
    }
}