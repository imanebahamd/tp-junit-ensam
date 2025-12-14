package ma.ensa.marrakech.exercice1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void testConvertMadToEur_ZeroAmount() {
        double result = CurrencyConverter.convertMadToEur(0);
        assertEquals(0, result, "Conversion de 0 MAD doit donner 0 EUR");
    }

    @Test
    void testConvertEurToMad_ZeroAmount() {
        double result = CurrencyConverter.convertEurToMad(0);
        assertEquals(0, result, "Conversion de 0 EUR doit donner 0 MAD");
    }

    @Test
    void testConvertMadToEur_NegativeAmount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverter.convertMadToEur(-100);
        }, "Un montant négatif doit lever une exception");
    }

    @Test
    void testConvertEurToMad_NegativeAmount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            CurrencyConverter.convertEurToMad(-50);
        });
    }

    @Test
    void testConvertMadToEur_PositiveAmount() {
        ExchangeRate.setMadToEurRate(0.09);
        double result = CurrencyConverter.convertMadToEur(100);
        assertEquals(9.0, result, 0.001, "100 MAD doivent donner 9 EUR");
    }

    @Test
    void testConvertEurToMad_PositiveAmount() {
        ExchangeRate.setEurToMadRate(11.11);
        double result = CurrencyConverter.convertEurToMad(10);
        assertEquals(111.1, result, 0.001, "10 EUR doivent donner 111.1 MAD");
    }

    @ParameterizedTest
    @CsvSource({
            "100, 9.0",
            "200, 18.0",
            "500, 45.0",
            "1000, 90.0"
    })
    void testConvertMadToEur_VariousAmounts(double mad, double expectedEur) {
        ExchangeRate.setMadToEurRate(0.09);
        double result = CurrencyConverter.convertMadToEur(mad);
        assertEquals(expectedEur, result, 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {10.5, 25.75, 100.0, 250.5})
    void testConvertEurToMad_RandomValues(double eur) {
        ExchangeRate.setEurToMadRate(11.11);
        double result = CurrencyConverter.convertEurToMad(eur);
        assertEquals(eur * 11.11, result, 0.001);
    }
}