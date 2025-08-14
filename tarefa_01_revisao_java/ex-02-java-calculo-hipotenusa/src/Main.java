//2. Faça um programa para calcular e exibir
// a medida da hipotenusa de um triângulo retângulo.

import java.util.Scanner;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int  cateto1, cateto2;
        double hipotenusa;

        System.out.print("Digite o valor do cateto 1: ");
        cateto1 = scan.nextInt();
        System.out.print("Digite o valor do cateto 1: ");
        cateto2 = scan.nextInt();

        double cat1 = Math.pow(cateto1, 2);
        double cat2 = Math.pow(cateto2, 2);
        double soma = cat1+cat2;
        hipotenusa = sqrt(soma);

        System.out.println("A hipotenusa desse triângulo mede: " + hipotenusa);


    }
}