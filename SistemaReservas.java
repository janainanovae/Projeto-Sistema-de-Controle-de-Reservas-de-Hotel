import java.util.Locale;
import java.util.Scanner;

public class SistemaReservas {

    private static final int CAPACIDADE = 10;

    // Vetor fixo de reservas e contador de quantas já foram cadastradas
    private static Reserva[] reservas = new Reserva[CAPACIDADE];
    private static int quantidade = 0;

    // Locale pt-BR: o valor da diária pode ser digitado com vírgula (ex.: 250,50)
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha que sobrou

            switch (opcao) {
                case 1:
                    novaReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    buscarReserva();
                    break;
                case 4:
                    ordenarReservas();
                    break;
                case 5:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha de 1 a 5.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    // Mostra o menu principal
    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE RESERVAS DO HOTEL =====");
        System.out.println("1 - Nova reserva");
        System.out.println("2 - Listar reservas");
        System.out.println("3 - Buscar reserva por nome do hóspede");
        System.out.println("4 - Ordenar reservas por número de dias (decrescente)");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Opção 1: cadastra uma nova reserva, validando dias e diária
    private static void novaReserva() {
        if (quantidade >= CAPACIDADE) {
            System.out.println("Capacidade máxima de " + CAPACIDADE + " reservas atingida!");
            return;
        }

        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo do quarto (Standard, Luxo, Presidencial...): ");
        String tipoQuarto = scanner.nextLine();

        // Repete a leitura até o número de dias ser válido (mínimo 1)
        int dias;
        do {
            System.out.print("Número de dias de estadia: ");
            dias = scanner.nextInt();

            if (dias < 1) {
                System.out.println("Número de dias inválido! Informe no mínimo 1.");
            }
        } while (dias < 1);

        // Repete a leitura até o valor da diária ser maior que zero
        double diaria;
        do {
            System.out.print("Valor da diária: ");
            diaria = scanner.nextDouble();

            if (diaria <= 0) {
                System.out.println("Valor da diária inválido! Deve ser maior que zero.");
            }
        } while (diaria <= 0);

        scanner.nextLine(); // consome a quebra de linha que sobrou

        reservas[quantidade] = new Reserva(nome, tipoQuarto, dias, diaria);
        quantidade++;

        System.out.println("Reserva cadastrada com sucesso!");
    }

    // Opção 2: lista todas as reservas usando o toString() da classe Reserva
    private static void listarReservas() {
        if (quantidade == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.println();
        System.out.println("===== Reservas cadastradas =====");
        for (int i = 0; i < quantidade; i++) {
            System.out.println((i + 1) + " - " + reservas[i].toString());
        }
    }

    // Opção 3: busca por parte do nome, sem diferenciar maiúsculas de minúsculas
    private static void buscarReserva() {
        System.out.print("Digite o nome (ou parte do nome) do hóspede: ");
        String termo = scanner.nextLine().toLowerCase();

        boolean encontrou = false;

        for (int i = 0; i < quantidade; i++) {
            if (reservas[i].getNomeHospede().toLowerCase().contains(termo)) {
                System.out.println(reservas[i].toString());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada para esse nome.");
        }
    }

    // Opção 4: ordena o vetor pelo número de dias, do maior para o menor (Bubble Sort)
    private static void ordenarReservas() {
        if (quantidade == 0) {
            System.out.println("Nenhuma reserva para ordenar.");
            return;
        }

        for (int i = 0; i < quantidade - 1; i++) {
            for (int j = 0; j < quantidade - 1 - i; j++) {
                if (reservas[j].getNumeroDias() < reservas[j + 1].getNumeroDias()) {
                    Reserva auxiliar = reservas[j];
                    reservas[j] = reservas[j + 1];
                    reservas[j + 1] = auxiliar;
                }
            }
        }

        System.out.println("Reservas ordenadas por número de dias (decrescente).");
        listarReservas();
    }
}
