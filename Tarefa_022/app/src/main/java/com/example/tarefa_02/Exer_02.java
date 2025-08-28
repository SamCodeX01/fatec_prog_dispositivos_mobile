package com.example.tarefa_02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_02 extends AppCompatActivity {

    EditText edCateto1, edCateto2;
    Button btCalcular, btLimpar2, btVoltar2, btAvancar2;
    TextView tvResultadoHipotenusa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer02);

        edCateto1 = findViewById(R.id.edPeso);
        edCateto2 = findViewById(R.id.edAltura);
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar2 = findViewById(R.id.btLimpar4);
        btVoltar2 = findViewById(R.id.btVoltar4);
        btAvancar2 = findViewById(R.id.btAvancar4);
        tvResultadoHipotenusa = findViewById(R.id.tvResultadoImc);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double  cateto1, cateto2,hipotenusa;

                cateto1 = Double.parseDouble(edCateto1.getText().toString().trim());
                cateto2 = Double.parseDouble(edCateto2.getText().toString().trim());

                double cat1 = Math.pow(cateto1, 2);
                double cat2 = Math.pow(cateto2, 2);
                double soma = cat1+cat2;
                hipotenusa = Math.sqrt(soma);

                tvResultadoHipotenusa.setText("A hipotenusa desse triângulo mede: " + String.format("%.2f", hipotenusa));
            }
        });

        btAvancar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_02.this,Exer_03.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener((findViewById(android.R.id.content)), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}