package interfaces;

import java.io.IOException;

/**
 * Iterface de connexion entre l'affichage en java et le traitement en C. Le .so
 * ou le .dll est inclut dans le jar. Pour cela on utilise la classe opensource
 * NativeUtils
 *
 * @author Rémi Mesté
 */
public class InterfaceJavaC {

    static final String NOM_LIB = "/libInterfaceJavaC";

    static {
        if (System.getProperty("os.name").startsWith("Windows")) {
            try {
                NativeUtils.loadLibraryFromJar(NOM_LIB + ".dll");
            } catch (IOException a) {
            }
        } else {
            try {    //Linux
                NativeUtils.loadLibraryFromJar(NOM_LIB + ".so");
            } catch (IOException e) {
            }
        }
    }

    public static native void debutPartie(int taille);

    public static native boolean jouer(int x, int y, char couleurJoueur);

    public static native char getCase(int x, int y);

    public static native boolean retourArriere();

    public static native void supprimerPlateau();

    public static native int sauvegarder(String nomFichierSauvegarde);

    public static native boolean charger(String nomFichierSauvegarde);

    public static native boolean estTerminePartie();

    public static native int getTaille();

    public static native int getNbrCoup();

    public static native void coupIA(char couleur, int niveauDifficulte);
}
