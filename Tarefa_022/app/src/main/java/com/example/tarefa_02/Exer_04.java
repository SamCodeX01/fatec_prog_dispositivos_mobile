package com.example.tarefa_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer04);

//        Scanner scan = new Scanner(System.in);
//        double imc, peso, altura;
//
//        System.out.print("Digite o seu peso: ");
//        peso = scan.nextDouble();
//        System.out.print("Digite a sua altura: ");
//        altura = scan.nextDouble();
//        imc = (peso / (altura * altura));
//        System.out.printf("Seu imc calculado: %.2f%n", imc);
//
//        if (imc < 18.5) {
//            System.out.println("Abaixo do peso!");
//        } else if (imc >= 18.5 && imc <= 24.9) {
//            System.out.println("Peso normal");
//        } else if (imc >= 25 && imc <= 29.9) {
//            System.out.println("Sobrepeso");
//        } else if (imc >= 30 && imc < 34.9) {
//            System.out.println("Obesidade grau I");
//        } else if (imc >= 35 && imc <= 39.9) {
//            System.out.println("Obesidade grau II");
//        } else if (imc >= 40) {
//            System.out.println("Obesidade grau III ou mórbida*/");
//        }
//    }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}