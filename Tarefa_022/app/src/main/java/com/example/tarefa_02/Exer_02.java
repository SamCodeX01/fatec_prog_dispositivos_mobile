package com.example.tarefa_02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exer_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exer02);

//
//        Scanner scan = new Scanner(System.in);
//        int  cateto1, cateto2;
//        double hipotenusa;
//
//        System.out.print("Digite o valor do cateto 1: ");
//        cateto1 = scan.nextInt();
//        System.out.print("Digite o valor do cateto 1: ");
//        cateto2 = scan.nextInt();
//
//        double cat1 = Math.pow(cateto1, 2);
//        double cat2 = Math.pow(cateto2, 2);
//        double soma = cat1+cat2;
//        hipotenusa = sqrt(soma);
//
//        System.out.println("A hipotenusa desse triângulo mede: " + hipotenusa);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btAvancar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}