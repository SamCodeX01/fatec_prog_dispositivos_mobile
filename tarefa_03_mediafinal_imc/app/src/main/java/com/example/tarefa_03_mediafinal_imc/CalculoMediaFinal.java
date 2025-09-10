package com.example.tarefa_03_mediafinal_imc;

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

public class CalculoMediaFinal extends AppCompatActivity {

    EditText edAtividades, edProva1, edTrabalhoT1, edTrabalhoT2,  edMultidisciplinar;
    TextView tvResultadoNota;
    Button btLimpar6, btVoltar6, btMenu6, btAvancar6, btCalcularNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_media_final);

        edAtividades = findViewById(R.id.edAtividades);
        edProva1 = findViewById(R.id.edProva1);
        edTrabalhoT1 = findViewById(R.id.edTrabalhoT1);
        edTrabalhoT2 = findViewById(R.id.edTrabalhoT2);
        edMultidisciplinar = findViewById(R.id.edAnoNascimento);
        tvResultadoNota = findViewById(R.id.tvResultadoNota);
        btLimpar6 = findViewById(R.id.btLimpar7);
        btVoltar6 = findViewById(R.id.btVoltar7);
        btMenu6 = findViewById(R.id.btMenu7);
        btAvancar6 = findViewById(R.id.btAvancar7x);
        btCalcularNota = findViewById(R.id.btValidarCpf);

        btCalcularNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double at, p1, t1, t2, m;

                at = Double.parseDouble(edAtividades.getText().toString().trim());
                p1 = Double.parseDouble(edProva1.getText().toString().trim());
                t1 = Double.parseDouble(edTrabalhoT1.getText().toString().trim());
                t2 = Double.parseDouble(edTrabalhoT2.getText().toString().trim());
                m = Double.parseDouble(edMultidisciplinar.getText().toString().trim());

                // Cálculo da nota final
                double notaFinal = (at * 0.20) + (p1 * 0.20) + (t1 * 0.30) + (t2 * 0.30) + (m * 0.10);

                tvResultadoNota.setText("Nota Final: " + notaFinal);
            }
        });
        btLimpar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edAtividades.setText("");
                edProva1.setText("");
                edTrabalhoT1.setText("");
                edTrabalhoT2.setText("");
                edMultidisciplinar.setText("");
            }
        });
        btVoltar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_06.this, Exer_05.class);
                startActivity(intent);
            }
        });
        btAvancar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_06.this, Exer_07.class);
                startActivity(intent);
            }
        });
        btMenu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_06.this, Tela_Principal.class);
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