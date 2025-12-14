package ma.ensa.marrakech.exercice2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class LivreTest {

    private Livre livre;

    @BeforeEach
    void setUp() {
        livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "978-2070612758");
    }

    // Test 1: Création avec des valeurs spécifiques
    @Test
    void testCreationLivre_AvecValeursSpecifiques() {
        assertEquals("Le Petit Prince", livre.getTitre());
        assertEquals("Antoine de Saint-Exupéry", livre.getAuteur());
        assertEquals(1943, livre.getAnneePublication());
        assertEquals("978-2070612758", livre.getIsbn());
    }

    // Test 2: Test des getters et setters
    @Test
    void testGettersEtSetters() {
        // Test setter/getter titre
        livre.setTitre("1984");
        assertEquals("1984", livre.getTitre());

        // Test setter/getter auteur
        livre.setAuteur("George Orwell");
        assertEquals("George Orwell", livre.getAuteur());

        // Test setter/getter année
        livre.setAnneePublication(1949);
        assertEquals(1949, livre.getAnneePublication());

        // Test setter/getter ISBN
        livre.setIsbn("978-2070368227");
        assertEquals("978-2070368227", livre.getIsbn());
    }

    // Test 3: Égalité de deux instances
    @Test
    void testEgaliteDeuxInstances() {
        Livre livre1 = new Livre("Titre", "Auteur", 2020, "1234567890");
        Livre livre2 = new Livre("Titre", "Auteur", 2020, "1234567890");

        assertEquals(livre1, livre2);
        assertEquals(livre1.hashCode(), livre2.hashCode());
    }

    // Test 4: Validation des données - année négative
    @Test
    void testAnneePublicationNegative_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Titre", "Auteur", -2023, "1234567890");
        });
    }

    // Test 5: Cas limites
    @Test
    void testTitreVide_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("", "Auteur", 2023, "1234567890");
        });
    }

    @Test
    void testAuteurVide_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Titre", "", 2023, "1234567890");
        });
    }

    @Test
    void testAnneeTresAncienne() {
        // Test avec une année très ancienne mais valide
        Livre livreAncien = new Livre("Les Misérables", "Victor Hugo", 1862, "978-2253088444");
        assertEquals(1862, livreAncien.getAnneePublication());
    }

    @Test
    void testAnneeTresRecente() {
        // Test avec une année récente
        int anneeActuelle = java.time.Year.now().getValue();
        Livre livreRecent = new Livre("Nouveau Livre", "Nouvel Auteur", anneeActuelle, "1234567890");
        assertEquals(anneeActuelle, livreRecent.getAnneePublication());
    }

    // Test 6: Performance - création multiple d'instances
    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testPerformanceCreationMultipleInstances() {
        List<Livre> livres = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            livres.add(new Livre("Livre " + i, "Auteur " + i, 2000 + (i % 24), "ISBN-" + i));
        }
        assertEquals(100000, livres.size());
    }

    // Test 7: Encapsulation - pas d'accès direct aux champs
    @Test
    void testEncapsulation_ChampsPrives() throws Exception {
        Field champTitre = Livre.class.getDeclaredField("titre");
        Field champAuteur = Livre.class.getDeclaredField("auteur");

        assertFalse(champTitre.isAccessible());
        assertFalse(champAuteur.isAccessible());
    }

    // Test 8: Pas de fuite mémoire (test simplifié)
    @Test
    void testPasDeFuiteMemoire() {
        List<Livre> livres = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            livres.add(new Livre("Livre" + i, "Auteur", 2023, "ISBN" + i));
        }

        long memoireAvant = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        livres.clear();
        System.gc();

        try {
            Thread.sleep(100); // Laisser du temps au GC
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long memoireApres = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // La mémoire après devrait être inférieure ou égale
        assertTrue(memoireApres <= memoireAvant ||
                (memoireApres - memoireAvant) < 1000000); // Tolérance de 1MB
    }

    // Tests paramétrés pour différentes valeurs
    @ParameterizedTest
    @ValueSource(ints = {0, 100, 1000, 1500, 2000})
    void testAnneePublication_ValeursDiverses(int annee) {
        if (annee >= 0) {
            Livre livre = new Livre("Titre", "Auteur", annee, "1234567890");
            assertEquals(annee, livre.getAnneePublication());
        }
    }
}