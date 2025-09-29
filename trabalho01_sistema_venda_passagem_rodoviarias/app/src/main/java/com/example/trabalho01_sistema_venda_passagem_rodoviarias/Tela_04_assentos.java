package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        // INICIALIZAR O ARRAY primeiro
        for (int i = 0; i < 40; i++) {
            statusAssentos[i] = "disponivel"; // Valor padrão
        }

        // Encontrar todos os assentos
        encontrarAssentos();

        // Configurar o aplicativo
        if (preferences.getBoolean("primeira_vez", true)) {
            Log.d("DEBUG", "Primeira vez - gerando estados aleatórios");
            gerarEstadosAleatorios();
            salvarEstados();
            preferences.edit().putBoolean("primeira_vez", false).apply();
        } else {
            Log.d("DEBUG", "Carregando estados salvos");
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
            } else {
                Log.e("DEBUG", "Assento " + (i + 1) + " não encontrado! ID: seats" + (i + 1));
            }
        }
    }

    private void gerarEstadosAleatorios() {
        Random random = new Random();
        int disponivelCount = 0, reservadoCount = 0, ocupadoCount = 0;

        Log.d("DEBUG_GERAR", "=== INICIANDO GERAÇÃO ALEATÓRIA ===");

        for (int i = 0; i < 40; i++) {
            int numero = random.nextInt(3); // Gera 0, 1 ou 2
            Log.d("DEBUG_GERAR", "Assento " + (i + 1) + " - random: " + numero);

            switch (numero) {
                case 0:
                    statusAssentos[i] = "disponivel";
                    disponivelCount++;
                    Log.d("DEBUG_GERAR", "Assento " + (i + 1) + " -> DISPONÍVEL");
                    break;
                case 1:
                    statusAssentos[i] = "reservado";
                    reservadoCount++;
                    Log.d("DEBUG_GERAR", "Assento " + (i + 1) + " -> RESERVADO");
                    break;
                case 2:
                    statusAssentos[i] = "ocupado";
                    ocupadoCount++;
                    Log.d("DEBUG_GERAR", "Assento " + (i + 1) + " -> OCUPADO");
                    break;
            }
        }

        Log.d("DEBUG_GERAR", "=== RESUMO ===");
        Log.d("DEBUG_GERAR", "Disponivel: " + disponivelCount);
        Log.d("DEBUG_GERAR", "Reservado: " + reservadoCount);
        Log.d("DEBUG_GERAR", "Ocupado: " + ocupadoCount);
        Log.d("DEBUG_GERAR", "Total: " + (disponivelCount + reservadoCount + ocupadoCount));
    }

    private void aplicarCores() {
        int disponivelCount = 0, reservadoCount = 0, ocupadoCount = 0;

        Log.d("DEBUG_CORES", "=== APLICANDO CORES ===");

        for (int i = 0; i < 40; i++) {
            if (assentos[i] != null) {
                String status = statusAssentos[i];
                Log.d("DEBUG_CORES", "Assento " + (i + 1) + " - Status: " + status);

                switch (status) {
                    case "disponivel":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.disponivel));
                        assentos[i].setClickable(true);
                        disponivelCount++;
                        break;
                    case "reservado":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.reservado));
                        assentos[i].setClickable(false);
                        reservadoCount++;
                        break;
                    case "ocupado":
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.ocupado));
                        assentos[i].setClickable(false);
                        ocupadoCount++;
                        break;
                    default:
                        // Se for qualquer outro valor, define como disponível
                        statusAssentos[i] = "disponivel";
                        assentos[i].setBackgroundColor(ContextCompat.getColor(this, R.color.disponivel));
                        assentos[i].setClickable(true);
                        disponivelCount++;
                        Log.w("DEBUG_CORES", "Assento " + (i + 1) + " com status inválido: " + status + " -> convertido para DISPONÍVEL");
                        break;
                }
            } else {
                Log.e("DEBUG_CORES", "Assento " + (i + 1) + " é NULL!");
            }
        }

        Log.d("DEBUG_CORES", "=== RESUMO CORES ===");
        Log.d("DEBUG_CORES", "Disponivel: " + disponivelCount + " (Verde)");
        Log.d("DEBUG_CORES", "Reservado: " + reservadoCount + " (Amarelo)");
        Log.d("DEBUG_CORES", "Ocupado: " + ocupadoCount + " (Vermelho)");
    }

    private void configurarCliques() {
        for (int i = 0; i < 40; i++) {
            final int index = i;
            if (assentos[i] != null) {
                assentos[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("disponivel".equals(statusAssentos[index])) {
                            // Reservar assento
                            statusAssentos[index] = "reservado";
                            assentosReservados++;
                            aplicarCores();
                            salvarEstados();
                            atualizarValorTotal();

                            Toast.makeText(Tela_04_assentos.this,
                                    "Assento " + (index + 1) + " reservado!",
                                    Toast.LENGTH_SHORT).show();

                            Log.d("DEBUG_CLIQUE", "Assento " + (index + 1) + " reservado. Total reservados: " + assentosReservados);
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
        Log.d("DEBUG_VALOR", "Assentos reservados: " + assentosReservados + ", Valor total: R$ " + total);
    }

    private void salvarEstados() {
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < 40; i++) {
            editor.putString("assento_" + i, statusAssentos[i]);
        }
        editor.putInt("assentos_reservados", assentosReservados);
        editor.apply();

        Log.d("DEBUG_SALVAR", "Estados salvos no SharedPreferences. Assentos reservados: " + assentosReservados);
    }

    private void carregarEstados() {
        assentosReservados = 0;
        int disponivelCount = 0, reservadoCount = 0, ocupadoCount = 0;

        Log.d("DEBUG_CARREGAR", "=== CARREGANDO ESTADOS ===");

        for (int i = 0; i < 40; i++) {
            String estadoSalvo = preferences.getString("assento_" + i, "disponivel");
            statusAssentos[i] = estadoSalvo;

            Log.d("DEBUG_CARREGAR", "Assento " + (i + 1) + " - Estado salvo: " + estadoSalvo);

            if ("reservado".equals(estadoSalvo)) {
                assentosReservados++;
                reservadoCount++;
            } else if ("disponivel".equals(estadoSalvo)) {
                disponivelCount++;
            } else if ("ocupado".equals(estadoSalvo)) {
                ocupadoCount++;
            } else {
                // Se for null ou qualquer outro valor, define como disponível
                statusAssentos[i] = "disponivel";
                disponivelCount++;
                Log.w("DEBUG_CARREGAR", "Assento " + (i + 1) + " com estado inválido: " + estadoSalvo + " -> convertido para DISPONÍVEL");
            }
        }

        Log.d("DEBUG_CARREGAR", "=== RESUMO CARREGAMENTO ===");
        Log.d("DEBUG_CARREGAR", "Disponivel: " + disponivelCount);
        Log.d("DEBUG_CARREGAR", "Reservado: " + reservadoCount);
        Log.d("DEBUG_CARREGAR", "Ocupado: " + ocupadoCount);
        Log.d("DEBUG_CARREGAR", "Assentos reservados: " + assentosReservados);
    }

    private void confirmarReserva() {
        if (assentosReservados > 0) {
            Log.d("DEBUG_CONFIRMAR", "Confirmando reserva de " + assentosReservados + " assentos");

            // Mudar todos os reservados para ocupados
            for (int i = 0; i < 40; i++) {
                if ("reservado".equals(statusAssentos[i])) {
                    statusAssentos[i] = "ocupado";
                    Log.d("DEBUG_CONFIRMAR", "Assento " + (i + 1) + " -> OCUPADO");
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
            salvarEstados(); // Salvar o novo estado
            atualizarValorTotal();

            Log.d("DEBUG_CONFIRMAR", "Reserva confirmada e contagem resetada");
        }
    }

    // Método para forçar TODOS como disponíveis (TESTE)
    public void forcarTodosDisponiveis(View view) {
        Log.d("DEBUG_FORCAR", "Forçando TODOS os assentos para DISPONÍVEL");

        for (int i = 0; i < 40; i++) {
            statusAssentos[i] = "disponivel";
        }
        assentosReservados = 0;
        salvarEstados();
        aplicarCores();
        atualizarValorTotal();
        Toast.makeText(this, "Todos os assentos resetados para DISPONÍVEL", Toast.LENGTH_SHORT).show();

        Log.d("DEBUG_FORCAR", "Todos os assentos foram forçados para DISPONÍVEL");
    }

    // Método para resetar tudo
    public void resetarTudo(View view) {
        Log.d("DEBUG_RESET", "Resetando SharedPreferences");
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        recreate(); // Recria a activity
    }
}