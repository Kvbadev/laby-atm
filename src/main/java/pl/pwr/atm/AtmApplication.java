package pl.pwr.atm;

import java.math.BigDecimal;
import java.util.Scanner;

public final class AtmApplication {
    private AtmApplication() {
    }

    public static void main(String[] args) {
        AtmService atm = new AtmService(new Account());
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            runMenu(atm, scanner);
        }
    }

    private static void runMenu(AtmService atm, Scanner scanner) {
        printMenu();
        boolean running = true;
        while (running && scanner.hasNextLine()) {
            String option = scanner.nextLine().trim();
            try {
                switch (option) {
                    case "1":
                        System.out.println("Saldo: " + atm.getBalance() + " PLN");
                        break;
                    case "2":
                        atm.deposit(readAmount(scanner));
                        System.out.println("Wpłata zakończona.");
                        break;
                    case "3":
                        atm.withdraw(readAmount(scanner));
                        System.out.println("Wypłata zakończona.");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Do widzenia!");
                        break;
                    default:
                        System.out.println("Nieznana opcja.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Błąd: " + exception.getMessage());
            }
            if (running) {
                printMenu();
            }
        }
    }

    private static BigDecimal readAmount(Scanner scanner) {
        System.out.println("Podaj kwotę w PLN (np. 25,50):");
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Nie podano kwoty.");
        }
        String input = scanner.nextLine().trim().replace(',', '.');
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Wpisz poprawną liczbę.", exception);
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Saldo | 2. Wpłata | 3. Wypłata | 0. Wyjście");
    }
}
