public class TabelaHashEncadeada{
    private No[] tabela;
    private int[] ultimosColididos;
    private int colisoes;

    public TabelaHashEncadeada(){
        this.tabela = new No[100];
        this.ultimosColididos = new int[3];
        this.colisoes = 0;
    }

    private int funcaoHash(int chave){
        int i = chave % tabela.length;
        return i;
    }

    private void ColisaoAchada(int valor){
        if(colisoes < 3){
            ultimosColididos[colisoes] = valor;
        }
        else{
            ultimosColididos[0] = ultimosColididos[1];
            ultimosColididos[1] = ultimosColididos[2];
            ultimosColididos[2] = valor;
        }
        colisoes +=1;
    }

    public void inserir(int valor) {
        int indice = funcaoHash(valor);
        No novoNo = new No(valor);

        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            ColisaoAchada(valor);

            No aux = tabela[indice];
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }

            aux.setProximo(novoNo);
        }
    }

    public int posicoesVazias(){
        int v = 0;
        for(int i = 0; i < tabela.length; i++){
            if(tabela[i] == null){
                v +=1;
            }
        }
        return v;
    }

    public int posicoesOcupadas(){
        int v = 0;
        for(int i =0; i< tabela.length; i++){
            if(tabela[i] != null){
                v+=1;
            }
        }
        return  v;
    }

    public int posicoesColisao(){
        int c = 0;
        for(int i =0; i< tabela.length; i++){
            if(tabela[i] != null && tabela[i].getProximo() != null){
                c +=1;
            }
        }
        return c;
    }

    public void maiorLista() {
        int maiorTamanho = 0;
        int indiceMaior = -1;

        for (int i = 0; i < tabela.length; i++) {
            int tamanhoAtual = 0;
            No aux = tabela[i];

            while (aux != null) {
                tamanhoAtual++;
                aux = aux.getProximo();
            }

            if (tamanhoAtual > maiorTamanho) {
                maiorTamanho = tamanhoAtual;
                indiceMaior = i;
            }
        }

        if (indiceMaior != -1) {
            System.out.println("Maior lista encadeada (posicao " + indiceMaior + ", tamanho " + maiorTamanho + "):");
            No aux = this.tabela[indiceMaior];
            while (aux != null) {
                System.out.print(aux.getDado());
                if (aux.getProximo() != null) {
                    System.out.print(" -> ");
                }
                aux = aux.getProximo();
            }
            System.out.println();
        } else {
            System.out.println("Tabela vazia.");
        }
    }
    public void imprimirColididos() {
        System.out.println("3 ultimos colididos");
        for (int i = 0; i < ultimosColididos.length; i++) {
            System.out.println("[" + (i + 1) + "]: " + ultimosColididos[i]);
        }
    }

    public void imprimirRelatorio() {
        System.out.println("Posicoes vazias: " + posicoesVazias());
        System.out.println("Total de posicoes ocupadas: " + posicoesOcupadas());
        System.out.println("Posições onde teve colisao real: " + posicoesColisao());
        System.out.println("Total geral de colisoes registradas: " + this.colisoes);
        maiorLista();
        imprimirColididos();
    }
}
