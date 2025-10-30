package com.example.projetocomedoria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RecargaActivity extends AppCompatActivity {

    private Button btnVoltar, btnValor10, btnValor20, btnValor50, btnConfirmarRecarga;
    private TextInputEditText edtValorPersonalizado;
    private double valorSelecionado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);

        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        btnVoltar = findViewById(R.id.btnVoltar);
        btnValor10 = findViewById(R.id.btnValor10);
        btnValor20 = findViewById(R.id.btnValor20);
        btnValor50 = findViewById(R.id.btnValor50);
        btnConfirmarRecarga = findViewById(R.id.btnConfirmarRecarga);
        edtValorPersonalizado = findViewById(R.id.edtValorPersonalizado);
    }

    private void configurarListeners() {
        btnVoltar.setOnClickListener(v -> {
            finish(); // Volta para a CarteiraActivity
        });

        // Botões de valor fixo
        btnValor10.setOnClickListener(v -> selecionarValor(10.00));
        btnValor20.setOnClickListener(v -> selecionarValor(20.00));
        btnValor50.setOnClickListener(v -> selecionarValor(50.00));

        btnConfirmarRecarga.setOnClickListener(v -> {
            confirmarRecarga();
        });
    }

    private void selecionarValor(double valor) {
        valorSelecionado = valor;
        edtValorPersonalizado.setText(String.valueOf(valor));

        // Resetar cores de todos os botões
        resetarCoresBotoes();

        // Destacar botão selecionado
        if (valor == 10.00) {
            btnValor10.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF2E7D32));
            btnValor10.setTextColor(0xFFFFFFFF);
        } else if (valor == 20.00) {
            btnValor20.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF2E7D32));
            btnValor20.setTextColor(0xFFFFFFFF);
        } else if (valor == 50.00) {
            btnValor50.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF2E7D32));
            btnValor50.setTextColor(0xFFFFFFFF);
        }
    }

    private void resetarCoresBotoes() {
        btnValor10.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFFFFFFF));
        btnValor10.setTextColor(0xFF2E7D32);

        btnValor20.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFFFFFFF));
        btnValor20.setTextColor(0xFF2E7D32);

        btnValor50.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFFFFFFFF));
        btnValor50.setTextColor(0xFF2E7D32);
    }

    private void confirmarRecarga() {
        // Verificar se tem valor personalizado
        String valorPersonalizado = edtValorPersonalizado.getText().toString().trim();

        if (!valorPersonalizado.isEmpty()) {
            try {
                valorSelecionado = Double.parseDouble(valorPersonalizado);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Digite um valor válido", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (valorSelecionado <= 0) {
            Toast.makeText(this, "Selecione ou digite um valor", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valorSelecionado < 5) {
            Toast.makeText(this, "Valor mínimo: R$ 5,00", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simular recarga bem-sucedida
        Toast.makeText(this, "Recarga de R$ " + valorSelecionado + " realizada com sucesso!", Toast.LENGTH_LONG).show();

        // Voltar para a carteira após 2 segundos
        new android.os.Handler().postDelayed(() -> {
            Intent intent = new Intent();
            intent.putExtra("valorRecarregado", valorSelecionado);
            setResult(RESULT_OK, intent);
            finish();
        }, 2000);
    }
}