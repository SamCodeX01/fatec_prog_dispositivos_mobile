package com.example.tarefa_05_seguro_desemprego_formal_versao_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTempoTrabalho, editSalario;
    CheckBox checkDemissaoSemJustaCausa;
    Button btnVerificar;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar EdgeToEdge (insets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textTitulo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar componentes
        editTempoTrabalho = findViewById(R.id.editTempoTrabalho);
        editSalario = findViewById(R.id.editSalario);
        checkDemissaoSemJustaCausa = findViewById(R.id.checkDemissaoSemJustaCausa);
        btnVerificar = findViewById(R.id.btnVerificar);
        textResultado = findViewById(R.id.textResultado);

        // Configurar clique do botão
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarSeguroDesemprego();
            }
        });
    }

    private void verificarSeguroDesemprego() {
        try {
            // Obter valores dos campos
            String tempoTrabalhoStr = editTempoTrabalho.getText().toString();
            String salarioStr = editSalario.getText().toString();

            // Verificar se campos estão preenchidos
            if (tempoTrabalhoStr.isEmpty() || salarioStr.isEmpty()) {
                textResultado.setText("Preencha todos os campos!");
                return;
            }

            int mesesTrabalhados = Integer.parseInt(tempoTrabalhoStr);
            double ultimoSalario = Double.parseDouble(salarioStr);
            boolean demissaoSemJustaCausa = checkDemissaoSemJustaCausa.isChecked();

            // Verificar condições básicas
            if (!demissaoSemJustaCausa) {
                textResultado.setText("❌ Não tem direito: \nDemissão deve ser sem justa causa");
                return;
            }

            if (mesesTrabalhados < 12) {
                textResultado.setText("❌ Não tem direito: \nMínimo 12 meses trabalhados");
                return;
            }

            // Verificar período de carência (não pode ter recebido seguro há menos de 16 meses)
            // Esta verificação seria mais complexa na vida real

            // Calcular parcelas e valor
            ResultadoSeguro resultado = calcularSeguroDesemprego(mesesTrabalhados, ultimoSalario);

            if (resultado.temDireito) {
                String mensagem = "✅ TEM DIREITO AO SEGURO-DESEMPREGO!\n\n" +
                        "Parcelas: " + resultado.parcelas + " meses\n" +
                        "Valor médio das 3 parcelas: \n" +
                        "1ª: R$ " + String.format("%.2f", resultado.valorPrimeiraParcela) + "\n" +
                        "2ª: R$ " + String.format("%.2f", resultado.valorSegundaParcela) + "\n" +
                        "3ª+: R$ " + String.format("%.2f", resultado.valorTerceiraParcela) + "\n\n" +
                        "Valor total aproximado: R$ " + String.format("%.2f", resultado.valorTotal);
                textResultado.setText(mensagem);
            } else {
                textResultado.setText("❌ Não tem direito às parcelas");
            }

        } catch (NumberFormatException e) {
            textResultado.setText("Erro: Digite valores válidos!");
        }
    }

    private ResultadoSeguro calcularSeguroDesemprego(int mesesTrabalhados, double ultimoSalario) {
        ResultadoSeguro resultado = new ResultadoSeguro();

        // REGRAS OFICIAIS DO SEGURO-DESEMPREGO

        // 1. Cálculo das parcelas (baseado no tempo trabalhado)
        if (mesesTrabalhados >= 12 && mesesTrabalhados <= 23) {
            resultado.parcelas = 4;
        } else if (mesesTrabalhados >= 24 && mesesTrabalhados <= 35) {
            resultado.parcelas = 5;
        } else if (mesesTrabalhados >= 36) {
            resultado.parcelas = 5; // Na regra atual, máximo é 5 parcelas
        } else {
            resultado.temDireito = false;
            return resultado;
        }

        // 2. Cálculo do valor (regras oficiais de 2024)
        // O valor é calculado com média dos últimos 3 salários, mas vamos simplificar usando o último

        double mediaSalarial = ultimoSalario; // Simplificação: usar último salário

        // Faixas de cálculo (valores aproximados para 2024)
        final double TETO_SEGURO = 2009.03; // Valor atualizado
        final double PISO = 1320.00; // Salário mínimo

        if (mediaSalarial <= PISO) {
            // Recebe o salário integral se ganha até 1 salário mínimo
            resultado.valorPrimeiraParcela = mediaSalarial;
            resultado.valorSegundaParcela = mediaSalarial;
            resultado.valorTerceiraParcela = mediaSalarial;
        } else if (mediaSalarial <= (PISO * 1.918)) {
            // Entre 1 e 1.918 salários mínimos
            // Calcula: salário mínimo + 80% do que exceder 1 salário mínimo
            double excedente = mediaSalarial - PISO;
            resultado.valorPrimeiraParcela = PISO + (excedente * 0.8);
            resultado.valorSegundaParcela = resultado.valorPrimeiraParcela;
            resultado.valorTerceiraParcela = resultado.valorPrimeiraParcela;
        } else {
            // Acima de 1.918 salários mínimos
            // Valor fixo do teto
            resultado.valorPrimeiraParcela = TETO_SEGURO;
            resultado.valorSegundaParcela = TETO_SEGURO;
            resultado.valorTerceiraParcela = TETO_SEGURO;
        }

        // Garantir que não ultrapasse o teto
        resultado.valorPrimeiraParcela = Math.min(resultado.valorPrimeiraParcela, TETO_SEGURO);
        resultado.valorSegundaParcela = Math.min(resultado.valorSegundaParcela, TETO_SEGURO);
        resultado.valorTerceiraParcela = Math.min(resultado.valorTerceiraParcela, TETO_SEGURO);

        // Calcular valor total (considerando que todas as parcelas são iguais na simplificação)
        resultado.valorTotal = (resultado.valorPrimeiraParcela * resultado.parcelas);
        resultado.temDireito = true;

        return resultado;
    }

    // Classe para armazenar resultados
    private class ResultadoSeguro {
        boolean temDireito = false;
        int parcelas = 0;
        double valorPrimeiraParcela = 0;
        double valorSegundaParcela = 0;
        double valorTerceiraParcela = 0;
        double valorTotal = 0;
    }
}