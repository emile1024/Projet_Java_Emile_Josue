import java.util.ArrayList;

public class Etudiant {

    // Liste centralisée des cours
    public static final String[] COURS_VALIDES = {      // Liste des cours autorisés
            "PRA", "BDA", "OIN", "IIA", "STA", "BDM", "SRD"
    };

    public static int getNombreCours() {
        return COURS_VALIDES.length;
    }

    public static boolean estCoursValide(String cours) {   // Vérifie si un cours est valide
        for (int i = 0; i < COURS_VALIDES.length; i++) {
            if (COURS_VALIDES[i].equalsIgnoreCase(cours)) {
                return true;
            }
        }
        return false;
    }

    private String prenom;
    private String nom;
    private ArrayList<NoteCours> notes;  // Liste des notes (structure dynamique)

    public Etudiant(String prenom, String nom) {
        this.prenom = prenom.trim().toUpperCase();
        this.nom = nom.trim().toUpperCase();
        this.notes = new ArrayList<>();
    }

    public void ajouterOuModifierNote(String cours, double note) {

        for (NoteCours n : notes) {
            if (n.getCodeCours().equalsIgnoreCase(cours)) {
                n.setNote(note);
                return;
            }
        }

        notes.add(new NoteCours(cours, note));
    }

    public double calculerMoyenne() {

        double somme = 0;
        int count = 0;

        for (NoteCours n : notes) {
            somme += n.getNote();
            count++;
        }

        if (count == 0) return -1;

        return somme / count;
    }

    public boolean estComplet() {
        return notes.size() == getNombreCours();
    }

    public void afficher() {  // Affichage formaté (ligne du tableau)

        Double pra = null, bda = null, oin = null, iia = null, sta = null, bdm = null, srd = null;

        for (NoteCours n : notes) {
            if (n.getCodeCours().equals("PRA")) pra = n.getNote();
            else if (n.getCodeCours().equals("BDA")) bda = n.getNote();
            else if (n.getCodeCours().equals("OIN")) oin = n.getNote();
            else if (n.getCodeCours().equals("IIA")) iia = n.getNote();
            else if (n.getCodeCours().equals("STA")) sta = n.getNote();
            else if (n.getCodeCours().equals("BDM")) bdm = n.getNote();
            else if (n.getCodeCours().equals("SRD")) srd = n.getNote();
        }

        double m = calculerMoyenne();
        String moyenneStr;

        if (m == -1) {
            moyenneStr = "-";
        } else {
            moyenneStr = String.format("%.2f", m);
        }

        System.out.printf("%-10s %-12s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-10s\n",
                prenom, nom,
                val(pra), val(bda), val(oin), val(iia),
                val(sta), val(bdm), val(srd),
                moyenneStr
        );
    }

    private String val(Double v) {

        if (v == null) {
            return "-";
        } else {
            return String.valueOf(v);
        }

    }

    public String toCSV() {  // Conversion des données en format CSV

        Double pra = null, bda = null, oin = null, iia = null, sta = null, bdm = null, srd = null;

        for (NoteCours n : notes) {
            if (n.getCodeCours().equals("PRA")) pra = n.getNote();
            else if (n.getCodeCours().equals("BDA")) bda = n.getNote();
            else if (n.getCodeCours().equals("OIN")) oin = n.getNote();
            else if (n.getCodeCours().equals("IIA")) iia = n.getNote();
            else if (n.getCodeCours().equals("STA")) sta = n.getNote();
            else if (n.getCodeCours().equals("BDM")) bdm = n.getNote();
            else if (n.getCodeCours().equals("SRD")) srd = n.getNote();
        }

        double m = calculerMoyenne();
        String moyenneStr;

        if (m == -1) {
            moyenneStr = "";
        } else {
            moyenneStr = String.valueOf(m);
        }

        return prenom + "," + nom + "," +
                valCSV(pra) + "," + valCSV(bda) + "," + valCSV(oin) + "," +
                valCSV(iia) + "," + valCSV(sta) + "," + valCSV(bdm) + "," +
                valCSV(srd) + "," + moyenneStr;
    }

    private String valCSV(Double v) {

        if (v == null) {
            return "";
        } else {
            return String.valueOf(v);
        }

    }

    public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
}