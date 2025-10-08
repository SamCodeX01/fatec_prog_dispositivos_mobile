package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() ->{
            startActivity(new Intent(MainActivity.this, Tela_01_Login.class));
            finish();
        },4000);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

/*
* Trabalho 01

Sistema de venda de passagens rodoviárias

Requisitos Funcionais

RF01 - O sistema deve permitir que o usuário faça login
RF02 - O sistema deve permitir que o usuário escolha a origem e o destino
RF03 - O sistema deve exibir os horário de ônibus
RF04 - O sistema deve permitir que o usuário escolha a poltrona; e reserve a passagem até que a compra seja concluída ou cancelada
RF05 - O sistema deve "ocupar" a poltrona após finalizar a compra
RF06 - O sistema deve registrar os dados do usuário que irá viajar na poltrona
RF07 - O sistema deve emitir a passagem com o qrCode em PDF
RF08 - O sistema deve registrar o valor e a forma de pagamento da passagem

obs.: (1) Os dados do sistema devem ser registrados em estruturas de dados (array, lista, set, ...).
         (2) Para facilitar os testes, deixe a senha visível.
*/