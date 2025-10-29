package com.example.projetocomedoria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText edtNome, edtSobrenome, edtCelular, edtCPF, edtEmail, edtSenha;
    private Button btnCadastrar;
    private TextView txtVoltarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        edtNome = findViewById(R.id.edtNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtCelular = findViewById(R.id.edtCelular);
        edtCPF = findViewById(R.id.edtCPF);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtVoltarLogin = findViewById(R.id.txtVoltarLogin);
    }

    private void configurarListeners() {
        btnCadastrar.setOnClickListener(v -> realizarCadastro());

        txtVoltarLogin.setOnClickListener(v -> {
            voltarParaLogin();
        });
    }

    private void realizarCadastro() {
        String nome = edtNome.getText().toString().trim();
        String sobrenome = edtSobrenome.getText().toString().trim();
        String celular = edtCelular.getText().toString().trim();
        String cpf = edtCPF.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();

        // Validação básica
        if (nome.isEmpty() || sobrenome.isEmpty() || celular.isEmpty() ||
                cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isEmailValido(email)) {
            Toast.makeText(this, "Digite um e-mail válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (senha.length() < 6) {
            Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

        new android.os.Handler().postDelayed(
                () -> voltarParaLogin(),
                1500
        );
    }

    private boolean isEmailValido(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void voltarParaLogin() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}