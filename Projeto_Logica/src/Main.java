import java.util.Scanner;

class Funcoes{
    public boolean FNC(String proposicao){ //Proposicao sem espacos
        boolean res = true;
        int tamanho = proposicao.length();

        for(int i = 0; i < tamanho; i++){
            if(i == 0){ //Para quando o caractere for o primeiro, e nao querer acessar uma posicao fora do array
                if(proposicao.charAt(i) != '(' && proposicao.charAt(i) != '~'){
                    res = false;
                }
            } else if(i == tamanho-1){ //para quando o caractere for o ultimo, e nao querer acessar uma posicao fora do array
                if(proposicao.charAt(i) != ')'){
                    res = false;
                }
            } else { //Para o restante dos caracteres
                if(proposicao.charAt(i) == '&'){
                    if(proposicao.charAt(i-1) != ')' || proposicao.charAt(i+1) != '(') {
                        res = false;
                    }
                } else if (proposicao.charAt(i) == 'P' || proposicao.charAt(i) == 'Q' || proposicao.charAt(i) == 'R' || proposicao.charAt(i) == 'S'){
                    if(proposicao.charAt(i-1) != '(' && proposicao.charAt(i-1) != '~' && proposicao.charAt(i-1) != 'V'){
                        res = false;
                    } else if (proposicao.charAt(i+1) != ')' && proposicao.charAt(i+1) != 'V'){
                        res = false;
                    }
                } //CONTINUAR AQUI !!!!
            }
        }

        return res;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String teste  = "Eu nao sei o que digitar"; //Colocar a entrada para retirar os espacos.
        String [] aux = teste.split(" "); //Cria um array de string pegando as palavras separas por espaco
        teste = "";
        for(int i = 0; i < aux.length; i++){
            teste += aux[i];
        }

        System.out.println(teste);


        /*Estava fazendo o metodo FNC da classe Funcoes
        * Mais Especificamente avaliando quando fosse V */
    }
}
