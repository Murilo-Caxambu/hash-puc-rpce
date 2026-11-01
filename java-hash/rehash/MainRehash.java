import java.util.Random;

public class MainRehash {
    public static void main(String[] args) {
        final long seed = 1090;
        final int tentativas = 100;
        final int limite = 100001;

        Random random = new Random(seed);
        TabelaRehash tabela = new TabelaRehash();

        System.out.println("Iniciando insercao de 100 elementos com seed 1090...\n");
        for (int i = 0; i < tentativas; i++) {
            int numeroAleatorio = random.nextInt(limite);
            tabela.inserir(numeroAleatorio);
        }

        System.out.println();
        tabela.imprimirRelatorio();

        System.out.println("\n3 mais colididos");
        int[] top3 = tabela.getTop3Elementos();
        for (int i = 0; i < top3.length; i++) {
            int elemento = top3[i];
            int posicao = tabela.buscar(elemento);
            if (posicao != -1) {
                System.out.println("Busca pelo valor " + elemento + ": encontrado na posicao [" + posicao + "]");
            } else {
                System.out.println("Busca pelo valor " + elemento + ": nao achado");
            }
        }
    }
}