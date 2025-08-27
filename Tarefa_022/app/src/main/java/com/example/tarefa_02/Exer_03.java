package com.example.tarefa_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer03);
//
//
//        Scanner scan = new Scanner(System.in);
//        Random random = new Random();
//
//        System.out.print("Digite a quantidade de numeros: ");
//        int n = scan.nextInt();
//
//        if(n < 1 || n > 100){
//            System.out.println("Erro: O número digitado deve estar entre 1 e 100");
//            return;
//        }
//
//        int[] numeros = new int[n];//O array de numeros esta armazenando n
//
//        // Passo 1: Gerar N números aleatórios sem repetição
//        for (int i = 0; i < n; i++) {
//            int novoNumero;
//            boolean repetido;
//
//            do {
//                repetido = false;
//                novoNumero = random.nextInt(100); // Gera de 0 a 99
//
//                // Verifica se o número já existe no array
//                for (int j = 0; j < i; j++) {
//                    if (numeros[j] == novoNumero) {
//                        repetido = true;
//                        break;
//                    }
//                }
//            } while (repetido);
//
//            numeros[i] = novoNumero;
//        }
//
//        // Aqui esta usando ordenação simples com selection sort
//        for (int i = 0; i < n - 1; i++) {
//            int indiceMenor = i;
//
//            // Encontra o menor número na parte não ordenada
//            for (int j = i + 1; j < n; j++) {
//                if (numeros[j] < numeros[indiceMenor]) {
//                    indiceMenor = j;
//                }
//            }
//
//            // Troca o menor número com a posição atual
//            int temp = numeros[i];
//            numeros[i] = numeros[indiceMenor];
//            numeros[indiceMenor] = temp;
//        }
//
//        // Aqui mostra os números ordenados
//        System.out.println("\nNúmeros em ordem crescente:");
//        for (int num : numeros) {
//            System.out.print(num + " ");
//        }
//

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btAvancar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}