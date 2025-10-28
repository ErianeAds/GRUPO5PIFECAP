package com.example.projetocomedoria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CarteiraActivity extends AppCompatActivity {

    private TextView txtSaldoAtual;
    private EditText edtValorRecarga;
    private Button btnRecarregar, btnVoltar;
    private double saldo = 50.0; // Mock

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        inicializarComponentes();
        atualizarSaldo();
        configurarListeners();
    }

    private void inicializarComponentes() {
        txtSaldoAtual = findViewById(R.id.txtSaldoAtual);
        edtValorRecarga = findViewById(R.id.edtValorRecarga);
        btnRecarregar = findViewById(R.id.btnRecarregar);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void atualizarSaldo() {
        txtSaldoAtual.setText(String.format("Saldo Atual: R$ %.2f", saldo));
    }

    private void configurarListeners() {
        btnRecarregar.setOnClickListener(v -> {
            String valorStr = edtValorRecarga.getText().toString();
            if (valorStr.isEmpty()) {
                Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorStr);
            if (valor <= 0) {
                Toast.makeText(this, "Valor deve ser positivo", Toast.LENGTH_SHORT).show();
                return;
            }

            saldo += valor;
            atualizarSaldo();
            edtValorRecarga.setText("");
            Toast.makeText(this, "Recarga realizada com sucesso!", Toast.LENGTH_SHORT).show();
        });

        btnVoltar.setOnClickListener(v -> {
            finish();
        });
    }
}