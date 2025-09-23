package com.example.tarefa_05_quiz;

public class Resultados {
    public static int acertos = 0;
    public static int erros = 0;
}
/*Em Java, uma classe abstrata serve como um "modelo" ou "esboço" para outras classes.
Ela não pode ser instanciada diretamente (ou seja, você não pode criar um objeto dela),
mas pode ser usada como base para outras classes que herdam dela.
A ideia principal é definir comportamentos e atributos comuns que serão compartilhados por várias classes,
mas deixar alguns detalhes para serem implementados pelas classes filhas.
* */

/*Como é abstrata, não pode ser instanciada diretamente.
Ou seja, você não pode fazer new Resultados().*/

/*
 * Como é static, a variável pertence à classe Resultados e não a instâncias dela.
 * Isso significa que o valor de acertos é compartilhado por todas as classes que usam Resultados.*/

/*A classe Resultados pode ser usada como uma base
para outras classes que precisam compartilhar a variável acertos.*/


/* Use uma classe abstrata quando você quer compartilhar código comum entre classes relacionadas.

 *  Use uma interface quando você quer definir um contrato que várias classes não relacionadas devem seguir.*/