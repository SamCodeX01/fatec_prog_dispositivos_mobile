import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.random;

/*3. Faça um programa para gerar
N números aleatórios [entre 0 e 99] sem repetição;
exibir os números gerados em ordem crescente.
Onde:
      N - representa a quantidade de números solicitado pelo usuário.*/
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

       System.out.print("Digite um numero: ");
        int n = scan.nextInt();

        int x = random.nextInt(n);
        System.out.println(x);

        for(int i = 1; i < n; i++){
            System.out.println(x);
        }


    }
}