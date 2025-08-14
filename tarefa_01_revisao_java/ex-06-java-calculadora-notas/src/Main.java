/*6.  Faça um programa em Java para calcular e exibir a nota final de um aluno da Disciplina de Programação para Dispositivos Móveis:
Critério de Avaliação:
   At - Atividades do Semestre - 20%
   P1 - Prova 1     - 20%
   T1 - Trabalho 1 - 30%
   T2 - Trabalho 2 - 30%
   M  - Avaliação Multidisciplinar - 10%
   Nota Final = (At * 0.20 + P1 * 0.20 + T1 * 0.30 + T2 * 0.30 + M * 0.10, 10)
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora de Nota Final");
        System.out.println("------------------------");

        System.out.print("Digite a nota das Atividades (At): ");
        double at = scanner.nextDouble();

        System.out.print("Digite a nota da Prova 1 (P1): ");
        double p1 = scanner.nextDouble();

        System.out.print("Digite a nota do Trabalho 1 (T1): ");
        double t1 = scanner.nextDouble();

        System.out.print("Digite a nota do Trabalho 2 (T2): ");
        double t2 = scanner.nextDouble();

        System.out.print("Digite a nota Multidisciplinar (M): ");
        double m = scanner.nextDouble();

        // Cálculo da nota final
        double notaFinal = (at * 0.20) + (p1 * 0.20) + (t1 * 0.30) + (t2 * 0.30) + (m * 0.10);

        System.out.println("\nNota Final: " + notaFinal);

    }
}