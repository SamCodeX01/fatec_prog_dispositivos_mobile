package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_03_Horario_Destino extends AppCompatActivity {
    Intent intent = new Intent(this, Tela_04_assentos.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela03_horario_destino);

         TextView tvOrigem, tvDestino, tvData;
         Spinner spinnerHorarios, spinnerEmpresas;
         Button btnContinuar;

         String[] horarios = {
                "06:00", "08:30", "12:00", "14:30", "18:00", "22:00"
        };

         String[] empresas = {
                "Cometa", "Itapemirim", "Gontijo", "Util", "Kaissara", "Expresso Brasileiro"
        };


        // Inicializar componentes
        tvOrigem = findViewById(R.id.tvOrigem);
        tvDestino = findViewById(R.id.tvDestino);
        tvData = findViewById(R.id.tvData);
        spinnerHorarios = findViewById(R.id.spinnerHorarios);
        spinnerEmpresas = findViewById(R.id.spinnerEmpresas);
        btnContinuar = findViewById(R.id.btnContinuar);

        intent.putExtra("horario", spinnerHorarios.getSelectedItem().toString());
        startActivity(intent);

        // Receber dados da tela anterior
        Intent intent = getIntent();
        String origem = intent.getStringExtra("origem");
        String destino = intent.getStringExtra("destino");
        String data = intent.getStringExtra("data");

        // Exibir dados recebidos
        tvOrigem.setText(origem);
        tvDestino.setText(destino);
        tvData.setText(data);

        // Configurar spinners
        ArrayAdapter<String> adapterHorarios = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, horarios);
        adapterHorarios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorarios.setAdapter(adapterHorarios);

        ArrayAdapter<String> adapterEmpresas = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, empresas);
        adapterEmpresas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresas.setAdapter(adapterEmpresas);

        // Configurar botão continuar
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String horario = spinnerHorarios.getSelectedItem().toString();
                String empresa = spinnerEmpresas.getSelectedItem().toString();

                Intent nextIntent = new Intent(Tela_03_Horario_Destino.this,
                        Tela_04_assentos.class);
                nextIntent.putExtra("origem", origem);
                nextIntent.putExtra("destino", destino);
                nextIntent.putExtra("data", data);
                nextIntent.putExtra("horario", horario);
                nextIntent.putExtra("empresa", empresa);
                startActivity(nextIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}