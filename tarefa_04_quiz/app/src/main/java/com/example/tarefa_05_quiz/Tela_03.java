package com.example.tarefa_05_quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import funcoes_para_chamar.FuncoesUteis;

public class Tela_03 extends AppCompatActivity {

    RadioButton rbQuestaoA, rbQuestaoB, rbQuestaoC, rbQuestaoD;
    Button btValidar, btProximoQ03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela03);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FuncoesUteis funcoes_uteis = new FuncoesUteis(this);
        //Acima o this se refere à instância atual da Tela_01, que é uma subclasse de AppCompatActivity.

        rbQuestaoA = findViewById(R.id.rbQuestaoA);
        rbQuestaoB = findViewById(R.id.rbQuestaoB);
        rbQuestaoC = findViewById(R.id.rbQuestaoC);
        rbQuestaoD = findViewById(R.id.rbQuestaoD);
        btValidar = findViewById(R.id.btValidar);
        btProximoQ03 = findViewById(R.id.btProximoQ03);

        //DESATIVANDO ALTERNATIVAS
        rbQuestaoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.desativarB_C_D(rbQuestaoB,rbQuestaoC,rbQuestaoD);
            }
        });
        rbQuestaoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.desativarA_C_D(rbQuestaoA,rbQuestaoC, rbQuestaoD);
            }
        });
        rbQuestaoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.desativarA_B_D(rbQuestaoA,rbQuestaoB, rbQuestaoD);
            }
        });
        rbQuestaoD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcoes_uteis.desativarA_B_C(rbQuestaoA,rbQuestaoB, rbQuestaoC);
            }
        });

        btValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbQuestaoA.isChecked()){
                    funcoes_uteis.respostaErrada();
                    rbQuestaoA.setTextColor(Color.RED);
                    Resultados.erros++;

                }
                else if (rbQuestaoB.isChecked()){
                    funcoes_uteis.respostaCerta();
                    rbQuestaoB.setTextColor(Color.GREEN);
                    Resultados.acertos++;
                }
                else if (rbQuestaoC.isChecked()){
                    funcoes_uteis.respostaErrada();
                    rbQuestaoC.setTextColor(Color.RED);
                    Resultados.erros++;
                }
                else if (rbQuestaoD.isChecked()){
                    funcoes_uteis.respostaErrada();
                    rbQuestaoD.setTextColor(Color.RED);
                    Resultados.erros++;
                }
            }
        });

        btProximoQ03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rbQuestaoA.isChecked() && !rbQuestaoB.isChecked() && !rbQuestaoC.isChecked() && !rbQuestaoD.isChecked()){
                    //btProximoQ01.setEnabled(false);
                    Toast.makeText(Tela_03.this, "Selecione uma alternativa!",Toast.LENGTH_LONG).show();
                }
                else{
                    //btProximoQ01.setEnabled(true);
                    funcoes_uteis.proximaTela(Tela_04.class);
                }

            }
        });

    }
}