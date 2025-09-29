package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_01_Login extends AppCompatActivity {

    EditText edLogin, edSenha;
    Button btAcessar, btFinalizarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela01_login);

        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
        btAcessar = findViewById(R.id.btAcessar);
        btFinalizarr = findViewById(R.id.btFinalizarr);


        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edLogin.getText().toString();
                String senha = edSenha.getText().toString();

                // Validação simples (pode personalizar)
                if (login.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Tela_01_Login.this,
                            "Preencha login e senha!", Toast.LENGTH_SHORT).show();
                } else {
                    // Login válido, ir para tela de assentos
                    Intent intent = new Intent(Tela_01_Login.this, Tela_02_Data_Origem_Destino.class);
                    startActivity(intent);
                }
            }
        });
        btFinalizarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Fecha o app
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}