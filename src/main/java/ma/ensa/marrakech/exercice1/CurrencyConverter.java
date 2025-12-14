package ma.ensa.marrakech.exercice1;

public class CurrencyConverter {

    public static double convertMadToEur(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant ne peut pas être négatif");
        }
        if (amount == 0) {
            return 0;
        }
        return amount * ExchangeRate.getMadToEurRate();
    }

    public static double convertEurToMad(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant ne peut pas être négatif");
        }
        if (amount == 0) {
            return 0;
        }
        return amount * ExchangeRate.getEurToMadRate();
    }
}