package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_06_Lista_Passageiros extends AppCompatActivity {

    private LinearLayout containerPassageiros;
    private RadioGroup radioGroupPagamento;
    private Button btnConfirmarPagamento;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela06_lista_passageiros);

        preferences = getSharedPreferences("PassageirosPref", MODE_PRIVATE);

        containerPassageiros = findViewById(R.id.containerPassageiros);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
        btnConfirmarPagamento = findViewById(R.id.btnConfirmarPagamento);

        carregarPassageiros();

        btnConfirmarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupPagamento.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(Tela_06_Lista_Passageiros.this,
                            "Selecione uma forma de pagamento!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String formaPagamento = "";
                if (selectedId == R.id.radioPix) {
                    formaPagamento = "PIX";
                } else if (selectedId == R.id.radioCartao) {
                    formaPagamento = "Cartão de Crédito";
                } else if (selectedId == R.id.radioTransferencia) {
                    formaPagamento = "Transferência Bancária";
                }

                // Salvar forma de pagamento
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("forma_pagamento", formaPagamento);
                editor.apply();

                Intent intent = new Intent(Tela_06_Lista_Passageiros.this,
                        Tela_07_QrCode.class);
                startActivity(intent);
            }
        });


        private void carregarPassageiros () {
            int passageiroCount = preferences.getInt("passageiro_count", 0);

            for (int i = 0; i < passageiroCount; i++) {
                String nome = preferences.getString("nome_" + i, "");
                String documento = preferences.getString("documento_" + i, "");

                CardView cardView = new CardView(this);
                CardView.LayoutParams cardParams = new CardView.LayoutParams(
                        CardView.LayoutParams.MATCH_PARENT,
                        CardView.LayoutParams.WRAP_CONTENT
                );
                cardParams.setMargins(0, 0, 0, 8);
                cardView.setLayoutParams(cardParams);
                cardView.setCardElevation(4);
                cardView.setRadius(4);

                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(16, 16, 16, 16);

                TextView tvNome = new TextView(this);
                tvNome.setText("Nome: " + nome);
                tvNome.setTextSize(14);

                TextView tvDocumento = new TextView(this);
                tvDocumento.setText("Documento: " + documento);
                tvDocumento.setTextSize(12);
                tvDocumento.setTextColor(Color.GRAY);

                layout.addView(tvNome);
                layout.addView(tvDocumento);
                cardView.addView(layout);

                containerPassageiros.addView(cardView);
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(

    findViewById(R.id.main), (v,insets)->

    {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });
 }
}