package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_02_Data_Origem_Destino extends AppCompatActivity {

    private Spinner spinnerOrigem, spinnerDestino;
    private DatePicker datePicker;
    private Button btnBuscarPassagem;

    private String[] estados = {
            "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
            "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão",
            "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará",
            "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
            "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima",
            "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela02_data_origem_destino);

        spinnerOrigem = findViewById(R.id.spinnerOrigem);
        spinnerDestino = findViewById(R.id.spinnerDestino);
        datePicker = findViewById(R.id.datePicker);
        btnBuscarPassagem = findViewById(R.id.btnBuscarPassagem);

        // Configurar spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerOrigem.setAdapter(adapter);
        spinnerDestino.setAdapter(adapter);

        btnBuscarPassagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origem = spinnerOrigem.getSelectedItem().toString();
                String destino = spinnerDestino.getSelectedItem().toString();
                int dia = datePicker.getDayOfMonth();
                int mes = datePicker.getMonth() + 1; // Month é 0-based
                int ano = datePicker.getYear();

                String data = String.format("%02d/%02d/%d", dia, mes, ano);

                if (origem.equals(destino)) {
                    Toast.makeText(Tela_02_Data_Origem_Destino.this,
                            "Origem e destino não podem ser iguais!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Tela_02_Data_Origem_Destino.this,
                        Tela_03_Horario_Destino.class);
                intent.putExtra("origem", origem);
                intent.putExtra("destino", destino);
                intent.putExtra("data", data);
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