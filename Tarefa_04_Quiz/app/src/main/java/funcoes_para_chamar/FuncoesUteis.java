package funcoes_para_chamar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tarefa_05_quiz.Resultados;

public class FuncoesUteis extends Resultados {//Aqui estamos criando uma classe chamada FuncoesUteis, vai guardar funções úteis para o aplicativo.

    public Context context;
    //Aqui dentro da classe, criamos uma variável chamada context.
    // Ela vai armazenar o Context que foi mencionado antes.
    // Essa variável é pública, ou seja, outras partes do código podem acessá-la.

    public FuncoesUteis(Context context){//Isso é um construtor.
        // Um construtor é um método especial que é chamado quando criamos um objeto dessa classe.
        //  Aqui, ele recebe um Context como parâmetro.

        this.context = context;//Dentro do construtor, estamos dizendo que a variável context da classe (this.context)
        // vai receber o valor do context que foi passado como parâmetro. O this é usado para diferenciar a variável da classe do parâmetro que tem o mesmo nome.

    }//Fim do construtor

    public static int acertos = 0;

    //Método para exibir uma mensagem de "Certa Resposta" usando um Toast.
    public void respostaCerta(){
        Toast.makeText(context,"Certa Resposta!", Toast.LENGTH_SHORT).show();
    }

    // Método para exibir uma mensagem de "Resposta Errada!" usando um Toast.
    public void respostaErrada(){
        Toast.makeText(context, "Resposta Errada!",Toast.LENGTH_SHORT).show();
    }

    //Método para desativar as alternativas B, C e D, caso escolha a alternativa A
    public void desativarB_C_D(RadioButton rbQuestaoB,RadioButton rbQuestaoC,RadioButton rbQuestaoD){
            rbQuestaoB.setEnabled(false);
            rbQuestaoC.setEnabled(false);
            rbQuestaoD.setEnabled(false);
    }

    //Método para desativar as alternativas A, C e D, caso escolha a alternativa B
    public void desativarA_C_D(RadioButton rbQuestaoA,RadioButton rbQuestaoC,RadioButton rbQuestaoD){
        rbQuestaoA.setEnabled(false);
        rbQuestaoC.setEnabled(false);
        rbQuestaoD.setEnabled(false);
    }

    //Método para desativar as alternativas A, B e D, caso escolha a alternativa C
    public void desativarA_B_D(RadioButton rbQuestaoA,RadioButton rbQuestaoB,RadioButton rbQuestaoD){
        rbQuestaoA.setEnabled(false);
        rbQuestaoB.setEnabled(false);
        rbQuestaoD.setEnabled(false);
    }

    //Método para desativar as alternativas A, B e C, caso escolha a alternativa D
    public void desativarA_B_C(RadioButton rbQuestaoA,RadioButton rbQuestaoB,RadioButton rbQuestaoC){
        rbQuestaoA.setEnabled(false);
        rbQuestaoB.setEnabled(false);
        rbQuestaoC.setEnabled(false);
    }

    // Método para navegar para outra tela (Activity)
    public void proximaTela(Class<?> activityClass){
        /* Aqui criou um método público (que pode ser acessado de qualquer lugar) chamado proximaTela.
            - Ele não retorna nenhum valor (void).
            - Ele recebe um parâmetro chamado activityClass, que é do tipo Class<?>.
            - Esse parâmetro representa a próxima tela (Activity) para onde queremos navegar.*/
        /*Class<?> Significa que o Class pode ser de qualquer tipo,
        Em outras palavras, é uma forma de dizer que o método proximaTela pode receber qualquer classe como parâmetro.
        O Class<?> permite que o método aceite qualquer classe como argumento, sem precisar saber de antemão qual será.*/

        Intent proximaTela = new Intent(context, activityClass); // Cria um Intent (mensageiro) chamado proximaTela, para abrir a próxima tela
        /*O Intent precisa de duas coisas:
        - context: É o local de onde estamos saindo (a tela atual).
        - activityClass: É a tela para onde queremos ir (a próxima tela).*/
        context.startActivity(proximaTela); // Inicia a próxima tela usando o Intent
    }
/*
    private int acertos = 0;
    private int erros = 0;

    public void incrementarAcertos(){
        acertos++;
    }
    public void incrementarErros(){
        erros++;
    }
    public int getAcertos(){
        return acertos;
    }
    public int getErros(){
        return erros;
    }
*/
}
