import java.util.*;

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
                char aux = clausulas[i].charAt(j);
                if(aux == '~'){
                    quantNegacoes++;
                } else if (aux >= 80 && aux <= 83){ //P = 80, Q = 81, R = 82, S = 83
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
        String[] clausulas = proposicao.split("&"); //Cria um array de clausulas
        ArrayList<String> clausulasVector = new ArrayList<>();


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
                char literal = auxUnitarias.charAt(1); //Pega qual é a variavel do literal
                int quantidadeClausulas = clausulasVector.size();
                for(int  k = 0; k < quantidadeClausulas; k++){
                    String auxClausula = "";
                    String clausulaAtual = clausulasVector.get(k);
                    for(int l = 0; l < clausulaAtual.length(); l++){
                        if(clausulaAtual.charAt(l) == '~'){
                            if(clausulaAtual.charAt(l+1) != literal){
                                auxClausula += clausulaAtual.charAt(l);
                                auxClausula += clausulaAtual.charAt(l+1);
                                l++;
                            } else {
                                l++;
                            }
                        } else if (clausulaAtual.charAt(l) == 'v') {
                            if(auxClausula.charAt(auxClausula.length()-1) != 'v' && auxClausula.charAt(auxClausula.length()-1) != '(' &&
                                    l != clausulaAtual.length()-2){
                                auxClausula += clausulaAtual.charAt(l);
                            }
                        } else if(clausulaAtual.charAt(l) == literal){ //Se entrar aqui preciso eliminar a clausula toda
                            auxClausula = "";
                            l = clausulaAtual.length();
                        } else { //Deve entrar aqui quando a digito atual for igual a '(' ou ')' ou 'P' ou 'Q' ou 'R' ou 'S'. Diferente de literal atual
                            if(clausulaAtual.charAt(l) == ')' && auxClausula.charAt(auxClausula.length()-1) == 'v'){
                                String aux = "";
                                for(int m = 0; m < auxClausula.length(); m++){ //Remover o 'v' do final
                                    if(m == auxClausula.length()-1){
                                        auxClausula = aux;
                                        auxClausula += ')';
                                    } else {
                                        aux += auxClausula.charAt(m);
                                    }
                                }

                            } else {
                                auxClausula += clausulaAtual.charAt(l);
                            }
                        }
                    }
                    if(auxClausula.equals("()")){
                        return false;
                    } else if(!auxClausula.equals(clausulaAtual) && !auxClausula.equals("")){
                        clausulasVector.add(auxClausula);
                        clausulasVector.remove(k);
                        k--;
                        quantidadeClausulas = clausulasVector.size();
                        if(auxClausula.length() == 4 || auxClausula.length() == 3){//Gerou uma nova clausula unitaria
                            if(!auxClausula.equals(auxUnitarias)){
                                unitarias.add(auxClausula);
                            }
                        }
                    } else if(auxClausula.equals("")){
                        if(clausulaAtual.length() != 3 && clausulaAtual.length() != 4){
                            clausulasVector.remove(k);
                            k--;
                            quantidadeClausulas = clausulasVector.size();
                        }
                    }
                }
            } else { //A clausula so possui um literal "negativo"
                char literal = auxUnitarias.charAt(2); //Pega qual e a variavel do literal
                int quantidadeClausulas = clausulasVector.size();
                for(int  k = 0; k < quantidadeClausulas; k++) {
                    String auxClausula = "";
                    String clausulaAtual = clausulasVector.get(k);
                    for(int l = 0; l < clausulaAtual.length(); l++){
                        if(clausulaAtual.charAt(l) == '~'){
                            if(clausulaAtual.charAt(l+1) == literal){
                                auxClausula = "";
                                l = clausulaAtual.length();
                            } else {
                                auxClausula += clausulaAtual.charAt(l);
                                auxClausula += clausulaAtual.charAt(l+1);
                                l++;
                            }
                        } else if(clausulaAtual.charAt(l) == 'v'){
                            if(auxClausula.charAt(auxClausula.length()-1) != 'v' && auxClausula.charAt(auxClausula.length()-1) != '(' &&
                                    l != clausulaAtual.length()-2){
                                auxClausula += clausulaAtual.charAt(l);
                            }
                        } else if(clausulaAtual.charAt(l) == literal){
                            //Nao faz nada, ou seja so ignora essa variavel
                        } else { //Deve entrar aqui quando a digito atual for igual a '(' ou ')' ou 'P' ou 'Q' ou 'R' ou 'S'. Diferente de literal atual
                            if(clausulaAtual.charAt(l) == ')' && auxClausula.charAt(auxClausula.length()-1) == 'v'){
                                String aux = "";
                                for(int m = 0; m < auxClausula.length(); m++){ //Remove o 'v' do final
                                    if(m == auxClausula.length()-1){
                                        auxClausula = aux;
                                        auxClausula += ')';
                                    } else {
                                        aux += auxClausula.charAt(m);
                                    }
                                }

                            } else {
                                auxClausula += clausulaAtual.charAt(l);
                            }
                        }
                    }

                    if(auxClausula.equals("()")){
                        return false;
                    } else if(!auxClausula.equals(clausulaAtual) && !auxClausula.equals("")){
                        clausulasVector.add(auxClausula);
                        clausulasVector.remove(k);
                        k--;
                        quantidadeClausulas = clausulasVector.size();
                        if(auxClausula.length() == 4 || auxClausula.length() == 3){ //Gerou uma nova clausula unitatira
                            if(auxClausula.equals(auxUnitarias)){
                                unitarias.add(auxClausula);
                            }
                        }
                    } else if (auxClausula.equals("")){
                        if(clausulaAtual.length() != 3 && clausulaAtual.length() != 4){//A clausula nao e mais necessaria, pela tecnica da propagacao unitaria
                            clausulasVector.remove(k);
                            k--;
                            quantidadeClausulas = clausulasVector.size();
                        }
                    }
                }
            }
        }

        return res;
    }
}

public class metodoResolucao {
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

            System.out.println("Problema #"+k);
            if(funcoes.FNC(proposicao) == false){
                System.out.println("Não está na FNC.");
            } else {
                if(funcoes.Horn(proposicao) == false){
                    System.out.println("Nem todas as cláusulas são de Horn.");
                } else { //Verificar se é satisfativel
                    if(funcoes.Satisfativel(proposicao) == false){
                        System.out.println("Não, não é satisfatível.");
                    } else {
                        System.out.println("Sim, é satisfatível.");
                    }
                }
            }
            System.out.println();
        }
    }
}
