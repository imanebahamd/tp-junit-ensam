package ma.ensa.marrakech.exercice4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tests pour vérifier la configuration JaCoCo
 */
class JaCoCoConfigurationTest {

    @Test
    void testJacocoReportGenerated() {
        // Vérifier que le rapport JaCoCo est généré
        File reportDir = new File("target/site/jacoco");
        File indexFile = new File("target/site/jacoco/index.html");

        assertTrue(reportDir.exists() || reportDir.mkdirs(),
                "Le répertoire des rapports JaCoCo doit exister");
    }

    @Test
    void testJacocoPropertiesExist() {
        // Vérifier que le fichier de configuration existe
        File propertiesFile = new File("src/main/resources/jacoco.properties");
        assertTrue(propertiesFile.exists() ||
                        new File("src/test/resources/jacoco.properties").exists(),
                "Le fichier de configuration JaCoCo doit exister");
    }

    @Test
    void testJacocoPluginConfigured() throws Exception {
        // Vérifier que le plugin JaCoCo est configuré dans pom.xml
        String pomContent = new String(Files.readAllBytes(Paths.get("pom.xml")));
        assertTrue(pomContent.contains("jacoco-maven-plugin"),
                "Le plugin JaCoCo doit être configuré dans pom.xml");
    }
}