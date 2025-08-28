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

public class Exer_05 extends AppCompatActivity {
    EditText edOpcao;
    Button btMenu, btGerarSenha, btLimpar5, btVoltar5, btAvancar5;
    TextView tvSenhaGerada,  tvAguardeChamada;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer05);

        edOpcao = findViewById(R.id.edAnoNascimento);
        btGerarSenha = findViewById(R.id.btValidarCpf);
        btLimpar5 = findViewById(R.id.btLimpar7);
        btVoltar5 = findViewById(R.id.btVoltar7);
        btAvancar5 = findViewById(R.id.btAvancar1);
        tvSenhaGerada = findViewById(R.id.tvSenhaGerada);
        tvAguardeChamada = findViewById(R.id.tvResultadoNota);
        btMenu = findViewById(R.id.btMenu7);


            btGerarSenha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int[] contadores = new int[5]; // Contadores para cada tipo de senha

                    int opcao = Integer.parseInt(edOpcao.getText().toString().trim());

                        if (opcao == 0) {
                            System.out.println("Encerrando o sistema...");
                            Toast.makeText(Exer_05.this,"Encerrando o sistema...", Toast.LENGTH_LONG).show();
                        }

                        if (opcao < 1 || opcao > 5) {
                            System.out.println("Opção inválida!");
                            Toast.makeText(Exer_05.this,"Opção inválida!", Toast.LENGTH_LONG).show();
                        }

                        contadores[opcao - 1]++; // Incrementa o contador para a opção escolhida

                        String prefixo = "";
                        switch (opcao) {
                            case 1: prefixo = "C"; break;
                            case 2: prefixo = "A"; break;
                            case 3: prefixo = "AC"; break;
                            case 4: prefixo = "AP"; break;
                            case 5: prefixo = "CP"; break;
                        }

                        String senha = prefixo + String.format("%03d", contadores[opcao - 1]);

                        //System.out.println("\nSenha gerada: " + senha);
                        tvSenhaGerada.setText("Senha gerada: " + senha);

                        //System.out.println("Por favor, aguarde para ser chamado!");
                        tvAguardeChamada.setText("Por favor, aguarde para ser chamado!");

                }
            });btLimpar5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edOpcao.setText("");
                    tvSenhaGerada.setText("");
                    tvAguardeChamada.setText("");
                }
            });
            btVoltar5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Exer_05.this, Exer_04.class);
                    startActivity(intent);
                }
            });
            btAvancar5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Exer_05.this, Exer_06.class);
                    startActivity(intent);
                }
            });
            btMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Exer_05.this, Tela_Principal.class);
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