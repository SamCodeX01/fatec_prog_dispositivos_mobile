package com.example.tarefa_02;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_03 extends AppCompatActivity {

    EditText edQtdNumeros;
    Button btGerarNumeros, btLimpar3, btVoltar3, btAvancar3;
    TextView tvMostrarNumerosGerados;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer03);

        edQtdNumeros = findViewById(R.id.edQtdNumeros);
        btGerarNumeros = findViewById(R.id.btGerarNumeros);
        btLimpar3 = findViewById(R.id.btLimpar3);
        btVoltar3 = findViewById(R.id.btVoltar3);
        btAvancar3 = findViewById(R.id.btAvancar3);
        tvMostrarNumerosGerados = findViewById(R.id.tvMostrarNumerosGerados);

        btGerarNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.print("Digite a quantidade de numeros: ");
               // int n = scan.nextInt();

                int n = Integer.parseInt(edQtdNumeros.getText().toString().trim());

                if(n < 1 || n > 100){
                   // System.out.println("Erro: O número digitado deve estar entre 1 e 100");
                    Toast.makeText(Exer_03.this, "Erro: O número digitado deve estar entre 1 e 100", Toast.LENGTH_SHORT).show();
                    return;
                }

                int[] numeros = new int[n];//O array de numeros esta armazenando n

                // Primeiro passo: Aqui gera N números aleatórios sem repetição
                for (int i = 0; i < n; i++) {
                    int novoNumero;
                    boolean repetido;

                    do {
                        repetido = false;
                        novoNumero = new Random().nextInt(100); // Gera de 0 a 99

                        // Verifica se o número já existe no array
                        for (int j = 0; j < i; j++) {
                            if (numeros[j] == novoNumero) {
                                repetido = true;
                                break;
                            }
                        }
                    } while (repetido);

                    numeros[i] = novoNumero;
                }

                // Aqui esta usando ordenação simples com selection sort
                for (int i = 0; i < n - 1; i++) {
                    int indiceMenor = i;

                    // Encontra o menor número na parte não ordenada
                    for (int j = i + 1; j < n; j++) {
                        if (numeros[j] < numeros[indiceMenor]) {
                            indiceMenor = j;
                        }
                    }

                    // Troca o menor número com a posição atual
                    int temp = numeros[i];
                    numeros[i] = numeros[indiceMenor];
                    numeros[indiceMenor] = temp;
                }

                // Aqui mostra os números ordenados
                for (int num : numeros) {
                    tvMostrarNumerosGerados.setText("\nNúmeros em ordem crescente:" + String.valueOf(num));
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}