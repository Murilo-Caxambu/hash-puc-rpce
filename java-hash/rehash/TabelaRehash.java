public class TabelaRehash {
    private int[] tabela;
    private boolean[] ocupado;
    private int inseridos;
    private int colisoes;
    private int[] top3Elementos;
    private int[] top3Colisoes;

    public TabelaRehash() {
        this.tabela = new int[100];
        this.ocupado = new boolean[100];
        this.inseridos = 0;
        this.colisoes = 0;

        this.top3Elementos = new int[3];
        this.top3Colisoes = new int[3];
        for (int i = 0; i < 3; i++) {
            this.top3Colisoes[i] = -1;
        }
    }

    private int funcaoHash(int chave) {
        return chave % this.tabela.length;
    }

    private void atualizarTop3(int elemento, int colisoesElemento) {
        if (colisoesElemento > top3Colisoes[0]) {
            top3Colisoes[2] = top3Colisoes[1];
            top3Elementos[2] = top3Elementos[1];

            top3Colisoes[1] = top3Colisoes[0];
            top3Elementos[1] = top3Elementos[0];

            top3Colisoes[0] = colisoesElemento;
            top3Elementos[0] = elemento;
        } else if (colisoesElemento > top3Colisoes[1]) {
            top3Colisoes[2] = top3Colisoes[1];
            top3Elementos[2] = top3Elementos[1];

            top3Colisoes[1] = colisoesElemento;
            top3Elementos[1] = elemento;
        } else if (colisoesElemento > top3Colisoes[2]) {
            top3Colisoes[2] = colisoesElemento;
            top3Elementos[2] = elemento;
        }
    }

    public boolean inserir(int valor) {
        if (inseridos >= 90) {
            System.out.println("Tabela atingiu 90% de ocupacao. Insercao recusada para o valor: " + valor);
            return false;
        }

        int indice = funcaoHash(valor);
        int colisoesElemento = 0;
        int tentativas = 0;

        while (ocupado[indice] && tentativas < tabela.length) {
            colisoesElemento++;
            colisoes++;
            indice = (indice + 1) % tabela.length;
            tentativas++;
        }

        if (!ocupado[indice]) {
            tabela[indice] = valor;
            ocupado[indice] = true;
            inseridos++;

            atualizarTop3(valor, colisoesElemento);
            return true;
        }

        return false;
    }

    public int buscar(int valor) {
        int indice = funcaoHash(valor);
        int tentativas = 0;

        while (ocupado[indice] && tentativas < tabela.length) {
            if (tabela[indice] == valor) {
                return indice;
            }
            indice = (indice + 1) % tabela.length;
            tentativas++;
        }

        return -1;
    }

    public void imprimirRelatorio() {
        System.out.println("Total de elementos inseridos: " + inseridos + " / 100 (Taxa: " + (inseridos) + "%)");
        System.out.println("Total geral de colisoes na insercao: " + colisoes);

        System.out.println("\n-Top 3 colisoes");
        for (int i = 0; i < 3; i++) {
            if (top3Colisoes[i] >= 0) {
                System.out.println((i + 1) + "º lugar: Valor " + top3Elementos[i] + " (" + top3Colisoes[i] + " colisoes)");
            }
        }

        System.out.println("\n Posicoes livres ");
        int totalLivres = 0;
        for (int i = 0; i < tabela.length; i++) {
            if (!ocupado[i]) {
                System.out.print("[" + i + "] ");
                totalLivres++;
            }
        }
        System.out.println("\nTotal de posicoes livres: " + totalLivres);
    }

    public int[] getTop3Elementos() {
        return top3Elementos;
    }
}