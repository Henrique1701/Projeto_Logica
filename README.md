# Problema da Satisfatibilidade
    Dada uma proposição φ , pergunta-se: φ é satisfatível?
O projeto é um resolvedor de instâncias do problema SAT. Seu programa receberá expressões bem-formadas da lógica proposicional e deverá retornar a solução do problema. Seu programa deve solucionar as instâncias a partir de um dos métodos vistos em sala de aula: Método da Resolução.

Entrada
A entrada será dada por um arquivo Entrada.in.
A primeira linha da entrada conterá um inteiro N representando o número de problemas (proposições) que seu programa deve resolver.
As N linhas seguintes conterão proposições.

Sintaxe
Operadores: cada entrada será composta por 5 operadores. Seus símbolos usados estão listados abaixo.

~ | Negação

v | Disjunção

& | Conjunção

> | Implicação

< | Equivalência (bi-implicação)

Variáveis: a entrada terá no máximo 4 variáveis - P, Q, R e S.

Parênteses: todas as cláusulas estão envoltas em parênteses, até mesmo as unitárias.

Saída
Seu programa deve gerar uma saída: “saida.out”
Para cada caso, a saída deve conter "Problema #x", onde x é o número do caso, iniciando de 1.
Cada caso deve ser separado por uma linha em branco.
Objetivos
A Resolução só aceitará proposições que estiverem na FNC e (nesse projeto) com todas as cláusulas sendo de Horn.
Sendo assim, caso a expressão não esteja na FNC, seu programa deve imprimir "Não está na FNC.".
Caso esteja, mas há cláusulas que não são de Horn, seu programa deve imprimir "Nem todas as cláusulas são de Horn.".
Caso contrário, seu programa deve imprimir a resposta do problema: "Sim, é satisfatível." ou "Não, não é satisfatível”.

Cláusulas de Horn:
Uma cláusula é dita cláusula de Horn se, e somente se, contém, no máximo, um literal positivo. Exemplos:

Exemplo #1:
(~P) & (P v ~R v ~S v ~S) & (~S v R v ~P) & (Q) & (P v ~S v ~S v ~Q) & (~P v ~R v ~P)

Exemplo #2:
(S) & (P v ~S v ~S) & (~P) & (R) & (Q) & (~R v ~S v S) & (~P) & (~R v Q v ~Q v ~P v ~Q)
