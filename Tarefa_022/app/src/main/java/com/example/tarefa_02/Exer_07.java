package com.example.tarefa_02;

import android.annotation.SuppressLint;
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
                    System.out.println("CPF inválido! Digite apenas 11 digitos numéricos!");
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
                Toast.makeText(Exer_07.this,(valido ? "CPF válido!" : "CPF inválido (dígitos verificadores incorretos)"),Toast.LENGTH_LONG).show();

                if(valido){
                    // Determinar região de emissão (8º dígito)
                    int regiao = Character.getNumericValue(cpf.charAt(8));
                    //System.out.println("Região de emissão: " + getRegiao(regiao));

                    //System.out.println("Dígitos verificadores: " + digito1 + digito2);
                    Toast.makeText(Exer_07.this,("Dígitos verificadores: " + digito1 + digito2),Toast.LENGTH_LONG).show();


                } else{
                    //System.out.println("CPF inválido (dígitos verificadores incorretos)");
                    Toast.makeText(Exer_07.this,"CPF inválido (dígitos verificadores incorretos)",Toast.LENGTH_LONG).show();
                }
            }
            public void getRegiao(int codigo) {
                switch (codigo) {
                    case 0:
                        tvMostrarRegiao.setText("Rio Grande do Sul");
                        break;
                    case 1:
                        tvMostrarRegiao.setText("Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins");
                        break;
                    case 2:
                        tvMostrarRegiao.setText("Amazonas, Pará, Roraima, Amapá, Acre e Rondônia");
                        break;
                    case 3:
                        tvMostrarRegiao.setText("Ceará, Maranhão e Piauí");
                        break;
                    case 4:
                        tvMostrarRegiao.setText("Paraíba, Pernambuco, Alagoas e Rio Grande do Norte");
                        break;
                    case 5:
                        tvMostrarRegiao.setText("Bahia e Sergipe");
                        break;
                    case 6:
                        tvMostrarRegiao.setText("Minas Gerais");
                        break;
                    case 7:
                        tvMostrarRegiao.setText("Rio de Janeiro e Espírito Santo");
                        break;
                    case 8:
                        tvMostrarRegiao.setText("São Paulo");
                        break;
                    case 9:
                        tvMostrarRegiao.setText("Paraná e Santa Catarina");
                        break;
                    default:
                        tvMostrarRegiao.setText("Região desconhecida");
                        break;
                }
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}