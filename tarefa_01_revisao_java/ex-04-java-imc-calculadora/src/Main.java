/*4. Faça um programa para calcular e exibir o IMC e o Situação de uma pessoa
O Índice de Massa Corporal (IMC) é uma medida utilizada para avaliar se uma pessoa está dentro de uma faixa de peso considerada saudável, em relação à sua altura.
O IMC é calculado pela divisão do peso da pessoa (em quilogramas) pelo quadrado da sua altura (em metros). A fórmula é:
      IMC =  (peso) / (altura)2

O valor do IMC indica a situação em que a pessoa se encontra:
- Abaixo de 18,5: Abaixo do peso
- Entre 18,5 e 24,9: Peso normal
- Entre 25 e 29,9: Sobrepeso
- Entre 30 e 34,9: Obesidade grau I
- Entre 35 e 39,9: Obesidade grau II
- 40 ou mais: Obesidade grau III ou mórbida*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double imc, peso, altura;

        System.out.print("Digite o seu peso: ");
        peso = scan.nextDouble();
        System.out.print("Digite a sua altura: ");
        altura = scan.nextDouble();
        imc = (peso / (altura * altura));
        System.out.printf("Seu imc calculado: %.2f%n", imc);

        if (imc < 18.5) {
            System.out.println("Abaixo do peso!");
        } else if (imc >= 18.5 && imc <= 24.9) {
            System.out.println("Peso normal");
        } else if (imc >= 25 && imc <= 29.9) {
            System.out.println("Sobrepeso");
        } else if (imc >= 30 && imc < 34.9) {
            System.out.println("Obesidade grau I");
        } else if (imc >= 35 && imc <= 39.9) {
            System.out.println("Obesidade grau II");
        } else if (imc >= 40) {
            System.out.println("Obesidade grau III ou mórbida*/");
        }
    }

}