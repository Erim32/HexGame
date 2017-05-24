package services;

import gui.FenetrePrincipale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Gestin de la lecture de la musique dans la partie.
 *
 * @author Mesté Rémi
 */
public class Musique {

    private final static String FICHIER_MUSIQUE = "musiques/musique.wav";
    private Clip clip = null;
    private AudioInputStream inputStream;

    /**
     * Instanciation, creation de la musique.
     */
    public Musique() {
        File fichierMusique = new File(FICHIER_MUSIQUE);
        File dossierCourant = new File(".");
        
        //verification launcher
        String cheminDossierCourant = dossierCourant.getAbsolutePath();
        
        if(new File(cheminDossierCourant+"/HexGame/").exists()) //Nom a synchroniser avec le launcher
            fichierMusique = new File("./HexGame/"+FICHIER_MUSIQUE);
        try {
            URL chemin = fichierMusique.toURI().toURL();
            inputStream = AudioSystem.getAudioInputStream(chemin);
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Démarer la musique.
     */
    public void demarrer() {
        clip.start();
    }

    /**
     * Mettre en pause la musique.
     */
    public void arreter() {
        clip.stop();
    }
}
