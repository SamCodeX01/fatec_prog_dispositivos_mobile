package com.example.tarefa_05_quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import funcoes_para_chamar.FuncoesUteis;

public class TelaFinal extends AppCompatActivity {
    Button btInicio, btResultado;
    TextView tvAcertos, tvErros,tvMedia, tvMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_final);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FuncoesUteis funcoes_uteis = new FuncoesUteis(this);

        btResultado = findViewById(R.id.btResultado);
        btInicio = findViewById(R.id.btInicio);
        tvAcertos = findViewById(R.id.tvAcertos);
        tvErros = findViewById(R.id.tvErros);
        tvMedia = findViewById(R.id.tvMedia);
        tvMensagem = findViewById(R.id.tvMensagem);

        btResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int acertadas = Resultados.acertos;
                int erradas = Resultados.erros;
                int media = (Resultados.erros/ Resultados.acertos) * 100;

                tvAcertos.setText(String.valueOf(acertadas) + ": ACERTOS");
                tvErros.setText(String.valueOf(erradas) + ": ERROS");
                tvMedia.setText("MÉDIA: " + String.valueOf(erradas) + "%");

                if (acertadas >= 6){
                    tvMensagem.setText("APROVADO!");
                }else{
                    tvMensagem.setText("REPROVADO!");
                }
            }
        });

        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.proximaTela(MainActivity.class);
                Resultados.acertos = 0;
            }
        });
    }
}