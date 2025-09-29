package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Tela_04_assentos extends AppCompatActivity {

    private TextView[] assentos = new TextView[40];
    private String[] statusAssentos = new String[40];
    private SharedPreferences preferences;
    private Button btConfirmarReserva;
    private TextView textView25;
    private int assentosReservados = 0;
    private double valorPorAssento = 50.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela04_assentos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        preferences = getSharedPreferences("AssentosPref", MODE_PRIVATE);
        btConfirmarReserva = findViewById(R.id.btConfirmarReserva);
        textView25 = findViewById(R.id.textView25);

        // Encontrar todos os assentos
        encontrarAssentos();

        // Configurar o aplicativo
        if (preferences.getBoolean("primeira_vez", true)) {
            gerarEstadosAleatorios();
            salvarEstados();
            preferences.edit().putBoolean("primeira_vez", false).apply();
        } else {
            carregarEstados();
        }

        aplicarCores();
        configurarCliques();
        atualizarValorTotal();

        // Configurar botão de confirmar
        btConfirmarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarReserva();
            }
        });
    }

    private void encontrarAssentos() {
        for (int i = 0; i < 40; i++) {
            int resourceId = getResources().getIdentifier("seats" + (i + 1), "id", getPackageName());
            assentos[i] = findViewById(resourceId);
            if (assentos[i] != null) {
                assentos[i].setText(String.valueOf(i + 1)); // Colocar número do assento
            }
        }
    }

    private void gerarEstadosAleatorios() {
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            int numero = random.nextInt(3);
            switch (numero) {
                case 0:
                    statusAssentos[i] = "disponivel";
                    break;
                case 1:
                    statusAssentos[i] = "reservado";
                    break;
                case 2:
                    statusAssentos[i] = "ocupado";
                    break;
            }
        }
    }

    private void aplicarCores() {
        for (int i = 0; i < 40; i++) {
            if (assentos[i] != null) {
                switch (statusAssentos[i]) {
                    case "disponivel":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.disponivel));
                        assentos[i].setClickable(true);
                        break;
                    case "reservado":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.reservado));
                        assentos[i].setClickable(false);
                        break;
                    case "ocupado":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.ocupado));
                        assentos[i].setClickable(false);
                        break;
                }
            }
        }
    }

    private void configurarCliques() {
        for (int i = 0; i < 40; i++) {
            final int index = i;
            if (assentos[i] != null) {
                assentos[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (statusAssentos[index].equals("disponivel")) {
                            // Reservar assento
                            statusAssentos[index] = "reservado";
                            assentosReservados++;
                            aplicarCores();
                            salvarEstados();
                            atualizarValorTotal();

                            Toast.makeText(Tela_04_assentos.this,
                                    "Assento " + (index + 1) + " reservado!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    private void atualizarValorTotal() {
        double total = assentosReservados * valorPorAssento;
        if (textView25 != null) {
            textView25.setText(String.format("Valor total: R$ %.2f", total));
        }

        // Habilitar/desabilitar botão conforme tem reservas
        if (btConfirmarReserva != null) {
            btConfirmarReserva.setEnabled(assentosReservados > 0);
        }
    }

    private void salvarEstados() {
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < 40; i++) {
            editor.putString("assento_" + i, statusAssentos[i]);
        }
        editor.putInt("assentos_reservados", assentosReservados);
        editor.apply();
    }

    private void carregarEstados() {
        for (int i = 0; i < 40; i++) {
            statusAssentos[i] = preferences.getString("assento_" + i, "disponivel");
            if (statusAssentos[i].equals("reservado")) {
                assentosReservados++;
            }
        }
        // Carregar contagem salva
        assentosReservados = preferences.getInt("assentos_reservados", 0);
    }

    private void confirmarReserva() {
        if (assentosReservados > 0) {
            // Mudar todos os reservados para ocupados
            for (int i = 0; i < 40; i++) {
                if (statusAssentos[i].equals("reservado")) {
                    statusAssentos[i] = "ocupado";
                }
            }

            aplicarCores();
            salvarEstados();

            // Mostrar mensagem de sucesso
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Reserva Confirmada!")
                    .setMessage(assentosReservados + " assentos reservados com sucesso!\nTotal: R$ " +
                            String.format("%.2f", (assentosReservados * valorPorAssento)))
                    .setPositiveButton("OK", null)
                    .show();

            // Resetar contagem
            assentosReservados = 0;
            atualizarValorTotal();
        }
    }

    // Método para resetar tudo (opcional - pode chamar em um botão)
    public void resetarTudo(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        recreate(); // Recria a activity
    }
}