package com.example.tarefa_02;

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

import java.util.Calendar;

public class Exer_01 extends AppCompatActivity {
    EditText edDiaNascimento, edMesNascimento, edAnoNascimento;
    Button btConverter, btLimpar, btVoltar, btAvancar1;
    TextView tvMostrarIdade, tvMostrarMeses, tvMostrarDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer01);

        edDiaNascimento = findViewById(R.id.edDiaNascimento);
        edMesNascimento = findViewById(R.id.edMesNascimento);
        edAnoNascimento = findViewById(R.id.edAnoNascimento);
        btConverter = findViewById(R.id.btConverter);
        btLimpar = findViewById(R.id.btLimpar);
        btVoltar = findViewById(R.id.btVoltar);
        btAvancar1 = findViewById(R.id.btAvancar1);
        tvMostrarIdade = findViewById(R.id.tvMostrarIdade);
        tvMostrarMeses = findViewById(R.id.tvMostrarMeses);
        tvMostrarDias = findViewById(R.id.tvMostrarDias);

        btConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int diaNascimento, mesNascimento, anoNascimento;
                int idade, anos, dias, meses = 0, anoAtual;

                diaNascimento = Integer.parseInt(edDiaNascimento.getText().toString().trim());
                mesNascimento = Integer.parseInt(edMesNascimento.getText().toString().trim());
                anoNascimento = Integer.parseInt(edAnoNascimento.getText().toString().trim());

                anoAtual = Calendar.getInstance().get(Calendar.YEAR);

                if(diaNascimento < 1 || diaNascimento > 31){
                    Toast.makeText(Exer_01.this,"Digite um dia válido!",Toast.LENGTH_SHORT).show();
                }

                if(mesNascimento<1 || mesNascimento > 12){
                    Toast.makeText(Exer_01.this,"Digite um mês válido!",Toast.LENGTH_SHORT).show();
                };

                idade = anoAtual-anoNascimento;

                //Conta apenas os meses completos antes do mês de nascimento e
                // Adiciona os dias vividos no mês de nascimento.
                dias = (idade*365)  + ((mesNascimento-1)*30)+ diaNascimento;

                meses = (12*idade) + mesNascimento;

            tvMostrarIdade.setText(String.valueOf(idade));

            tvMostrarMeses.setText(String.valueOf(meses));

            tvMostrarDias.setText(String.valueOf(dias));
            }
        });


        btAvancar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_01.this, Exer_02.class);
                startActivity(intent);
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edDiaNascimento.setText("");
                edMesNascimento.setText("");
                edAnoNascimento.setText("");
                tvMostrarIdade.setText("");
                tvMostrarDias.setText("");
                tvMostrarMeses.setText("");
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_01.this, Tela_Principal.class);
                startActivity(intent);
                finish();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}