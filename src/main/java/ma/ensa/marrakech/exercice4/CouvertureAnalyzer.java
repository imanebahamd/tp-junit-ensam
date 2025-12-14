package ma.ensa.marrakech.exercice4;

/**
 * Classe utilitaire pour analyser la couverture de code.
 * Cette classe sert principalement à démontrer les fonctionnalités de JaCoCo.
 */
public class CouvertureAnalyzer {

    private int totalClasses;
    private int coveredClasses;
    private double coveragePercentage;

    public CouvertureAnalyzer() {
        this.totalClasses = 0;
        this.coveredClasses = 0;
        this.coveragePercentage = 0.0;
    }

    public void addClassCoverage(boolean isCovered) {
        totalClasses++;
        if (isCovered) {
            coveredClasses++;
        }
        calculatePercentage();
    }

    private void calculatePercentage() {
        if (totalClasses > 0) {
            coveragePercentage = (double) coveredClasses / totalClasses * 100;
        }
    }

    public double getCoveragePercentage() {
        return coveragePercentage;
    }

    public String getCoverageStatus() {
        if (coveragePercentage >= 80) {
            return "EXCELLENT";
        } else if (coveragePercentage >= 70) {
            return "BON";
        } else if (coveragePercentage >= 50) {
            return "MOYEN";
        } else {
            return "INSUFFISANT";
        }
    }

    // Méthode avec plusieurs branches pour tester la couverture
    public String analyzeComplexMethod(int value) {
        if (value < 0) {
            return "NÉGATIF";
        } else if (value == 0) {
            return "ZÉRO";
        } else if (value > 0 && value < 100) {
            return "PETIT POSITIF";
        } else if (value >= 100 && value < 1000) {
            return "MOYEN POSITIF";
        } else {
            return "GRAND POSITIF";
        }
    }
}