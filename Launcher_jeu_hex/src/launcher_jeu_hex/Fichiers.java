package launcher_jeu_hex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static launcher_jeu_hex.gui.NOM_LOGICIEL;

/**
 * Fonctions utilisantt le traitement de donnée de type fichier
 *
 * @author Rémi Mesté
 */
public class Fichiers {

    /**
     * Recuperation de la numero de version du client.
     *
     * @param cheminManifest
     * @return numero de version dans le manifest du coté client.
     */
    public static String lireVersionLocale(String cheminManifest) {
        String version = "0.0.0";
        try (BufferedReader br = new BufferedReader(new FileReader(cheminManifest))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                if (line.toLowerCase().contains("version")) {
                    String mots[];
                    mots = line.toLowerCase().split(" ");
                    return mots[1];
                }
            }
        } catch (Exception e) {
        }
        return version;
    }

    /**
     * Teste si le fichier existe et est un fichier.
     *
     * @param chemin
     * @return boolean
     */
    public static boolean manifestDejaExistant(String chemin) {
        File f = new File(chemin);
        return (f.exists() && f.isFile());
    }

    /**
     * Teste si le fichier existe et est un fichier.
     *
     * @param chemin
     * @return boolean
     */
    public static boolean dossierInstallationDejaExistant(String chemin) {
        File f = new File(chemin);
        return (f.exists() && f.isDirectory());
    }

    /**
     * Supprmier le contenu du dossier spécifié
     *
     * @param cheminFichier
     */
    public static void surppressionContenuDossierRecursif(String cheminFichier) {
        final String NOM_DOSSIER_SAUVEGARDE = "save";
        File fichier = new File(cheminFichier);
        if (fichier.isDirectory()) { //Si on est dans un dossier
            //Si il est vide on eput le supprmier
            if ((fichier.list().length == 0) && (!fichier.getName().equals(NOM_LOGICIEL))) {
                fichier.delete();
                System.out.println("-- Dossier supprimé : " + fichier.getAbsolutePath());
            } else {
                //Sinon on recupere le contenu du dossier
                String listeFichiers[] = fichier.list();
                //appel recursif
                for (String listeFichier : listeFichiers) {
                    //suppression recursive
                    surppressionContenuDossierRecursif(cheminFichier + "/" + listeFichier);
                }

                //Si le sous fichier traite es tvide on le supprmie
                if ((fichier.list().length == 0) && (!fichier.getName().equals(NOM_LOGICIEL))) {
                    fichier.delete();
                    System.out.println("-- Dossier supprime : " + fichier.getAbsolutePath());
                }
            }

        } else { // est un fichier
            System.out.println("-- Suppression du fichier : " + fichier.getAbsolutePath());
            if (!fichier.delete()) {
                System.out.println("---- Erreur de suppression du fichier:" + fichier.getAbsolutePath());
            }
        }
    }
}
