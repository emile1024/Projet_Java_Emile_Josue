public class NoteCours {  // Représente une note associée à un cours

    private String codeCours;  // Code du cours et note correspondante
    private double note;

    // Constructeur (Normalisation du code cours)

    public NoteCours(String codeCours, double note) {
        this.codeCours = codeCours.toUpperCase();
        this.note = note;
    }

    // Getters (accesseurs)

    public String getCodeCours() {
        return codeCours;
    }

    public double getNote() {
        return note;
    }

    // Modification de la note

    public void setNote(double note) {
        this.note = note;
    }
}