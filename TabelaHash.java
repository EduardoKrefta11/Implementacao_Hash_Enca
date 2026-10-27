import java.util.Random;

public class TabelaHash {

    public static void main(String[] args) {
        final int TAMANHO = 100;
        final int QUANTIDADE = 1000;
        final int LIMITE = 100000;

        Random random = new Random(1090);
        No[] tabela = new No[TAMANHO];

        int ultimo1 = -1;
        int ultimo2 = -1;
        int ultimo3 = -1;

        for (int i = 0; i < QUANTIDADE; i++) {
            int valor = random.nextInt(LIMITE + 1);
            int posicao = valor % TAMANHO;

            No novo = new No(valor);

            if (tabela[posicao] == null) {
                tabela[posicao] = novo;
            } else {
                No atual = tabela[posicao];
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novo;

                ultimo3 = ultimo2;
                ultimo2 = ultimo1;
                ultimo1 = valor;
            }
        }

        int posicoesVazias = 0;
        int quantidadeListas = 0;
        int maiorTamanho = 0;
        int posicaoMaiorLista = -1;

        for (int i = 0; i < TAMANHO; i++) {
            if (tabela[i] == null) {
                posicoesVazias++;
            } else {
                int tamanho = 0;
                No atual = tabela[i];
                while (atual != null) {
                    tamanho++;
                    atual = atual.proximo;
                }

                if (tamanho >= 2) {
                    quantidadeListas++;
                }

                if (tamanho > maiorTamanho) {
                    maiorTamanho = tamanho;
                    posicaoMaiorLista = i;
                }
            }
        }

        System.out.println("===== RESULTADOS =====");

        System.out.println();
        System.out.println("Maior lista encadeada:");
        System.out.println("Posição: " + posicaoMaiorLista);
        System.out.println("Tamanho: " + maiorTamanho);
        System.out.print("Elementos: ");
        No atual = tabela[posicaoMaiorLista];
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) {
                System.out.print(" -> ");
            }
            atual = atual.proximo;
        }
        System.out.println();

        System.out.println();
        System.out.println("Quantidade de listas encadeadas geradas: " + quantidadeListas);
        System.out.println("Quantidade de posições vazias: " + posicoesVazias);

        System.out.println();
        System.out.println("Três últimos valores inseridos em listas encadeadas:");
        System.out.println("1º (mais recente): " + ultimo1);
        System.out.println("2º: " + ultimo2);
        System.out.println("3º: " + ultimo3);
    }
}

class No {
    int valor;
    No proximo;

    No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}
