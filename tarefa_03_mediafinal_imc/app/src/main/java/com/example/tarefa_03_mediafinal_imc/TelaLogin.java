package com.example.tarefa_03_mediafinal_imc;

import android.annotation.SuppressLint;
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

public class TelaLogin extends AppCompatActivity {
    EditText edLogin, edSenha;
    Button btAcessar, btFinalizarr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_login);

        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
        btAcessar = findViewById(R.id.btAcessar);
        btFinalizarr = findViewById(R.id.btFinalizarr);

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String login, senha;
                    login = edLogin.getText().toString();
                    senha = edSenha.getText().toString();

                if(login.equals("sam") && senha.equals("123")){
                    Intent intent = new Intent(TelaLogin.this, TelaMenu.class);
                    startActivity(intent);
                }
                 else{
                    Toast.makeText(TelaLogin.this, "Senha ou Login inválidos", Toast.LENGTH_SHORT).show();
                    Toast.makeText(TelaLogin.this, "Login: sam | senha: 123", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btFinalizarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}