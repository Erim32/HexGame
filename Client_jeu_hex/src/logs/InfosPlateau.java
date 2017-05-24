package logs;

import static interfaces.InterfaceJavaC.getCase;
import static interfaces.InterfaceJavaC.getTaille;

/**
 * Affichages des logs détaillés
 *
 * @author remi
 */
public class InfosPlateau {

    /**
     * Affiche les infos détaillé sur le plateau.
     */
    public static void InfosPlateau() {
        System.out.println("-------------------");
        System.out.println("-- Infos plateau --");
        System.out.println("-------------------");
        afficherTaillePlateau();
        System.out.println("-- Grille:");
        afficherGrillePlateau();
        System.out.println("-------------------");
    }

    /**
     * Affiche seulement la taille du plateau.
     */
    public static void afficherTaillePlateau() {
        System.out.println("-- Taille = " + getTaille());
    }

    /**
     * Affiche le plateau au format ascii.
     */
    public static void afficherGrillePlateau() {
        System.out.println("Grille:");
        for (int y = 0; y < getTaille(); y++) {
            String ligne = "";
            for (int x = 0; x < getTaille(); x++) {
                ligne += getCase(y, x);
            }
            System.out.println(ligne);
        }
    }

}
