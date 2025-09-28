package com.example.tarefa_02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_04 extends AppCompatActivity {

    EditText edPeso, edAltura;
    Button btCalcular, btLimpar4, btVoltar4, btAvancar4;
    TextView tvResultadoImc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer04);

        edPeso = findViewById(R.id.edCpf);
        edAltura = findViewById(R.id.edMesNascimento);
        btCalcular = findViewById(R.id.btValidarCpf);
        btLimpar4 = findViewById(R.id.btLimpar7);
        btVoltar4 = findViewById(R.id.btVoltar7);
        btAvancar4 = findViewById(R.id.btAvancar7x);
        tvResultadoImc = findViewById(R.id.tvMostrarRegiao);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double imc, peso, altura;

                peso = Double.parseDouble(edPeso.getText().toString().trim());
                altura = Double.parseDouble(edAltura.getText().toString().trim());

                imc = (peso / (altura * altura));

                System.out.printf("Seu imc calculado: %.2f%n", imc);

                tvResultadoImc.setText("Seu imc calculado é: " + imc);

                if (imc < 18.5) {
                    Toast.makeText(Exer_04.this,"Abaixo do peso!",Toast.LENGTH_LONG).show();
                } else if (imc >= 18.5 && imc <= 24.9) {
                    Toast.makeText(Exer_04.this,"Peso normal!",Toast.LENGTH_LONG).show();
                } else if (imc >= 25 && imc <= 29.9) {
                    Toast.makeText(Exer_04.this,"Sobrepeso!",Toast.LENGTH_LONG).show();
                } else if (imc >= 30 && imc < 34.9) {
                    Toast.makeText(Exer_04.this,"Obesidade grau I!",Toast.LENGTH_LONG).show();
                } else if (imc >= 35 && imc <= 39.9) {
                    Toast.makeText(Exer_04.this,"Obesidade grau II!",Toast.LENGTH_LONG).show();
                } else if (imc >= 40) {
                    Toast.makeText(Exer_04.this,"Obesidade grau III ou mórbida!",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btLimpar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPeso.setText("");
                edAltura.setText("");
                tvResultadoImc.setText("");
            }
        });

        btVoltar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_04.this, Exer_03.class);
                startActivity(intent);
            }
        });

        btAvancar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_04.this, Exer_05.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}