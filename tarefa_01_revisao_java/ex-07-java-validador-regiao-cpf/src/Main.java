import java.util.Scanner;

/*7. Faça um programa para receber um CPF, calcular seu controle e exibir o nome da região onde foi emitido e o controle do CPF
   Último número do corpo do CPF | Região onde foi emitido

    0| Rio Grande do Sul
    1| Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins
    2| Amazonas, Pará, Roraima, Amapá, Acre e Rondônia
    3| Ceará, Maranhão e Piauí
    4| Paraíba, Pernambuco, Alagoas e Rio Grande do Norte
    5| Bahia e Sergipe
    6| Minas Gerais
    7| Rio de Janeiro e Espírito Santo
    8| São Paulo
    9| Paraná e Santa Catarina
*/

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String cpf = "";

        System.out.print("Digite o seu cpf: ");
        cpf = scan.next().replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            System.out.println("CPF inválido! Digite apenas 11 digitos numéricos!");
            return;
        }

        //Cálculo do primeiro digito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);//soma = 0 recebe (o primeiro valor do cpf digitado) * (10 - i que vale zero inicialmente)
        }
        int digito1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
        //"Se o resto da divisão de soma por 11 for menor que 2, use 0; caso contrário, use 11 - resto."

        // Cálculo do 2º dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

        // Validação
        boolean valido = (digito1 == Character.getNumericValue(cpf.charAt(9))) &&
                (digito2 == Character.getNumericValue(cpf.charAt(10)));

        System.out.println(valido ? "CPF válido!" : "CPF inválido (dígitos verificadores incorretos)");

         if(valido){
            // Determinar região de emissão (8º dígito)
            int regiao = Character.getNumericValue(cpf.charAt(8));
            System.out.println("Região de emissão: " + getRegiao(regiao));

            System.out.println("Dígitos verificadores: " + digito1 + digito2);
         } else{
        System.out.println("CPF inválido (dígitos verificadores incorretos)");
        }

}

        private static String getRegiao(int codigo) {
            return switch (codigo) {
                case 0 -> "Rio Grande do Sul";
                case 1 -> "Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins";
                case 2 -> "Amazonas, Pará, Roraima, Amapá, Acre e Rondônia";
                case 3 -> "Ceará, Maranhão e Piauí";
                case 4 -> "Paraíba, Pernambuco, Alagoas e Rio Grande do Norte";
                case 5 -> "Bahia e Sergipe";
                case 6 -> "Minas Gerais";
                case 7 -> "Rio de Janeiro e Espírito Santo";
                case 8 -> "São Paulo";
                case 9 -> "Paraná e Santa Catarina";
                default -> "Região desconhecida";
            };
    }
}
