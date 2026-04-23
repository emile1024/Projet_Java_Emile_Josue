import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Lecture des entrées utilisateur
        GestionEtudiants gestion = new GestionEtudiants(); // Gestionnaire principal des étudiants (logique métier)

        gestion.chargerDepuisCSV();   // Chargement des données depuis le fichier CSV

        int choix = -1;

        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Ajouter / Modifier");
            System.out.println("2. Supprimer");
            System.out.println("3. Afficher tous");
            System.out.println("4. Afficher étudiants incomplets");
            System.out.println("5. Classement par moyenne");
            System.out.println("6. Exporter CSV");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");

            try {
                choix = sc.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Erreur ! Nombre entier attendu.");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (choix) {

                case 1:
                    System.out.print("Prénom : ");
                    String p = sc.nextLine();

                    System.out.print("Nom : ");
                    String n = sc.nextLine();

                    String c;

                    while (true) {
                        System.out.print("Cours (PRA,BDA,OIN,IIA,STA,BDM,SRD) : ");
                        c = sc.nextLine().toUpperCase();

                        if (Etudiant.estCoursValide(c)) break;

                        System.out.println("❌ Cours invalide !");
                    }

                    double note;

                    try {             // Lecture sécurisée du choix utilisateur
                        System.out.print("Note : ");
                        note = sc.nextDouble();

                        if (note < 0 || note > 100) {
                            System.out.println("❌ Note invalide !");
                            sc.nextLine();
                            break;
                        }

                    } catch (Exception e) {
                        System.out.println("❌ Mauvaise saisie !");
                        sc.nextLine();
                        break;
                    }

                    sc.nextLine();

                    gestion.ajouterOuModifier(p, n, c, note); // Appel de la logique métier
                    gestion.exporterCSV();    // Sauvegarde automatique après ajout ou modification
                    break;

                case 2:
                    System.out.print("Prénom : ");
                    String p2 = sc.nextLine();

                    System.out.print("Nom : ");
                    String n2 = sc.nextLine();

                    gestion.supprimerEtudiant(p2, n2);
                    gestion.exporterCSV();    // Sauvegarde automatique après suppression
                    break;

                case 3:
                    gestion.afficherTous();
                    break;

                case 4:
                    gestion.afficherIncomplets();
                    break;

                case 5:
                    gestion.trierParMoyenne();
                    gestion.afficherTous();
                    break;

                case 6:
                    if (gestion.exporterCSV()) {
                        System.out.println("✅ Exportation réussie");
                    } else {
                        System.out.println("❌ Echec d’exportation");
                    }
                    break;

                case 0:
                    gestion.exporterCSV();    // Sauvegarde automatique avant fermeture
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("❌ Choix invalide ! Veuillez entrer un nombre entre 0 et 6.");
            }

        } while (choix != 0);

        sc.close();
    }
}