/*5. Faça um programa para gerar senhas de atendimento.
    Exemplo:
                   1 - Caixa
                   2 - Atendimento
                   3 - Abertura de Conta
                   4 - Atendimento Preferencial
                   5- Caixa Preferencial*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] contadores = new int[5]; // Contadores para cada tipo de senha

        while (true) {
            System.out.println("\nEscolha o tipo de atendimento:");
            System.out.println("1 - Caixa");
            System.out.println("2 - Atendimento");
            System.out.println("3 - Abertura de Conta");
            System.out.println("4 - Atendimento Preferencial");
            System.out.println("5 - Caixa Preferencial");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Encerrando o sistema...");
                break;
            }

            if (opcao < 1 || opcao > 5) {
                System.out.println("Opção inválida!");
                continue;
            }

            contadores[opcao - 1]++; // Incrementa o contador para a opção escolhida

            String prefixo = "";
            switch (opcao) {
                case 1: prefixo = "C"; break;
                case 2: prefixo = "A"; break;
                case 3: prefixo = "AC"; break;
                case 4: prefixo = "AP"; break;
                case 5: prefixo = "CP"; break;
            }

            String senha = prefixo + String.format("%03d", contadores[opcao - 1]);
            System.out.println("\nSenha gerada: " + senha);
            System.out.println("Por favor, aguarde para ser chamado!");
        }

    }

}