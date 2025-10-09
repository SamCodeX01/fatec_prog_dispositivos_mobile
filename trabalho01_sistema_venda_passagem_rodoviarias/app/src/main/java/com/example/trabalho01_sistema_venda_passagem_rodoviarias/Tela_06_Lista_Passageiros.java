package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "Tela06_Debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela06_lista_passageiros);

        Log.d(TAG, "onCreate: Iniciando Tela06");

        try {
            // Inicializar componentes COM VERIFICAÇÃO
            containerPassageiros = findViewById(R.id.containerPassageiros);
            radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
            btnConfirmarPagamento = findViewById(R.id.btnConfirmarPagamento);

            if (containerPassageiros == null) Log.e(TAG, "containerPassageiros é NULL");
            if (radioGroupPagamento == null) Log.e(TAG, "radioGroupPagamento é NULL");
            if (btnConfirmarPagamento == null) Log.e(TAG, "btnConfirmarPagamento é NULL");

            preferences = getSharedPreferences("PassageirosPref", MODE_PRIVATE);

            // Carregar passageiros
            carregarPassageiros();

            // Configurar clique do botão
            btnConfirmarPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão ConfirmarPagamento clicado");
                    confirmarPagamento();
                }
            });

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            Log.d(TAG, "onCreate: Tela06 configurada com sucesso");

        } catch (Exception e) {
            Log.e(TAG, "ERRO no onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Erro ao carregar tela: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void confirmarPagamento() {
        try {
            Log.d(TAG, "confirmarPagamento: Iniciando");

            if (radioGroupPagamento == null) {
                Toast.makeText(this, "Erro: Grupo de pagamento não encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = radioGroupPagamento.getCheckedRadioButtonId();
            Log.d(TAG, "Radio selecionado ID: " + selectedId);

            // Verificar se selecionou uma forma de pagamento
            if (selectedId == -1) {
                Toast.makeText(this, "Selecione uma forma de pagamento!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obter forma de pagamento selecionada
            String formaPagamento = obterFormaPagamento(selectedId);
            Log.d(TAG, "Forma de pagamento: " + formaPagamento);

            // Salvar forma de pagamento
            salvarFormaPagamento(formaPagamento);

            // Ir para tela final
            irParaTelaFinal();

        } catch (Exception e) {
            Log.e(TAG, "ERRO em confirmarPagamento: " + e.getMessage(), e);
            Toast.makeText(this, "Erro ao confirmar pagamento", Toast.LENGTH_SHORT).show();
        }
    }

    private String obterFormaPagamento(int selectedId) {
        try {
            if (selectedId == R.id.radioPix) {
                return "PIX";
            } else if (selectedId == R.id.radioCartao) {
                return "Cartão de Crédito";
            } else if (selectedId == R.id.radioTransferencia) {
                return "Transferência Bancária";
            }
            return "Não especificado";
        } catch (Exception e) {
            Log.e(TAG, "Erro em obterFormaPagamento: " + e.getMessage());
            return "Erro";
        }
    }

    private void salvarFormaPagamento(String formaPagamento) {
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("forma_pagamento", formaPagamento);
            editor.apply();

            Toast.makeText(this, "Pagamento: " + formaPagamento, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Forma de pagamento salva: " + formaPagamento);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao salvar forma de pagamento: " + e.getMessage());
        }
    }

    private void irParaTelaFinal() {
        try {
            Log.d(TAG, "Tentando abrir Tela_08_QrPdf_Final");
            Intent intent = new Intent(this, Tela_08_QrPdf_Final.class);
            startActivity(intent);
            Log.d(TAG, "Tela_08 aberta com sucesso");
        } catch (Exception e) {
            Log.e(TAG, "ERRO ao abrir Tela_08: " + e.getMessage(), e);
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();

            // Fallback: tentar abrir Tela_07
            try {
                Intent intent = new Intent(this, Tela_07_QrCode.class);
                startActivity(intent);
            } catch (Exception e2) {
                Log.e(TAG, "ERRO ao abrir Tela_07 também: " + e2.getMessage());
            }
        }
    }

    private void carregarPassageiros() {
        try {
            if (containerPassageiros == null) {
                Log.e(TAG, "containerPassageiros é null em carregarPassageiros");
                return;
            }

            containerPassageiros.removeAllViews();

            int passageiroCount = preferences.getInt("passageiro_count", 0);
            Log.d(TAG, "Quantidade de passageiros: " + passageiroCount);

            if (passageiroCount == 0) {
                TextView tvSemPassageiros = new TextView(this);
                tvSemPassageiros.setText("Nenhum passageiro cadastrado");
                tvSemPassageiros.setTextSize(16);
                tvSemPassageiros.setPadding(16, 16, 16, 16);
                containerPassageiros.addView(tvSemPassageiros);
                return;
            }

            for (int i = 0; i < passageiroCount; i++) {
                String nome = preferences.getString("nome_" + i, "");
                String documento = preferences.getString("documento_" + i, "");
                String cpf = preferences.getString("cpf_" + i, "");

                Log.d(TAG, "Passageiro " + i + ": " + nome);

                if (!nome.isEmpty()) {
                    CardView cardView = criarCardPassageiro(nome, documento, cpf);
                    containerPassageiros.addView(cardView);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "ERRO em carregarPassageiros: " + e.getMessage(), e);
        }
    }

    private CardView criarCardPassageiro(String nome, String documento, String cpf) {
        try {
            CardView cardView = new CardView(this);
            CardView.LayoutParams cardParams = new CardView.LayoutParams(
                    CardView.LayoutParams.MATCH_PARENT,
                    CardView.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, 16);
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(8);
            cardView.setRadius(12);
            cardView.setCardBackgroundColor(Color.WHITE);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(24, 24, 24, 24);

            TextView tvNome = new TextView(this);
            tvNome.setText("👤 " + nome);
            tvNome.setTextSize(16);
            tvNome.setTextColor(Color.BLACK);

            TextView tvDocumento = new TextView(this);
            tvDocumento.setText("📄 " + documento);
            tvDocumento.setTextSize(14);
            tvDocumento.setTextColor(Color.DKGRAY);

            layout.addView(tvNome);
            layout.addView(tvDocumento);

            if (cpf != null && !cpf.isEmpty()) {
                TextView tvCpf = new TextView(this);
                tvCpf.setText("🔢 CPF: " + cpf);
                tvCpf.setTextSize(12);
                tvCpf.setTextColor(Color.GRAY);
                layout.addView(tvCpf);
            }

            cardView.addView(layout);
            return cardView;

        } catch (Exception e) {
            Log.e(TAG, "Erro ao criar card passageiro: " + e.getMessage());
            return new CardView(this); // Retorna card vazio em caso de erro
        }
    }
}