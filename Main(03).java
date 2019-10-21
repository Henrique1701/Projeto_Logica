import java.util.*;
/*import java.util.Queue;
import java.util.Scanner;*/

class Funcoes{
    public boolean FNC(String proposicao){ //Proposicao sem espacos
        boolean res = true;
        int tamanho = proposicao.length();

        for(int i = 0; i < tamanho; i++){
            char atual = proposicao.charAt(i);
            if(i == 0){ //Para quando o caractere for o primeiro, e nao querer acessar uma posicao fora do array
                if(atual != '(' && atual != '~'){
                    res = false;
                }
            } else if(i == tamanho-1){ //para quando o caractere for o ultimo, e nao querer acessar uma posicao fora do array
                if(atual != ')'){
                    res = false;
                }
            } else { //Para o restante dos caracteres
                char proximo = proposicao.charAt(i+1);
                char anterior = proposicao.charAt(i-1);
                if(atual == '<' || atual == '>'){
                    res = false;
                } else if(atual == '&'){
                    if(anterior != ')' || proximo != '(') {
                        res = false;
                    }
                } else if (atual == 'P' || atual == 'Q' || atual == 'R' || atual == 'S'){
                    if(anterior != '(' && anterior != '~' && anterior != 'v'){
                        res = false;
                    } else if (proximo != ')' && proximo != 'v'){
                        res = false;
                    }
                } else if (atual == 'v' ){
                    if(anterior != 'P' && anterior != 'Q' && anterior != 'R' && anterior != 'S' ){
                        res = false;
                    } else if (proximo != 'P' && proximo != 'Q' && proximo != 'R' && proximo != 'S' && proximo != '~'){
                        res = false;
                    }
                } else if (atual == '~' ){
                    if(anterior != 'P' && anterior != 'Q' && anterior != 'R' && anterior != 'S' &&
                            anterior != '(' && anterior != 'v'){
                        res = false;
                    } else if (proximo != 'P' && proximo != 'Q' && proximo != 'R' && proximo != 'S'){
                        res = false;
                    }
                } else if (atual == '('){
                    if (anterior != '&' ){
                        res = false;
                    } else if (proximo != 'P' && proximo != 'Q' && proximo != 'R' && proximo != 'S' &&
                            proximo != '~'){
                        res = false;
                    }
                } else if (atual == ')'){
                    if (anterior != 'P' && anterior != 'Q' && anterior != 'R' && anterior != 'S' &&
                            anterior != ')'){
                        res = false;
                    } else if (proximo != ')' && proximo != '&' && proximo != 'v'){
                        res = false;
                    }
                }
            }

            if(res == false){
                i = tamanho;
            }
        }

        return res;
    }

    public boolean Horn(String proposicao){
        boolean res = true;
        String[] clausulas = proposicao.split("&"); //Separa todas as clausulas e armazenam em um array

        for(int i = 0; i < clausulas.length; i++){
            int quantLiterais = 0, quantNegacoes = 0;
            for(int j = 0; j < clausulas[i].length(); j++){
                if(clausulas[i].charAt(j) == '~'){
                    quantNegacoes++;
                } else if (clausulas[i].charAt(j) < 80 || clausulas[i].charAt(j) > 83){ //P = 80, Q = 81, R = 82, S = 83
                    quantLiterais++;
                }
            }
            if(quantLiterais - quantNegacoes > 1){
                res = false;
                i = clausulas.length;
            }
        }

        return res;
    }

