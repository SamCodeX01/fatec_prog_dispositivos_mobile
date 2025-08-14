import java.util.Scanner;

/*1. Faça um programa que leia a idade de uma pessoa, expressa em anos, meses e dias e
mostre-a expressa em dias. Considere o ano com 365 dias e o mês com 30.
(Ex: 1 anos, 1 meses e 10 dias = 405 dias.*/

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int diaNascimento, mesNascimento, anoNascimento;
        int idade, anos, dias, meses = 0, anoAtual;

        anoAtual = java.time.Year.now().getValue();

        System.out.print("Digite o dia do seu nascimento: ");
        diaNascimento = scan.nextInt();
        System.out.print("Digite o mês do nascimento: ");
        mesNascimento = scan.nextInt();
        System.out.print("Digite o ano do seu nascimento: ");
        anoNascimento = scan.nextInt();

        idade = anoAtual-anoNascimento;

        if(mesNascimento<1 || mesNascimento > 12){
            System.out.println("Digite um mês válido!");
            return;
        }
        if(diaNascimento < 1 || diaNascimento > 31){
            System.out.println("Digite um dia válido!");
            return;
        }

        dias = (idade*365)  + ((mesNascimento-1)*30)+ diaNascimento;
        meses = (12*idade) + mesNascimento;

        System.out.println("\nVocê tem: \n" + idade + " anos.");
        System.out.println(meses + " meses.");
        System.out.println(dias + " dias vividos.");
    }
}