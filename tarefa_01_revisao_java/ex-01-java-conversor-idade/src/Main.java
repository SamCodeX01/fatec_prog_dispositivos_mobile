import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int diaNascimento, mesNascimento, anoNascimento;
        int idade, anos, dias, meses = 0, anoAtual;

        anoAtual = java.time.Year.now().getValue();

        System.out.print("Digite o dia do seu nascimento: ");
        diaNascimento = scan.nextInt();
        if(diaNascimento < 1 || diaNascimento > 31){
            System.out.println("Digite um dia válido!");
            return;
        }
        System.out.print("Digite o mês do nascimento: ");
        mesNascimento = scan.nextInt();
        if(mesNascimento<1 || mesNascimento > 12){
            System.out.println("Digite um mês válido!");
            return;
        }
        System.out.print("Digite o ano do seu nascimento: ");
        anoNascimento = scan.nextInt();

        idade = anoAtual-anoNascimento;

        dias = (idade*365)  + ((mesNascimento-1)*30)+ diaNascimento;
        meses = (12*idade) + mesNascimento;

        System.out.println("\nVocê tem: \n" + idade + " anos.");
        System.out.println(meses + " meses.");
        System.out.println(dias + " dias vividos.");
    }
}