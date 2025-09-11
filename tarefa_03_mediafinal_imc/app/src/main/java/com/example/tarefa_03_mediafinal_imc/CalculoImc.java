package com.example.tarefa_03_mediafinal_imc;

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

public class CalculoImc extends AppCompatActivity {

    EditText edPeso, edAltura;
    Button btCalcularImc, btLimparImc, btVoltarImc;
    TextView tvMostrarImc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imc);

        edPeso = findViewById(R.id.edCpf);
        edAltura = findViewById(R.id.edMesNascimento);
        btCalcularImc = findViewById(R.id.btCalcularImc);
        btLimparImc = findViewById(R.id.btLimparImc);
        btVoltarImc = findViewById(R.id.btVoltarImc);
        tvMostrarImc = findViewById(R.id.tvMostrarImc);

        btCalcularImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double imc, peso, altura;

                peso = Double.parseDouble(edPeso.getText().toString().trim());
                altura = Double.parseDouble(edAltura.getText().toString().trim());

                imc = (peso / (altura * altura));

                System.out.printf("Seu imc calculado: %.2f%n", imc);

                tvMostrarImc.setText("Seu imc calculado é: " + imc);

                if (imc < 18.5) {
                    Toast.makeText(CalculoImc.this,"Abaixo do peso!",Toast.LENGTH_LONG).show();
                } else if (imc >= 18.5 && imc <= 24.9) {
                    Toast.makeText(CalculoImc.this,"Peso normal!",Toast.LENGTH_LONG).show();
                } else if (imc >= 25 && imc <= 29.9) {
                    Toast.makeText(CalculoImc.this,"Sobrepeso!",Toast.LENGTH_LONG).show();
                } else if (imc >= 30 && imc < 34.9) {
                    Toast.makeText(CalculoImc.this,"Obesidade grau I!",Toast.LENGTH_LONG).show();
                } else if (imc >= 35 && imc <= 39.9) {
                    Toast.makeText(CalculoImc.this,"Obesidade grau II!",Toast.LENGTH_LONG).show();
                } else if (imc >= 40) {
                    Toast.makeText(CalculoImc.this,"Obesidade grau III ou mórbida!",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btLimparImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPeso.setText("");
                edAltura.setText("");
                tvMostrarImc.setText("");
            }
        });

        btVoltarImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculoImc.this, CalculoMediaFinal.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}