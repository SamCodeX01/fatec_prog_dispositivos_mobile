package com.example.tarefa_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_06 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer06);


//        public static void main(String[] args) {
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Calculadora de Nota Final");
//            System.out.println("------------------------");
//
//            System.out.print("Digite a nota das Atividades (At): ");
//            double at = scanner.nextDouble();
//
//            System.out.print("Digite a nota da Prova 1 (P1): ");
//            double p1 = scanner.nextDouble();
//
//            System.out.print("Digite a nota do Trabalho 1 (T1): ");
//            double t1 = scanner.nextDouble();
//
//            System.out.print("Digite a nota do Trabalho 2 (T2): ");
//            double t2 = scanner.nextDouble();
//
//            System.out.print("Digite a nota Multidisciplinar (M): ");
//            double m = scanner.nextDouble();
//
//            // Cálculo da nota final
//            double notaFinal = (at * 0.20) + (p1 * 0.20) + (t1 * 0.30) + (t2 * 0.30) + (m * 0.10);
//
//            System.out.println("\nNota Final: " + notaFinal);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}