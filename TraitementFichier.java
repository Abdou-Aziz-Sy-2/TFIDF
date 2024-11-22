import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TraitementFichier {
    public static void main(String[] args) {
        try {
            File file = new File("purchases.txt");
            ArrayList<String> motsUniques = new ArrayList<>(); // Liste des mots uniques
            ArrayList<Integer> occurrences = new ArrayList<>(); // Liste des occurrences correspondantes

            int totalMots = 0;

            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\s+|\\p{Punct}+"); // Ignorer espaces et ponctuation

            // Lire les mots du fichier
            while (scanner.hasNext()) {
                String mot = scanner.next().toLowerCase(); // Convertir en minuscule
                if (!mot.trim().isEmpty()) { // Vérifier que le mot n'est pas vide
                    totalMots++;
                    if (motsUniques.contains(mot)) {
                        // Si le mot est déjà dans la liste, incrémenter son occurrence
                        int index = motsUniques.indexOf(mot);
                        occurrences.set(index, occurrences.get(index) + 1);
                    } else {
                        // Sinon, ajouter le mot à la liste et initialiser son occurrence à 1
                        motsUniques.add(mot);
                        occurrences.add(1);
                    }
                }
            }

            scanner.close();

            // Afficher les fréquences des mots
            System.out.println("TF (Term Frequency) pour chaque mot :");
            for (int i = 0; i < motsUniques.size(); i++) {
                String mot = motsUniques.get(i);
                int occ = occurrences.get(i);
                double tf = (double) occ / totalMots; // Calcul du TF
                System.out.printf("Mot : '%s', Occurrences : %d, TF : %.5f%n", mot, occ, tf);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
            e.printStackTrace();
        }
    }
}
