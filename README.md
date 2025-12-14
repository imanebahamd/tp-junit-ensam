TP JUnit - ENSA Marrakech
📚 Description

Travaux Pratiques sur JUnit pour le module d'Ingénierie Logicielle - Filière Génie Informatique

🏗️ Structure du Projet
Exercice 1 : Conversion de Devises

    CurrencyConverter : Conversion entre MAD et EUR

    ExchangeRate : Gestion des taux de change

    Tests unitaires avec cas limites (valeurs nulles, négatives, conversions)

Exercice 2 : Système de Gestion de Bibliothèque

    Livre : Classe de base avec validation

    Tests JUnit 4 et 5 pour compatibilité

    Tests de performance et fuites mémoire

    Validation des données (titre, auteur, année, ISBN)

Exercice 3 : Gestion Avancée des Dates

    LivreAvance avec LocalDate

    Validation des dates (1000 - présent)

    Tests de limites et exceptions

    Compatibilité avec l'ancienne version

Exercice 4 : Intégration JaCoCo

    Configuration Maven avec JaCoCo

    Tests de couverture de code

    Objectif de 80% de couverture

    Rapport HTML/XML généré automatiquement

Exercice 5 : Gestion de Fichiers

    FileManager : Lecture/écriture de fichiers

    Gestion des exceptions (fichiers inexistants, permissions)

    Tests avec fichiers temporaires

🚀 Installation et Exécution
Prérequis

    Java 11 ou supérieur
    Maven 3.6+
    

Commandes

# Cloner le projet
git clone https://github.com/imanebahamd/tp-junit-ensam.git
cd tp-junit-ensam

# Compiler et exécuter les tests
mvn clean test

# Générer le rapport de couverture JaCoCo
mvn jacoco:report

# Ouvrir le rapport (Mac)
open target/site/jacoco/index.html

# Ouvrir le rapport (Windows)
start target/site/jacoco/index.html

# Ouvrir le rapport (Linux)
xdg-open target/site/jacoco/index.html

# Exécuter les tests d'un exercice spécifique
mvn test -Dtest="*Exercice1*"
mvn test -Dtest="*Exercice2*"
mvn test -Dtest="*Exercice3*"
mvn test -Dtest="*Exercice4*"
mvn test -Dtest="*Exercice5*"

# Package JAR exécutable
mvn clean package

# Nettoyer et tout regénérer
mvn clean install


📊 Technologies Utilisées

    Java 11

    JUnit 5 (avec compatibilité JUnit 4)

    Maven pour la gestion des dépendances

    JaCoCo pour la couverture de code

    Git/GitHub pour le contrôle de version


🧪 Cas de Tests Couverts

    Tests unitaires : Chaque méthode testée individuellement

    Tests d'intégration : Interaction entre composants

    Tests de performance : Temps d'exécution raisonnable

    Tests de limites : Valeurs extrêmes et cas limites

    Tests d'exception : Gestion des erreurs

    Tests de compatibilité : JUnit 4 et JUnit 5

📈 Couverture de Code

    Objectif : ≥ 80% de couverture

    Outils : JaCoCo intégré avec Maven

    Rapports : HTML, XML, CSV disponibles

    Métriques : Instructions, branches, lignes, méthodes
    









