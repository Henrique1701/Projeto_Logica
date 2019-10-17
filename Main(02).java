import java.util.*;
/*import java.util.Queue;
import java.util.Scanner;*/

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
                if(proposicao.charAt(i) == '<' || proposicao.charAt(i) == '>'){
                    res = false;
                } else if(proposicao.charAt(i) == '&'){
                    if(proposicao.charAt(i-1) != ')' || proposicao.charAt(i+1) != '(') {
                        res = false;
                    }
                } else if (proposicao.charAt(i) == 'P' || proposicao.charAt(i) == 'Q' || proposicao.charAt(i) == 'R' || proposicao.charAt(i) == 'S'){
                    if(proposicao.charAt(i-1) != '(' && proposicao.charAt(i-1) != '~' && proposicao.charAt(i-1) != 'v'){
                        res = false;
                    } else if (proposicao.charAt(i+1) != ')' && proposicao.charAt(i+1) != 'v'){
                        res = false;
                    }
                } else if (proposicao.charAt(i) == 'v' ){
                    if(proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'Q' && proposicao.charAt(i-1) != 'R' && proposicao.charAt(i-1) != 'S' ){
                        res = false;
                    } else if (proposicao.charAt(i+1) != 'P' && proposicao.charAt(i+1) != 'Q' && proposicao.charAt(i+1) != 'R' && proposicao.charAt(i+1) != 'S' && proposicao.charAt(i+1) != '~'){
                        res = false;
                    }
                } else if (proposicao.charAt(i) == '~' ){
                    if(proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'Q' && proposicao.charAt(i-1) != 'R' && proposicao.charAt(i-1) != 'S' &&
                            proposicao.charAt(i-1) != '(' && proposicao.charAt(i-1) != 'v'){
                        res = false;
                    } else if (proposicao.charAt(i+1) != 'P' && proposicao.charAt(i+1) != 'Q' && proposicao.charAt(i+1) != 'R' && proposicao.charAt(i+1) != 'S'){
                        res = false;
                    }
                } else if (proposicao.charAt(i) == '('){
                    if (proposicao.charAt(i-1) != '&' ){
                        res = false;
                    } else if (proposicao.charAt(i+1) != 'P' && proposicao.charAt(i+1) != 'Q' && proposicao.charAt(i+1) != 'R' && proposicao.charAt(i+1) != 'S' &&
                            proposicao.charAt(i+1) != '~'){
                        res = false;
                    }
                } else if (proposicao.charAt(i) == ')'){
                    if (proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'Q' && proposicao.charAt(i-1) != 'R' && proposicao.charAt(i-1) != 'S' &&
                            proposicao.charAt(i-1) != ')'){
                        res = false;
                    } else if (proposicao.charAt(i+1) != ')' && proposicao.charAt(i+1) != '&' && proposicao.charAt(i+1) != 'v'){
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
        Queue<String> unitarias = new LinkedList<String>();
        String[] clausulas = proposicao.split("&");

        for(int i = 0; i < clausulas.length; i++){
            for(int j = 0; j < clausulas[i].length(); j++){
                if(clausulas[i].charAt(j) >= 80 && clausulas[i].charAt(j) <= 83){
                    if(clausulas[i].charAt(j+1) == ')' && (clausulas[i].charAt(j-1) == '(' || clausulas[i].charAt(j-2) == '(')){ //Verifica se é uma clausula unitaria...
                        unitarias.add(clausulas[i]);
                    }
                }
            }
            if(unitarias.isEmpty() == true){ //Entre as clausulas nao existe nenhuma unitaria logo, nao e satisfativel
                return false;
            } else {
                while (unitarias.isEmpty() == false){
                    String auxUnitarias = unitarias.remove();
                    if(auxUnitarias.length() == 3){ //A clausula so possui um literal "positivo"
                        char literal = auxUnitarias.charAt(1);
                        for(int  k = 0; k < clausulas.length; k++){
                            String auxClausula = "";
                            for(int l = 0; l < clausulas[k].length(); l++){
                                if(clausulas[k].charAt(l) != literal && (clausulas[k].charAt(l) == '~' && clausulas[k].charAt(l+1) != literal)){ //Preciso arrumar essa condicao, eu quero que ele entre apenas se ele for diferente do literou, e caso for ~ so se p proximo for diferente do literal tambem
                                    //PAREI AQUI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                }
                            }
                        }
                    } else { //A clausula so possui um literal "negativo"

                    }
                }
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
            Estava fazendo as condicoes para poder eliminar os priterais...
         */

    }
}
