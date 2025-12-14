package ma.ensa.marrakech.exercice3;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

public class LivreAvance {
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private String isbn;

    // Constantes pour validation
    private static final LocalDate DATE_MIN = LocalDate.of(1000, 1, 1);
    private static final LocalDate DATE_MAX = LocalDate.now();

    public LivreAvance(String titre, String auteur, LocalDate datePublication, String isbn) {
        setTitre(titre);
        setAuteur(auteur);
        setDatePublication(datePublication);
        setIsbn(isbn);
    }

    // Constructeur alternatif pour compatibilité
    public LivreAvance(String titre, String auteur, int anneePublication, String isbn) {
        this(titre, auteur, LocalDate.of(anneePublication, 1, 1), isbn);
    }

    // Getters
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public int getAnneePublication() {
        return datePublication.getYear();
    }

    public String getIsbn() {
        return isbn;
    }

    // Setters avec validation
    public void setTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide");
        }
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être vide");
        }
        this.auteur = auteur;
    }

    public void setDatePublication(LocalDate datePublication) {
        if (datePublication == null) {
            throw new IllegalArgumentException("La date de publication ne peut pas être nulle");
        }
        validerDatePublication(datePublication);
        this.datePublication = datePublication;
    }

    public void setDatePublication(int annee) {
        setDatePublication(LocalDate.of(annee, 1, 1));
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }
        this.isbn = isbn;
    }

    // Méthode de validation de date
    private void validerDatePublication(LocalDate date) {
        if (date.isBefore(DATE_MIN)) {
            throw new IllegalArgumentException(
                    String.format("La date de publication (%s) est antérieure à l'an 1000", date)
            );
        }
        if (date.isAfter(DATE_MAX)) {
            throw new IllegalArgumentException(
                    String.format("La date de publication (%s) est postérieure à la date actuelle", date)
            );
        }
    }

    // Méthode pour vérifier si la date est raisonnable (public pour les tests)
    public boolean estDateRaisonnable() {
        return !datePublication.isBefore(DATE_MIN) && !datePublication.isAfter(DATE_MAX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivreAvance livre = (LivreAvance) o;
        return Objects.equals(titre, livre.titre) &&
                Objects.equals(auteur, livre.auteur) &&
                Objects.equals(datePublication, livre.datePublication) &&
                Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, auteur, datePublication, isbn);
    }

    @Override
    public String toString() {
        return String.format("LivreAvance{titre='%s', auteur='%s', datePublication=%s, isbn='%s'}",
                titre, auteur, datePublication, isbn);
    }
}