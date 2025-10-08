package com.example.trabalho01_sistema_venda_passagem_rodoviarias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_05_Passageiros extends AppCompatActivity {

    private EditText edtNome, edtDocumento, edtDataNascimento, edtCPF;
    private Button btnSalvarPassageiro;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela05_passageiros);

        preferences = getSharedPreferences("PassageirosPref", MODE_PRIVATE);

        edtNome = findViewById(R.id.edtNome);
        edtDocumento = findViewById(R.id.edtDocumento);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        edtCPF = findViewById(R.id.edtCPF);
        btnSalvarPassageiro = findViewById(R.id.btnSalvarPassageiro);

        btnSalvarPassageiro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nome = edtNome.getText().toString();
//                String documento = edtDocumento.getText().toString();
//                String dataNascimento = edtDataNascimento.getText().toString();
//                String cpf = edtCPF.getText().toString();
//
//                if (nome.isEmpty() || documento.isEmpty() || dataNascimento.isEmpty() || cpf.isEmpty()) {
//                    Toast.makeText(Tela_05_Passageiros.this,
//                            "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Salvar passageiro
//                int passageiroCount = preferences.getInt("passageiro_count", 0);
//                SharedPreferences.Editor editor = preferences.edit();
//
//                editor.putString("nome_" + passageiroCount, nome);
//                editor.putString("documento_" + passageiroCount, documento);
//                editor.putString("dataNascimento_" + passageiroCount, dataNascimento);
//                editor.putString("cpf_" + passageiroCount, cpf);
//                editor.putInt("passageiro_count", passageiroCount + 1);
//                editor.apply();
//
//                // Salvar dados da viagem
//                editor.putString("origem", getIntent().getStringExtra("origem"));
//                editor.putString("destino", getIntent().getStringExtra("destino"));
//                editor.putString("data", getIntent().getStringExtra("data"));
//                editor.putString("horario", getIntent().getStringExtra("horario"));
//                editor.putFloat("valor", (float) getIntent().getDoubleExtra("valor", 0));
//                editor.apply();
//
//                Toast.makeText(Tela_05_Passageiros.this,
//                        "Passageiro salvo com sucesso!", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(Tela_05_Passageiros.this,
//                        Tela_06_Lista_Passageiros.class);
//                startActivity(intent);
//            }
@Override
public void onClick(View v) {
    String nome = edtNome.getText().toString();
    String documento = edtDocumento.getText().toString();
    String dataNascimento = edtDataNascimento.getText().toString();
    String cpf = edtCPF.getText().toString();

    if (nome.isEmpty() || documento.isEmpty() || dataNascimento.isEmpty() || cpf.isEmpty()) {
        Toast.makeText(Tela_05_Passageiros.this,
                "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        return;
    }

    // Salvar passageiro
    int passageiroCount = preferences.getInt("passageiro_count", 0);
    SharedPreferences.Editor editor = preferences.edit();

    editor.putString("nome_" + passageiroCount, nome);
    editor.putString("documento_" + passageiroCount, documento);
    editor.putString("dataNascimento_" + passageiroCount, dataNascimento);
    editor.putString("cpf_" + passageiroCount, cpf);
    editor.putInt("passageiro_count", passageiroCount + 1);

    // Salvar dados da viagem (AGORA CORRETAMENTE)
    editor.putString("origem", getIntent().getStringExtra("origem"));
    editor.putString("destino", getIntent().getStringExtra("destino"));
    editor.putString("data", getIntent().getStringExtra("data"));
    editor.putString("horario", getIntent().getStringExtra("horario"));
    editor.putString("empresa", getIntent().getStringExtra("empresa"));
    editor.putFloat("valor", (float) getIntent().getDoubleExtra("valor", 0));
    editor.putInt("assentos_reservados", getIntent().getIntExtra("assentos_reservados", 0));

    editor.apply();

    Toast.makeText(Tela_05_Passageiros.this,
            "Passageiro salvo com sucesso!", Toast.LENGTH_SHORT).show();

    // Limpar campos para próximo passageiro ou ir para lista
    edtNome.setText("");
    edtDocumento.setText("");
    edtDataNascimento.setText("");
    edtCPF.setText("");

    // Perguntar se quer adicionar mais passageiros ou ir para pagamento
    new AlertDialog.Builder(Tela_05_Passageiros.this)
            .setTitle("Passageiro Salvo")
            .setMessage("Deseja adicionar outro passageiro?")
            .setPositiveButton("Sim", (dialog, which) -> {
                // Fica na mesma tela para adicionar mais
            })
            .setNegativeButton("Ir para Pagamento", (dialog, which) -> {
                Intent intent = new Intent(Tela_05_Passageiros.this,
                        Tela_06_Lista_Passageiros.class);
                startActivity(intent);
            })
            .show();
}
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}