package ma.ensa.marrakech.exercice5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class FileManagerTest {

    private FileManager fileManager;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
    }

    @Test
    void testReadFileExistant() throws IOException {
        // Créer un fichier temporaire
        Path testFile = tempDir.resolve("test.txt");
        String contenuAttendu = "Ceci est un test\nAvec plusieurs lignes";
        Files.writeString(testFile, contenuAttendu);

        // Lire le fichier
        String contenuLu = fileManager.readFile(testFile.toString());

        assertEquals(contenuAttendu, contenuLu);
    }

    @Test
    void testReadFileInexistant_ThrowsIOException() {
        Path fichierInexistant = tempDir.resolve("inexistant.txt");

        IOException exception = assertThrows(IOException.class, () -> {
            fileManager.readFile(fichierInexistant.toString());
        });

        assertTrue(exception.getMessage().contains("n'existe pas"));
    }

    @Test
    void testReadFileRepertoire_ThrowsIOException() {
        // Essayer de lire un répertoire
        IOException exception = assertThrows(IOException.class, () -> {
            fileManager.readFile(tempDir.toString());
        });

        assertTrue(exception.getMessage().contains("fichier régulier"));
    }

    @Test
    void testReadFileAvecEncoding() throws IOException {
        Path testFile = tempDir.resolve("test-utf8.txt");
        String contenu = "Test avec accents: éèàç";
        Files.writeString(testFile, contenu);

        String contenuLu = fileManager.readFileWithEncoding(testFile.toString(), "UTF-8");
        assertEquals(contenu, contenuLu);
    }

    @Test
    void testReadFileGrand() throws IOException {
        // Créer un fichier de 1MB
        Path grandFichier = tempDir.resolve("grand.txt");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            builder.append("Ligne ").append(i).append(": ").append("x".repeat(100)).append("\n");
        }
        Files.writeString(grandFichier, builder.toString());

        // Vérifier qu'on peut lire le fichier
        String contenu = fileManager.readFile(grandFichier.toString());
        assertTrue(contenu.length() > 1000000); // Plus d'1MB
    }

    @Test
    void testReadFileVide() throws IOException {
        Path fichierVide = tempDir.resolve("vide.txt");
        Files.createFile(fichierVide);

        String contenu = fileManager.readFile(fichierVide.toString());
        assertEquals("", contenu);
    }
}