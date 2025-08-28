package com.example.tarefa_02;

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

public class Tela_Principal extends AppCompatActivity {

    Button btExe01,btExe02,btExe03, btExe04;
    Button btExe05, btExe06, btExe07;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_principal);

        btExe01 = findViewById(R.id.btExe01);
        btExe02 = findViewById(R.id.btExe02);
        btExe03 = findViewById(R.id.btExe03);
        btExe04 = findViewById(R.id.btExe04);
        btExe05 = findViewById(R.id.btExe05);
        btExe06 = findViewById(R.id.btExe06);
        btExe07 = findViewById(R.id.btExe07);

        btExe01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_01.class);
                startActivity(intent);
                }
            });

        btExe02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_02.class);
                startActivity(intent);
                }
            });
        btExe03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_03.class);
                startActivity(intent);
                }
            });
        btExe04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_04.class);
                startActivity(intent);
                }
            });
  btExe05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_05.class);
                startActivity(intent);
                }
            });
  btExe06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_06.class);
                startActivity(intent);
                }
            });
  btExe07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this,Exer_07.class);
                startActivity(intent);
                }
            });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}