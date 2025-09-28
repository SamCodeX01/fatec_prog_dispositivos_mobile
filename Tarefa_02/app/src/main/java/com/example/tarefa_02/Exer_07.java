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

public class Exer_07 extends AppCompatActivity {

    EditText edCpf;
    TextView tvMostrarValidacao, tvMostrarRegiao;
    Button btLimpar7, btVoltar7, btMenu7, btAvancarx, btValidarCpf;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer07);

        edCpf = findViewById(R.id.edCpf);
        tvMostrarValidacao = findViewById(R.id.tvMostrarValidacao);
        tvMostrarRegiao = findViewById(R.id.tvMostrarRegiao);
        btValidarCpf = findViewById(R.id.btValidarCpf);
        btLimpar7 = findViewById(R.id.btLimpar7);
        btVoltar7 = findViewById(R.id.btVoltar7);
        btMenu7 = findViewById(R.id.btMenu7);
        btAvancarx = findViewById(R.id.btAvancarx);

        btValidarCpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = "";

               // System.out.print("Digite o seu cpf: ");
              //  cpf = scan.next().replaceAll("[^0-9]", "");

                //cateto1 = Double.parseDouble(edCpf.getText().toString().trim().replaceAll("[^0-9]", ""));
                cpf = edCpf.getText().toString().trim().replaceAll("[^0-9]", "");

                if (cpf.length() != 11) {
                   // System.out.println("CPF inválido! Digite apenas 11 digitos numéricos!");
                    Toast.makeText(Exer_07.this,"CPF inválido! Digite apenas 11 digitos numéricos!",Toast.LENGTH_LONG).show();
                    return;
                }

                //Cálculo do primeiro digito verificador
                int soma = 0;
                for (int i = 0; i < 9; i++) {
                    soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);//soma = 0 recebe (o primeiro valor do cpf digitado) * (10 - i que vale zero inicialmente)
                }
                int digito1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
                //"Se o resto da divisão de soma por 11 for menor que 2, use 0; caso contrário, use 11 - resto."

                // Cálculo do 2º dígito verificador
                soma = 0;
                for (int i = 0; i < 10; i++) {
                    soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
                }
                int digito2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

                // Validação
                boolean valido = (digito1 == Character.getNumericValue(cpf.charAt(9))) &&
                        (digito2 == Character.getNumericValue(cpf.charAt(10)));

                //System.out.println(valido ? "CPF válido!" : "CPF inválido (dígitos verificadores incorretos)");
               // Toast.makeText(Exer_07.this,(valido ? "CPF válido!" : "CPF inválido (dígitos verificadores incorretos)"),Toast.LENGTH_LONG).show();

                if(valido){
                    // Determinar região de emissão (8º dígito)
                    int regiao = Character.getNumericValue(cpf.charAt(8));
                    //System.out.println("Região de emissão: " + getRegiao(regiao));
                    String regiaoStr = getRegiao(regiao);
                    tvMostrarRegiao.setText("Região de emissão (" + regiao + "): " + regiaoStr);
                   // tvMostrarRegiao.setText("Região de emissão: " + getRegiao(regiao));
                    //tvMostrarRegiao.setText("Região de emissão: " + regiao);
                    //System.out.println("Dígitos verificadores: " + digito1 + digito2);
                    //Toast.makeText(Exer_07.this,("Dígitos verificadores: " + digito1 + digito2),Toast.LENGTH_LONG).show();
                    tvMostrarValidacao.setText("Dígitos verificadores: " + digito1 + digito2);

                }
                else{
                    //System.out.println("CPF inválido (dígitos verificadores incorretos)");
                    Toast.makeText(Exer_07.this,"CPF inválido (dígitos verificadores incorretos)",Toast.LENGTH_LONG).show();
                }
            }
            public String getRegiao(int codigo) {
                //String return;
                switch (codigo) {
                    case 0:
                        return "Rio Grande do Sul";
                    case 1:
                        return "Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins";
                    case 2:
                        return "Amazonas, Pará, Roraima, Amapá, Acre e Rondônia";
                    case 3:
                        return "Ceará, Maranhão e Piauí";
                    case 4:
                        return "Paraíba, Pernambuco, Alagoas e Rio Grande do Norte";
                    case 5:
                        return "Bahia e Sergipe";
                    case 6:
                        return "Minas Gerais";
                    case 7:
                        return "Rio de Janeiro e Espírito Santo";
                    case 8:
                        return "São Paulo";
                    case 9:
                        return "Paraná e Santa Catarina";
                    default:
                        return "Região desconhecida";
                }
                //tvMostrarRegiao.setText("Região de emissão: " + return);
            }
        });

        btLimpar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMostrarRegiao.setText("");
                tvMostrarValidacao.setText("");
                edCpf.setText("");
            }
        });
        btVoltar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_07.this, Exer_06.class);
                startActivity(intent);
            }
        });
        btAvancarx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_07.this, Exer_01.class);
                startActivity(intent);
            }
        });
        btMenu7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exer_07.this, Tela_Principal.class);
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