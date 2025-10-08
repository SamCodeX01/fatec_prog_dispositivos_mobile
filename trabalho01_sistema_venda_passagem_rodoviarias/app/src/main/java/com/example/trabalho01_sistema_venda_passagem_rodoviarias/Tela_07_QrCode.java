package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Tela_07_QrCode extends AppCompatActivity {

    private TextView tvCodigoReserva, tvInformacoes;
    private Button btnSalvarPDF;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela07_qr_code);

        preferences = getSharedPreferences("PassageirosPref", MODE_PRIVATE);

        tvCodigoReserva = findViewById(R.id.tvCodigoReserva);
        tvInformacoes = findViewById(R.id.tvInformacoes);
        btnSalvarPDF = findViewById(R.id.btnSalvarPDF);

        gerarCodigoReserva();
        exibirInformacoes();

        btnSalvarPDF.setOnClickListener(v -> {
            Toast.makeText(this, "Reserva confirmada com sucesso!", Toast.LENGTH_SHORT).show();
            // Voltar para tela inicial
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void gerarCodigoReserva() {
        // Gera um código simples baseado no timestamp
        String codigo = "RES" + System.currentTimeMillis();
        tvCodigoReserva.setText(codigo);

        // Salva o código
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("codigo_reserva", codigo);
        editor.apply();
    }

    private void exibirInformacoes() {
//        StringBuilder info = new StringBuilder();
//
//        // Dados da viagem
//        info.append("VIAGEM CONFIRMADA\n\n");
//        info.append("Origem: ").append(preferences.getString("origem", "")).append("\n");
//        info.append("Destino: ").append(preferences.getString("destino", "")).append("\n");
//        info.append("Data: ").append(preferences.getString("data", "")).append("\n");
//        info.append("Horário: ").append(preferences.getString("horario", "")).append("\n");
//        info.append("Valor Total: R$ ").append(preferences.getFloat("valor", 0)).append("\n");
//        info.append("Forma de Pagamento: ").append(preferences.getString("forma_pagamento", "")).append("\n\n");
//
//        // Passageiros
//        int passageiroCount = preferences.getInt("passageiro_count", 0);
//        info.append("PASSAGEIROS:\n");
//        for (int i = 0; i < passageiroCount; i++) {
//            info.append("Nome: ").append(preferences.getString("nome_" + i, "")).append("\n");
//            info.append("Documento: ").append(preferences.getString("documento_" + i, "")).append("\n");
//            info.append("CPF: ").append(preferences.getString("cpf_" + i, "")).append("\n\n");
//        }
//
//        tvInformacoes.setText(info.toString());
//    }
        StringBuilder info = new StringBuilder();

        // Dados da viagem
        info.append("VIAGEM CONFIRMADA\n\n");
        info.append("Origem: ").append(preferences.getString("origem", "N/A")).append("\n");
        info.append("Destino: ").append(preferences.getString("destino", "N/A")).append("\n");
        info.append("Data: ").append(preferences.getString("data", "N/A")).append("\n");
        info.append("Horário: ").append(preferences.getString("horario", "N/A")).append("\n");
        info.append("Empresa: ").append(preferences.getString("empresa", "N/A")).append("\n");
        info.append("Assentos Reservados: ").append(preferences.getInt("assentos_reservados", 0)).append("\n");
        info.append("Valor Total: R$ ").append(String.format("%.2f", preferences.getFloat("valor", 0))).append("\n");
        info.append("Forma de Pagamento: ").append(preferences.getString("forma_pagamento", "N/A")).append("\n\n");

        // Passageiros
        int passageiroCount = preferences.getInt("passageiro_count", 0);
        info.append("PASSAGEIROS (").append(passageiroCount).append("):\n");
        for (int i = 0; i < passageiroCount; i++) {
            info.append("Passageiro ").append(i + 1).append(":\n");
            info.append("  Nome: ").append(preferences.getString("nome_" + i, "")).append("\n");
            info.append("  Documento: ").append(preferences.getString("documento_" + i, "")).append("\n");
            info.append("  Data Nasc.: ").append(preferences.getString("dataNascimento_" + i, "")).append("\n");
            info.append("  CPF: ").append(preferences.getString("cpf_" + i, "")).append("\n\n");
        }

        tvInformacoes.setText(info.toString());
    }
}




