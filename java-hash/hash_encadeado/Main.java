import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final long seed = 1090;
        final int total_elementos = 1000;
        final int limite = 100001;

        Random random = new Random(seed);
        TabelaHashEncadeada tabelaHash = new TabelaHashEncadeada();

        for (int i = 0; i < total_elementos; i++) {
            int numeroAleatorio = random.nextInt(limite);
            tabelaHash.inserir(numeroAleatorio);
        }

        tabelaHash.imprimirRelatorio();
    }
}