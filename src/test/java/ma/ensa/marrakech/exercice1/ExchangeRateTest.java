package ma.ensa.marrakech.exercice1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateTest {

    @BeforeEach
    void setUp() {
        ExchangeRate.setMadToEurRate(0.09);
        ExchangeRate.setEurToMadRate(11.11);
    }

    @Test
    void testGetMadToEurRate() {
        assertEquals(0.09, ExchangeRate.getMadToEurRate(), 0.001);
    }

    @Test
    void testGetEurToMadRate() {
        assertEquals(11.11, ExchangeRate.getEurToMadRate(), 0.001);
    }

    @Test
    void testSetMadToEurRate_NegativeRate_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExchangeRate.setMadToEurRate(-0.05);
        });
    }

    @Test
    void testSetMadToEurRate_ZeroRate_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExchangeRate.setMadToEurRate(0);
        });
    }

    @Test
    void testSetEurToMadRate_NegativeRate_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExchangeRate.setEurToMadRate(-10);
        });
    }

    @Test
    void testSetMadToEurRate_ValidRate_UpdatesBothRates() {
        ExchangeRate.setMadToEurRate(0.1);
        assertEquals(0.1, ExchangeRate.getMadToEurRate(), 0.001);
        assertEquals(10.0, ExchangeRate.getEurToMadRate(), 0.001);
    }

    @Test
    void testSetEurToMadRate_ValidRate_UpdatesBothRates() {
        ExchangeRate.setEurToMadRate(10.0);
        assertEquals(10.0, ExchangeRate.getEurToMadRate(), 0.001);
        assertEquals(0.1, ExchangeRate.getMadToEurRate(), 0.001);
    }
}