package com.example.tarefa_05_quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import funcoes_para_chamar.FuncoesUteis;

public class MainActivity extends AppCompatActivity {

    Button btIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FuncoesUteis funcoes_uteis = new FuncoesUteis(this);

        btIniciar = findViewById(R.id.btIniciar);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.proximaTela(Tela_01.class);
            }
        });

    }
}
/*Respostas:
1 - b) Cria um novo array com os resultados de uma função aplicada a cada elemento.
2 - a) "object"
3 - b) Compara valores e tipos.
4 - c) const x = 10;
5 - b) Adiciona um ou mais elementos ao final de um array.
6 - b) "53"
7 - b) Executa uma função após um determinado tempo.
8 - d) Todas as alternativas estão corretas.
9 - a) Cria um novo array com todos os elementos que passam em um teste.
10 - b) true
*/