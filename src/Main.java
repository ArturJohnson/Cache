import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int LIMITE_CACHE = 10;

    public static void main(String[] args) {

        List<Pessoa> banco = new ArrayList<>();
        List<Pessoa> cache = new ArrayList<>();


        banco.add(new Pessoa(1, "Ana", 25));
        banco.add(new Pessoa(2, "Bruno", 30));
        banco.add(new Pessoa(3, "Carla", 22));
        banco.add(new Pessoa(4, "Daniel", 28));
        banco.add(new Pessoa(5, "Eduarda", 35));
        banco.add(new Pessoa(6, "Felipe", 19));
        banco.add(new Pessoa(7, "Gabriela", 40));
        banco.add(new Pessoa(8, "Henrique", 27));
        banco.add(new Pessoa(9, "Isabela", 31));
        banco.add(new Pessoa(10, "João", 24));
        banco.add(new Pessoa(11, "Lucas", 29));
        banco.add(new Pessoa(12, "Mariana", 33));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nDigite um ID para buscar (ou -1 para sair): ");
            int idBuscado = scanner.nextInt();

            if (idBuscado == -1) {
                System.out.println("Saindo do programa...");
                break;
            }

            // 1. Verifica primeiro se a pessoa já está no cache
            Pessoa pessoaEncontrada = buscarPorId(cache, idBuscado);

            if (pessoaEncontrada != null) {
                System.out.println("Pessoa encontrada no cache: " + pessoaEncontrada);
            } else {

                pessoaEncontrada = buscarPorId(banco, idBuscado);

                if (pessoaEncontrada != null) {
                    if (cache.size() >= LIMITE_CACHE) {
                        Pessoa removida = cache.remove(0); // O elemento no índice 0 é o mais antigo (FIFO)
                        System.out.println("Cache cheio (" + LIMITE_CACHE + " itens)! Pessoa mais antiga (" + removida.getNome() + ") foi removida.");
                    }


                    cache.add(pessoaEncontrada);
                    System.out.println("Pessoa buscada no banco e adicionada ao cache: " + pessoaEncontrada);
                } else {
                    System.out.println("Pessoa com ID " + idBuscado + " não foi encontrada no banco de dados.");
                }
            }
        }

        scanner.close();
    }

    // Método auxiliar para buscar uma Pessoa pelo ID em qualquer lista
    private static Pessoa buscarPorId(List<Pessoa> lista, int id) {
        for (Pessoa p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}

