package ma.ensa.marrakech.exercice3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.concurrent.TimeUnit;

class LivreAvanceTest {

    private LivreAvance livre;

    @BeforeEach
    void setUp() {
        livre = new LivreAvance("Le Petit Prince",
                "Antoine de Saint-Exupéry",
                LocalDate.of(1943, 4, 6),
                "978-2070612758");
    }

    // Test 1: Validation des dates
    @Test
    void testDatePublicationValide() {
        assertTrue(livre.estDateRaisonnable());
    }

    @Test
    void testDatePublicationTropAncienne_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LivreAvance("Ancien Livre", "Auteur Inconnu",
                    LocalDate.of(500, 1, 1), "1234567890");
        });
    }

    @Test
    void testDatePublicationFuture_ThrowsException() {
        LocalDate futureDate = LocalDate.now().plusYears(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new LivreAvance("Livre Futur", "Auteur Futur", futureDate, "1234567890");
        });
    }

    // Test 2: Compatibilité avec ancienne version
    @Test
    void testCompatibiliteAncienConstructeur() {
        LivreAvance livreCompat = new LivreAvance("1984", "George Orwell", 1949, "978-2070368227");
        assertEquals(1949, livreCompat.getAnneePublication());
        assertEquals("1984", livreCompat.getTitre());
    }

    // Test 3: Limites de dates
    @Test
    void testDateLimiteInferieure() {
        // Date exactement à la limite inférieure
        LivreAvance livreLimite = new LivreAvance("Livre Ancien", "Auteur",
                LocalDate.of(1000, 1, 1), "1234567890");
        assertTrue(livreLimite.estDateRaisonnable());
    }

    @Test
    void testDateLimiteSuperieure() {
        // Date d'aujourd'hui (limite supérieure)
        LivreAvance livreAujourdhui = new LivreAvance("Livre Récent", "Auteur",
                LocalDate.now(), "1234567890");
        assertTrue(livreAujourdhui.estDateRaisonnable());
    }

    @Test
    void testDateJusteAvantAn1000_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LivreAvance("Très Ancien", "Auteur",
                    LocalDate.of(999, 12, 31), "1234567890");
        });
    }

    // Test 4: Performance avec dates
    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testPerformanceAvecDates() {
        for (int i = 0; i < 10000; i++) {
            new LivreAvance("Livre " + i, "Auteur " + i,
                    LocalDate.of(1500 + (i % 500), 1, 1),
                    "ISBN-" + i);
        }
    }

    // Test 5: Gestion des exceptions dans les setters
    @Test
    void testSetterDateNulle_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setDatePublication(null);
        });
    }

    // Test 6: Cas complexes
    @Test
    void testMemeLivreDatesDifferentes_NonEgaux() {
        LivreAvance livre1 = new LivreAvance("Titre", "Auteur",
                LocalDate.of(2020, 1, 1), "123");
        LivreAvance livre2 = new LivreAvance("Titre", "Auteur",
                LocalDate.of(2020, 1, 2), "123");
        assertNotEquals(livre1, livre2);
    }

    // Tests paramétrés
    @ParameterizedTest
    @CsvSource({
            "1500, true",
            "999, false",
            "1000, true",
            "2023, true",
    })
    void testValidationDatesParametrees(int annee, boolean valide) {
        if (valide) {
            LivreAvance livre = new LivreAvance("Titre", "Auteur",
                    LocalDate.of(annee, 1, 1), "1234567890");
            assertTrue(livre.estDateRaisonnable());
        } else {
            assertThrows(IllegalArgumentException.class, () -> {
                new LivreAvance("Titre", "Auteur",
                        LocalDate.of(annee, 1, 1), "1234567890");
            });
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1500-06-15", "1800-12-01", "2000-03-20"})
    void testCreationAvecDatesValides(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LivreAvance livre = new LivreAvance("Titre", "Auteur", date, "1234567890");
        assertEquals(date, livre.getDatePublication());
    }


    @Test
    void testGetAnneePublication() {
        assertEquals(1943, livre.getAnneePublication());
    }

    @Test
    void testSetDatePublicationAvecAnnee() {
        livre.setDatePublication(1950);
        assertEquals(1950, livre.getAnneePublication());
        assertEquals(LocalDate.of(1950, 1, 1), livre.getDatePublication());
    }

    @Test
    void testConstructeurAvecAnnee() {
        LivreAvance livreAnnee = new LivreAvance("Test", "Auteur", 2000, "1234567890");
        assertEquals(2000, livreAnnee.getAnneePublication());
        assertEquals(LocalDate.of(2000, 1, 1), livreAnnee.getDatePublication());
    }

    @Test
    void testToString() {
        String expected = "LivreAvance{titre='Le Petit Prince', auteur='Antoine de Saint-Exupéry', " +
                "datePublication=1943-04-06, isbn='978-2070612758'}";
        assertEquals(expected, livre.toString());
    }

    @Test
    void testEqualsEtHashCode() {
        LivreAvance livre1 = new LivreAvance("Titre", "Auteur", LocalDate.of(2020, 1, 1), "123");
        LivreAvance livre2 = new LivreAvance("Titre", "Auteur", LocalDate.of(2020, 1, 1), "123");
        LivreAvance livre3 = new LivreAvance("Autre", "Auteur", LocalDate.of(2020, 1, 1), "123");

        // Test equals
        assertEquals(livre1, livre2);
        assertNotEquals(livre1, livre3);

        // Test hashCode cohérent
        assertEquals(livre1.hashCode(), livre2.hashCode());

        // Test avec null
        assertNotEquals(null, livre1);

        // Test avec autre classe
        assertNotEquals("string", livre1);
    }

    @Test
    void testSetterIsbn() {
        livre.setIsbn("Nouveau-ISBN");
        assertEquals("Nouveau-ISBN", livre.getIsbn());
    }

    @Test
    void testSetterIsbnVide_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setIsbn("");
        });
    }

    @Test
    void testSetterIsbnNull_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setIsbn(null);
        });
    }
}