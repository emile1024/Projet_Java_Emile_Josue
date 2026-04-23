import java.util.ArrayList;
import java.io.*;

public class GestionEtudiants {

    private ArrayList<Etudiant> etudiants;   // Liste dynamique des étudiants

    public GestionEtudiants() {
        etudiants = new ArrayList<>();
    }

    private Etudiant chercher(String prenom, String nom) { // Recherche d’un étudiant par prénom et nom
        for (Etudiant e : etudiants) {
            if (e.getPrenom().equalsIgnoreCase(prenom)     // insensible à la casse
                    && e.getNom().equalsIgnoreCase(nom)) {
                return e;
            }
        }
        return null;
    }

    public void ajouterOuModifier(String prenom, String nom, String cours, double note) {

        Etudiant e = chercher(prenom, nom);

        if (e == null) {
            e = new Etudiant(prenom, nom);
            etudiants.add(e);
        }

        e.ajouterOuModifierNote(cours, note);
    }

    public void supprimerEtudiant(String prenom, String nom) {

        Etudiant e = chercher(prenom, nom);

        if (e == null) {
            System.out.println("❌ Introuvable !");
            return;
        }

        etudiants.remove(e);
        System.out.println("✅ Supprimé avec succès !");
    }

    public void afficherTous() {

        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
            return;
        }

        System.out.printf("%-10s %-12s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-10s\n",
                "Prénom","Nom","PRA","BDA","OIN","IIA","STA","BDM","SRD","Moyenne");

        for (Etudiant e : etudiants) {
            e.afficher();
        }
    }

    public void afficherIncomplets() {

        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
            return;
        }

        System.out.printf("%-10s %-12s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-10s\n",
                "Prénom","Nom","PRA","BDA","OIN","IIA","STA","BDM","SRD","Moyenne");

        for (Etudiant e : etudiants) {
            if (!e.estComplet()) {
                e.afficher();
            }
        }
    }

    public void trierParMoyenne() {

        etudiants.sort((a, b) ->
                Double.compare(b.calculerMoyenne(), a.calculerMoyenne()));
    }

    public boolean exporterCSV() {        // Exportation des données vers un fichier CSV

        try {
            FileWriter writer = new FileWriter("resultats.csv");

            writer.write("Prénom,Nom,PRA,BDA,OIN,IIA,STA,BDM,SRD,Moyenne\n");

            for (Etudiant e : etudiants) {
                writer.write(e.toCSV() + "\n");
            }

            writer.close();
            return true; // succès

        } catch (IOException e) {
            return false; // échec
        }
    }

    public void chargerDepuisCSV() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("resultats.csv"));

            etudiants.clear();   // Nettoyage de la liste pour éviter les doublons lors du chargement

            String ligne = reader.readLine();

            while ((ligne = reader.readLine()) != null) {

                String[] data = ligne.split(",");

                Etudiant e = new Etudiant(data[0], data[1]);

                if (!data[2].isEmpty()) e.ajouterOuModifierNote("PRA", Double.parseDouble(data[2]));
                if (!data[3].isEmpty()) e.ajouterOuModifierNote("BDA", Double.parseDouble(data[3]));
                if (!data[4].isEmpty()) e.ajouterOuModifierNote("OIN", Double.parseDouble(data[4]));
                if (!data[5].isEmpty()) e.ajouterOuModifierNote("IIA", Double.parseDouble(data[5]));
                if (!data[6].isEmpty()) e.ajouterOuModifierNote("STA", Double.parseDouble(data[6]));
                if (!data[7].isEmpty()) e.ajouterOuModifierNote("BDM", Double.parseDouble(data[7]));
                if (!data[8].isEmpty()) e.ajouterOuModifierNote("SRD", Double.parseDouble(data[8]));

                etudiants.add(e);
            }

            reader.close();
            System.out.println("✅ Chargement réussi");

        } catch (IOException e) {
            System.out.println("📂 Erreur de lecture du fichier CSV");
        }
    }
}