package services;

import gui.boites_de_dialogues.DialogTypeJoueurCharger;
import static interfaces.InterfaceJavaC.charger;
import static interfaces.InterfaceJavaC.sauvegarder;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Gestion des sauvegarde et du chargement des partie.
 *
 * @author Mesté Rémi
 */
public class GestionSauvegardes {

    private final static String EXTENSION = "sav";
    private static String CHEMIN_DOSSIER_SAVE = "./save/";

    private static String nomFichier;
    private static JFileChooser selecteurDeFichier = new javax.swing.JFileChooser();
    private static FileNameExtensionFilter filtre;

    private static void verificationDossierSave() {
        File save = new File(CHEMIN_DOSSIER_SAVE);
        if (!(save.exists() && save.isDirectory())) {
            if (!save.mkdir()) {
                CHEMIN_DOSSIER_SAVE = "~/Desktop/";
            }
        }
    }

    /**
     * Interactions permettant le chargement d'une partie.
     */
    public static void chargerPartie() {
        verificationDossierSave();
        filtre = new FileNameExtensionFilter("Fichier de sauvegarde", EXTENSION);
        selecteurDeFichier.setAcceptAllFileFilterUsed(false);
        selecteurDeFichier.setCurrentDirectory(new java.io.File(CHEMIN_DOSSIER_SAVE));
        selecteurDeFichier.setFileFilter(filtre);
        selecteurDeFichier.setFileHidingEnabled(true);
        int returnValue = selecteurDeFichier.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = selecteurDeFichier.getSelectedFile();
            nomFichier = selectedFile.getAbsolutePath();
            if (!charger(nomFichier)) {
                System.out.println("-- Impossible de charger la partie");
                JOptionPane.showMessageDialog(null, "Impossible de charger la partie depuis:\n" + nomFichier,
                        "Erreur de chargement", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("-- Partie charger " + nomFichier);
                new DialogTypeJoueurCharger().setVisible(true);
            }
        } else {
            System.out.println("-- Fermeture de la fenetre de chargement");
        }
    }

    /**
     * Interactions permettant de sauvegarder une partie.
     */
    public static void sauvegarderPartie() {
        verificationDossierSave();
        filtre = new FileNameExtensionFilter("*." + EXTENSION, EXTENSION);
        selecteurDeFichier.setAcceptAllFileFilterUsed(false);
        selecteurDeFichier.setCurrentDirectory(new java.io.File(CHEMIN_DOSSIER_SAVE));
        selecteurDeFichier.setFileFilter(filtre);
        selecteurDeFichier.setFileHidingEnabled(true);
        selecteurDeFichier.setDialogTitle("Sauvegarder");
        selecteurDeFichier.setApproveButtonText("Enregister");
        int returnValue = selecteurDeFichier.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = selecteurDeFichier.getSelectedFile();
            nomFichier = selectedFile.getAbsolutePath() + "." + EXTENSION;
            System.out.println("-- Sauvegarde de la partie dans " + nomFichier);
            if (sauvegarder(nomFichier) == 1) //une erreur est survenue.
            {
                JOptionPane.showMessageDialog(null, "Impossible de sauvegarder la partie dans:\n" + nomFichier,
                        "Erreur de sauvegarde", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Sauvegardé avec succès dans\n" + nomFichier, "Information",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } else {
            System.out.println("-- Fermeture du menu de sauvegarde");
        }

    }
}