    public boolean Satisfativel(String proposicao){ //Se encontrar uma clausula vazia é satisfativel
        boolean res = true;
        Queue<String> unitarias = new LinkedList<>(); //Irei usar uma lista para armazenar as clausulas unitarias, isso vai me ajudar a controlar quem eu ja verifiquei
        Queue<String> pClausulas = new LinkedList<>(); //Essa e as tres listas seguimtes servira para armazenar as clausula que contem o literal em questao
        Queue<String> qClausulas = new LinkedList<>();
        Queue<String> rClausulas = new LinkedList<>();
        Queue<String> sClausulas = new LinkedList<>();
        String[] clausulas = proposicao.split("&"); //Cria um array de clausulas
        ArrayList<String> clausulasVector = new ArrayList<>();

        String clausulaRemovida = pClausulas.poll();


        for(int i = 0; i < clausulas.length; i++){
            clausulasVector.add(clausulas[i]);
            if(clausulas[i].length() == 3 || clausulas[i].length() == 4){
                unitarias.add(clausulas[i]);
            }
        }
        if(unitarias.isEmpty() == true) { //Entre as clausulas nao existe nenhuma unitaria logo, nao e satisfativel
            return true;
        }
        while (unitarias.isEmpty() == false){ //Na hora de retirar os literais RETIRAR os operadores tambem
            String auxUnitarias = unitarias.remove();
            if(auxUnitarias.length() == 3){ //A clausula so possui um literal "positivo"
                char literal = auxUnitarias.charAt(1);
                int quantidadeClausulas = clausulasVector.size();
                for(int  k = 0; k < quantidadeClausulas; k++){
                    String auxClausula = "";
                    String clausulaAtual = clausulasVector.get(k);
                    for(int l = 0; l < clausulaAtual.length(); l++){
                        if(clausulaAtual.charAt(l) == '~'){
                            if(clausulaAtual.charAt(l+1) != literal){
                                auxClausula += clausulaAtual.charAt(l);
                                auxClausula += clausulaAtual.charAt(l);
                                l++;
                            } else {
                                l++;
                            }
                        } else if (clausulaAtual.charAt(l) == 'v') {
                            if(auxClausula.charAt(auxClausula.length()-1) != 'v'){
                                auxClausula += clausulaAtual.charAt(l);
                            }
                        } else if(clausulaAtual.charAt(l) == literal){ //Se entrar aqui preciso eliminar a clausula toda
                            auxClausula = "";
                            l = clausulaAtual.length();
                        } else {
                            //PAREI AQUI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        }
                        /*if(clausulaAtual.charAt(l) != literal){
                            if(clausulaAtual.charAt(l) == '~'){
                                if (clausulaAtual.charAt(l+1) != literal){
                                    auxClausula += clausulaAtual.charAt(l); //E uma negacao de uma literal diverente da literal que estamos avaliando
                                }
                            } else {
                                auxClausula += clausulaAtual.charAt(l); //Nesse caso e um digito diferente do literal que estamos avaliando
                            }
                        } else if(clausulaAtual.charAt(l-1) != '~'){
                            auxClausula += clausulaAtual.charAt(l);
                        }*/
                    }
                    if(auxClausula == "()"){
                        return false;
                    } else if(auxClausula != clausulaAtual){
                        clausulasVector.add(auxClausula);
                        quantidadeClausulas++;
                    }
                }
            } else { //A clausula so possui um literal "negativo"

            }
        }

        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Funcoes funcoes = new Funcoes();

        int quantidade = in.nextInt();
        in.nextLine();
        for (int k = 1; k < quantidade+1; k++) {
            String proposicao = in.nextLine();
            String[] propAux = proposicao.split(" ");
            proposicao = "";
            for (int i = 0; i < propAux.length; i++) {
                proposicao += propAux[i];
            }

            System.out.println(funcoes.Horn(proposicao));

            System.out.println("Problema #"+k);
            if(funcoes.FNC(proposicao) == false){
                System.out.println("Não está na FNC.");
            } else {
                if(funcoes.Horn(proposicao) == false){
                    System.out.println("Nem todas as cláusulas são de Horn.");
                } else { //Verificar se é satisfativel

                }
            }
            System.out.println();
        }



        /*String proposicao = "(Q v ~P v ~P v ~S v ~R) & (~R) & (Q v ~Q v ~Q v ~Q v ~S) & (~P v R v ~S v ~P v ~R) & (S v ~R v ~P) & (Q v ~S) & (~Q v P v ~R) & (R v ~R v ~S v ~R) & (~R v ~Q v ~R v Q)";
        String[] propAux = proposicao.split(" ");
        proposicao = "";
        for (int i = 0; i < propAux.length; i++) {
            proposicao += propAux[i];
        }
        System.out.println(funcoes.FNC(proposicao));*/


        /*String aux = in.nextLine();
        String[] aux2 = aux.split(" ");
        aux = "";
        for(int i = 0; i < aux2.length; i++){
            aux += aux2[i];
        }
        String[] aux3 = aux.split("&");
        System.out.println();*/

        /*  Parei no metodo satisfativel
            Estava fazendo as condicoes para poder eliminar os literais
            Porem devo me atentar para tirar os operadores "v"
         */

    }
}
