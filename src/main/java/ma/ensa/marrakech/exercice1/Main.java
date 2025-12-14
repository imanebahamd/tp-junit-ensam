package ma.ensa.marrakech.exercice1;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // FORCER LA LOCALE US POUR AVOIR DES POINTS AU LIEU DE VIRGULES
        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Convertisseur de Devises ===");
        System.out.println("1. MAD → EUR");
        System.out.println("2. EUR → MAD");
        System.out.print("Choisissez une option (1 ou 2): ");

        try {
            int choice = scanner.nextInt();

            System.out.print("Entrez le montant: ");
            double amount = scanner.nextDouble();

            switch (choice) {
                case 1:
                    double result = CurrencyConverter.convertMadToEur(amount);
                    System.out.printf("%.2f MAD = %.2f EUR%n", amount, result);
                    break;
                case 2:
                    result = CurrencyConverter.convertEurToMad(amount);
                    System.out.printf("%.2f EUR = %.2f MAD%n", amount, result);
                    break;
                default:
                    System.out.println("Option invalide!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre valide");
            // Vider le buffer
            if (scanner.hasNext()) {
                scanner.next();
            }
        } finally {
            scanner.close();
        }
    }
}