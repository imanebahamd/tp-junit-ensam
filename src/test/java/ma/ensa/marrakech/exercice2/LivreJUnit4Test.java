package ma.ensa.marrakech.exercice2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LivreJUnit4Test {

    private Livre livre;

    @Before
    public void setUp() {
        livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "978-2070612758");
    }

    @Test
    public void testCreationLivre_JUnit4() {
        assertEquals("Le titre doit être correct", "Le Petit Prince", livre.getTitre());
        assertEquals("L'auteur doit être correct", "Antoine de Saint-Exupéry", livre.getAuteur());
        assertEquals("L'année doit être correcte", 1943, livre.getAnneePublication());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAnneeNegative_JUnit4() {
        new Livre("Titre", "Auteur", -1, "1234567890");
    }

    @Test
    public void testEgalite_JUnit4() {
        Livre livre1 = new Livre("Même", "Auteur", 2023, "123");
        Livre livre2 = new Livre("Même", "Auteur", 2023, "123");

        assertTrue("Les livres doivent être égaux", livre1.equals(livre2));
    }
}