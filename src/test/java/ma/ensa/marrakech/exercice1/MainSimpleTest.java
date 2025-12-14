package ma.ensa.marrakech.exercice1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

class MainSimpleTest {

    @TempDir
    Path tempDir;

    @Test
    void testMainExecutesWithoutError_ValidInput() throws Exception {
        // Test 1: Entrée valide - conversion MAD vers EUR
        testWithInput("1\n100\n", "100.00 MAD = 9.00 EUR");
    }

    @Test
    void testMainExecutesWithoutError_ValidInput2() throws Exception {
        // Test 2: Entrée valide - conversion EUR vers MAD
        testWithInput("2\n50\n", "50.00 EUR = 555.50 MAD");
    }

    @Test
    void testMainExecutesWithoutError_ZeroAmount() throws Exception {
        // Test 3: Montant zéro
        testWithInput("1\n0\n", "0.00 MAD = 0.00 EUR");
    }

    @Test
    void testMainExecutesWithoutError_InvalidChoice() throws Exception {
        // Test 4: Choix invalide
        testWithInput("3\n100\n", "Option invalide");
    }

    @Test
    void testMainExecutesWithoutError_NegativeAmount() throws Exception {
        // Test 5: Montant négatif
        testWithInput("1\n-100\n", "Le montant ne peut pas être négatif");
    }

    @Test
    void testMainExecutesWithoutError_InvalidNumber() throws Exception {
        // Test 6: Entrée non numérique
        testWithInput("abc\n", "Veuillez entrer un nombre valide");
    }

    @Test
    void testMainClassStructure() {
        // Test 7: Structure de la classe
        assertNotNull(Main.class, "La classe Main devrait exister");

        try {
            Method mainMethod = Main.class.getMethod("main", String[].class);
            assertNotNull(mainMethod, "La méthode main devrait exister");
            assertEquals(void.class, mainMethod.getReturnType(), "main devrait retourner void");
        } catch (NoSuchMethodException e) {
            fail("La méthode main devrait exister: " + e.getMessage());
        }
    }

    @Test
    void testMainWithFileInput() throws Exception {
        // Test 8: Avec entrée depuis un fichier
        Path inputFile = tempDir.resolve("input.txt");
        Files.write(inputFile, "1\n100\n".getBytes());

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try (FileInputStream fileIn = new FileInputStream(inputFile.toFile());
             ByteArrayOutputStream outContent = new ByteArrayOutputStream()) {

            System.setIn(fileIn);
            System.setOut(new PrintStream(outContent));

            // Exécuter main
            Main.main(new String[]{});

            String output = outContent.toString();
            assertFalse(output.isEmpty(), "Main devrait afficher quelque chose");

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    private void testWithInput(String input, String expectedContainedText) throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            // Préparer l'entrée
            ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            // Capturer la sortie
            ByteArrayOutputStream testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));

            // Exécuter main
            Main.main(new String[]{});

            // Vérifier la sortie
            String output = testOut.toString();
            assertTrue(output.contains(expectedContainedText),
                    "La sortie devrait contenir '" + expectedContainedText +
                            "'\nSortie réelle:\n" + output);

        } finally {
            // Restaurer les flux
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}