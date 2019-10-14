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
                } else if (proposicao.charAt(i) == 'V' ){
                    if(proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'Q' && proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'P' ){
                        res = false;
                    } else if (proposicao.charAt(i+1) != 'P' && proposicao.charAt(i+1) != 'Q' && proposicao.charAt(i+1) != 'R' && proposicao.charAt(i+1) != 'S'){
                        res = false;
                    }
                } else if (proposicao.charAt(i) == '~' ){
                    if(proposicao.charAt(i-1) != 'P' && proposicao.charAt(i-1) != 'Q' && proposicao.charAt(i-1) != 'R' && proposicao.charAt(i-1) != 'S' &&
                            proposicao.charAt(i-1) != '(' && proposicao.charAt(i-1) != 'V'){
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
                    } else if (proposicao.charAt(i+1) != ')' && proposicao.charAt(i+1) != '&' && proposicao.charAt(i+1) != 'V'){
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

    public boolean Horn(String[] clausulas){
        boolean res = true;



        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Funcoes funcoes = new Funcoes();

        int quantidade = in.nextInt();

        for (int k = 1; k < quantidade+1; k++) {
            String proposicao = in.nextLine();
            String[] propAux = proposicao.split(" ");
            proposicao = "";
            for (int i = 0; i < propAux.length; i++) {
                proposicao += propAux[i];
            }

            System.out.println(funcoes.FNC(proposicao));

            /*System.out.println("Problema #"+k);
            if(funcoes.FNC(proposicao) == false){
                System.out.println("Não está na FNC.");
            } else {
                System.out.println("Está na FNC.");
            }
            System.out.println();*/
        }



        /*String aux = in.nextLine();
        String[] aux2 = aux.split(" ");
        aux = "";
        for(int i = 0; i < aux2.length; i++){
            aux += aux2[i];
        }
        String[] aux3 = aux.split("&");
        System.out.println();*/

        /*O metodo da FNC esta errado...
         * :( */
    }
}
