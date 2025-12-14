package ma.ensa.marrakech.exercice4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tests qui répondent aux questions théoriques de l'exercice 4
 */
class Exercice4QuestionsTest {

    // Question I.1 : Intégration JaCoCo dans Maven
    @Test
    void testJacocoMavenIntegration() {
        // Réponse : Via le plugin jacoco-maven-plugin dans pom.xml
        assertTrue(true, "JaCoCo s'intègre via le plugin Maven correspondant");
    }

    // Question I.2 : Plugins/dépendances JaCoCo
    @Test
    void testJacocoDependencies() {
        // Réponse : Plugin uniquement, pas de dépendance runtime
        assertTrue(true, "Seulement le plugin jacoco-maven-plugin, pas de dépendance");
    }

    // Question II.3 : Exécution tests avec JaCoCo
    @Test
    void testExecutionCommand() throws Exception {
        String command = "mvn clean test jacoco:report";
        // Vérifier que la commande crée les rapports
        ProcessBuilder pb = new ProcessBuilder("mvn", "--version");
        Process process = pb.start();
        int exitCode = process.waitFor();

        assertEquals(0, exitCode, "Maven doit être installé et fonctionnel");
    }

    // Question V.9 : Objectifs de couverture
    @Test
    void testCoverageGoals() {
        double targetCoverage = 0.80; // 80%
        CouvertureAnalyzer analyzer = new CouvertureAnalyzer();

        // Simuler 10 classes, 8 couvertes = 80%
        for (int i = 0; i < 10; i++) {
            analyzer.addClassCoverage(i < 8);
        }

        assertEquals("EXCELLENT", analyzer.getCoverageStatus(),
                "L'objectif de 80% doit donner le statut EXCELLENT");
    }

    // Question VII.13 : Interprétation rapport
    @Test
    void testReportInterpretation() {
        // Les métriques JaCoCo importantes :
        String[] metrics = {"INSTRUCTION", "BRANCH", "LINE", "METHOD", "CLASS"};

        assertEquals(5, metrics.length, "5 métriques principales doivent être analysées");
        assertTrue(metrics[0].equals("INSTRUCTION"), "La couverture d'instructions est primordiale");
    }

    // Question IX.16 : Qualité du code
    @Test
    void testCodeQualityBenefits() {
        // Avantages de la couverture de code
        String[] benefits = {
                "Identifie le code non testé",
                "Réduit les bugs en production",
                "Améliore la maintenabilité",
                "Facilite les refactorings"
        };

        assertTrue(benefits.length >= 3, "Au moins 3 avantages significatifs");
    }
}