package launcher_jeu_hex;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static launcher_jeu_hex.SystemeExploitation.*;
import static launcher_jeu_hex.Fichiers.*;
import static launcher_jeu_hex.Internet.*;

/**
 * Lanceur d'application java avec systeme de mise a jour et d'installation.
 *
 * @author Rémi
 */
public class gui extends javax.swing.JFrame {

    public static final String DOSSIER_INSTALLATION_LINUX = "./";
    public static final String DOSSIER_INSTALLATION_WINDOWS = "./";
    public static final String NOM_LOGICIEL = "HexGame";
    public static final String NOM_FICHIER_MANIFEST = "manifest.txt";
    public static final String MESSAGE_LANCEMENT = "Bonne partie.";
    public static final String MESSAGE_ERREUR1 = "Erreur: une connexion internet est nécessaire.";
    public static final String URL_DEPOT = "http://erim.fr/projets/hexgame/depot_dl/";
    public static final String VERSION = "23-04-2017";
    private static final String FICHIER_A_LANCER = "Client_jeu_hex.jar";

    //attributs
    private int xSouris;
    private int ySouris;

    private final String cheminInstallation;
    private final String cheminLogiciel;
    private final String cheminManifest;

    private int nombreFichiersTelecharge = 0;
    private int nombreFichiersATelecharger = 0;

    //Constructeur de l'interface
    public gui() {
        //Initialisation graphique

        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("assets/titreFont.ttf")).deriveFont(Font.PLAIN, 14);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        message.setText("Recherche de mise a jour");
        credit.setText("Version: " + VERSION);

        lauch_button.setVisible(false);

        //Gestion du chemin d instalation en fonction de l os
        String os = getOs();
        System.out.println("Nom du SE: " + os);
        if (estWindows(os)) {
            this.cheminInstallation = DOSSIER_INSTALLATION_WINDOWS;
        } else //linux
        {
            this.cheminInstallation = DOSSIER_INSTALLATION_LINUX;
        }

        this.cheminLogiciel = this.cheminInstallation + NOM_LOGICIEL + "/";
        this.cheminManifest = this.cheminInstallation + NOM_LOGICIEL + "/" + NOM_FICHIER_MANIFEST;

        System.out.println("-- Chemin du dossier d'installation: " + cheminInstallation);
        new Thread() {
            public void run() {
                actualisationMAJ();
            }
        }.start();
    }

    /**
     * Gere l'avancement des mises a jours / installations en fonction de la
     * situation.
     */
    private void actualisationMAJ() {
        if (accesInternet()) {
            System.out.println("-- Verification internet OK");

            //si le dossier existe
            if (dossierInstallationDejaExistant(cheminLogiciel)) {
                System.out.println("-- Le dossier d'installation existe.");

                //verifier maniefest
                System.out.println("-- Verification existance du manifest : " + cheminManifest);
                if (manifestDejaExistant(cheminManifest)) {
                    String version = lireVersionLocale(cheminManifest);
                    String versionS = getVersionEnLigne();
                    System.out.println("-- version locale \t" + version);
                    System.out.println("-- version serveur  \t" + versionS);
                    if (!version.equals(versionS)) {
                        telechargementGlobal();
                        actualisationMAJ();
                    } else {
                        lancement();
                    }
                } else {
                    //supprmier tout le contenu du dossier
                    System.out.println("-- Vidage du dossier du logiciel: " + cheminLogiciel);
                    telechargementGlobal();
                    actualisationMAJ();
                }
            } else {
                System.out.println("-- Le dossier d'installation existe pas.");
                //On creer le dossier;
                System.out.println("-- Creation du dossier pour le logiciel: " + cheminInstallation + NOM_LOGICIEL + "/");
                new File(cheminLogiciel).mkdirs();
                telechargementGlobal();
                actualisationMAJ();
            }

        } else {
            System.out.println("-- Verification internet KO");
            //presupose que l'integrite du projet est valide
            if (dossierInstallationDejaExistant(cheminLogiciel)
                    && (manifestDejaExistant(cheminManifest))) {

                //tout est ok on permet de lancer
                lancement();
            } else { //message d'erreur impossible de telecharger le jeu ou de le lancer
                if (!dossierInstallationDejaExistant(cheminLogiciel)) {
                    System.out.println("-- Le dossier d'installation n existe pas.");
                }

                if (!manifestDejaExistant(cheminManifest)) {
                    System.out.println("-- Le manifest n existe pas.");
                }

                messageErreur(MESSAGE_ERREUR1);
            }
        }
    }

    /**
     * Supprime les anciens fichiers/ dossier du dossier d'installation.
     */
    private void telechargementGlobal() {
        final String urlManfiest = URL_DEPOT + "manifest.txt";
        List<String> listFichiers = new ArrayList<>();
        URL oracle;

        surppressionContenuDossierRecursif(cheminLogiciel);

        try {
            //telechargement du nouveau manifest
            telecharger(urlManfiest);
        } catch (IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Récuperation des informations en ligne
        try {
            oracle = new URL(urlManfiest);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String ligne;
            while ((ligne = in.readLine()) != null) {
                if (ligne.charAt(0) == '@') {
                    this.nombreFichiersATelecharger += 1;
                    listFichiers.add(ligne.replace("@", ""));
                }
            }
            nombreFichiersATelecharger += 1; //le manifest lui meme
            in.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }

        //verification des versions
        for (String url : listFichiers) {
            try {
                //construction des sous dossiers
                String repertoires = "";
                String temp[] = url.split("/");
                for (int i = 0; i < temp.length - 1; i++) {
                    repertoires += temp[i] + "/";
                    if (!dossierInstallationDejaExistant(cheminLogiciel + repertoires));
                    System.out.println("-- Création du dossier: " + cheminLogiciel + repertoires);
                    new File(cheminLogiciel + repertoires).mkdirs();
                }
                telecharger(URL_DEPOT + url);
                message.setText("Téléchargement des fichiers " + nombreFichiersTelecharge + " / " + nombreFichiersATelecharger);
            } catch (IOException ex) {
                Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Télécharge le fichier provenant de l'url passé en parametre.
     *
     * @param urlFichier
     * @throws MalformedURLException
     * @throws IOException
     */
    public void telecharger(String urlFichier) throws MalformedURLException, IOException {
        Thread t;
        String cheminCompletSortie;
        cheminCompletSortie = (this.cheminLogiciel + urlFichier.replace(URL_DEPOT, ""));

        t = new Thread() {
            @Override
            public void run() {
                System.out.println("-- Début du téléchargement du fichier (" + urlFichier + ")");
                long totalDownload = 0;                      // total bytes downloaded
                final int BUFFER_SIZE = 1024;                   // size of the buffer
                int tailleFichierServeur = recupererTailleAvecUrl(urlFichier);
                byte[] data = new byte[BUFFER_SIZE];               // buffer
                File file = new File(cheminCompletSortie);
                OutputStream out;

                BufferedInputStream in;
                try {
                    out = new FileOutputStream(file);
                    in = new BufferedInputStream(new URL(urlFichier).openStream());
                    int dataRead;                          // data read in each try
                    while ((dataRead = in.read(data, 0, 1024)) > 0) {
                        totalDownload += dataRead;                    // adding data downloaded to total data
                        out.write(data, 0, dataRead);
                        float percent = ((float) totalDownload / (float) tailleFichierServeur) * 100;
                        progress_bar.setValue((int) percent);

                    }
                    //Fermer les flux
                    out.close();
                    in.close();

                } catch (MalformedURLException ex) {

                } catch (IOException e) {
                }

            }
        };
        t.start();
        this.nombreFichiersTelecharge += 1;
    }

    /**
     * Affiche un message d'erreur.
     *
     * @param message
     */
    private void messageErreur(String message) {
        this.message.setForeground(Color.red);
        this.message.setText(message);
    }

    /**
     * Lance le programme cible du launcher.
     */
    private void lancement() {
        System.out.println("Lancement du logiciel: " + NOM_LOGICIEL);
        this.message.setText(MESSAGE_LANCEMENT);
        this.message.setForeground(Color.black);
        progress_bar.setVisible(false);
        lauch_button.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boutonFenetreFermer = new javax.swing.JLabel();
        boutonFenetreReduction = new javax.swing.JLabel();
        titre_logiciel = new javax.swing.JLabel();
        credit = new javax.swing.JLabel();
        DeplacementFenetreRegion = new javax.swing.JLabel();
        haut_fenetre_fond_repeat = new javax.swing.JLabel();
        guiFond_bas_fenetre = new javax.swing.JLabel();
        lauch_button = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        progress_bar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boutonFenetreFermer.setFont(new java.awt.Font("Napalm Strike", 0, 18)); // NOI18N
        boutonFenetreFermer.setForeground(new java.awt.Color(240, 240, 240));
        boutonFenetreFermer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        boutonFenetreFermer.setText("X");
        boutonFenetreFermer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boutonFenetreFermer.setPreferredSize(new java.awt.Dimension(30, 30));
        boutonFenetreFermer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boutonFenetreFermerMouseClicked(evt);
            }
        });
        getContentPane().add(boutonFenetreFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, -1, 20));

        boutonFenetreReduction.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 24)); // NOI18N
        boutonFenetreReduction.setForeground(new java.awt.Color(255, 255, 255));
        boutonFenetreReduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        boutonFenetreReduction.setText("-");
        boutonFenetreReduction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boutonFenetreReduction.setPreferredSize(new java.awt.Dimension(30, 30));
        boutonFenetreReduction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boutonFenetreReductionMouseClicked(evt);
            }
        });
        getContentPane().add(boutonFenetreReduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 20, 20));

        titre_logiciel.setFont(new java.awt.Font("Enter The Grid", 1, 34)); // NOI18N
        titre_logiciel.setForeground(new java.awt.Color(255, 255, 255));
        titre_logiciel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre_logiciel.setText("hex game");
        getContentPane().add(titre_logiciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 710, -1));

        credit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        credit.setForeground(new java.awt.Color(240, 240, 240));
        credit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 20));

        DeplacementFenetreRegion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                DeplacementFenetreRegionMouseDragged(evt);
            }
        });
        DeplacementFenetreRegion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DeplacementFenetreRegionMousePressed(evt);
            }
        });
        getContentPane().add(DeplacementFenetreRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 20));

        haut_fenetre_fond_repeat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        haut_fenetre_fond_repeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/launcher_jeu_hex/assets/haut_de_fenetre_fond.png"))); // NOI18N
        getContentPane().add(haut_fenetre_fond_repeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        guiFond_bas_fenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guiFond_bas_fenetre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/launcher_jeu_hex/assets/bas_de_fenetre_fond.png"))); // NOI18N
        guiFond_bas_fenetre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(guiFond_bas_fenetre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 720, -1));

        lauch_button.setText("JOUER");
        lauch_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lauch_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(lauch_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 160, 40));

        message.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 720, 20));

        progress_bar.setForeground(new java.awt.Color(0, 211, 40));
        progress_bar.setString("");
        getContentPane().add(progress_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 320, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Modifie les coordonnée de la fenetre lorsque elle est déplacée.
     *
     * @param evt
     */
    private void DeplacementFenetreRegionMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeplacementFenetreRegionMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xSouris, y - ySouris);
    }//GEN-LAST:event_DeplacementFenetreRegionMouseDragged

    /**
     * Actualise les coordonnées de la fenetre lorsque l'on clique sur le haut
     * de la fenetre.
     *
     * @param evt
     */
    private void DeplacementFenetreRegionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeplacementFenetreRegionMousePressed
        xSouris = evt.getX();
        ySouris = evt.getY();
    }//GEN-LAST:event_DeplacementFenetreRegionMousePressed

    /**
     * Interaction lors du clic sur le X de la fenetre.
     *
     * @param evt
     */
    private void boutonFenetreFermerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boutonFenetreFermerMouseClicked
        System.exit(0);
    }//GEN-LAST:event_boutonFenetreFermerMouseClicked

    /**
     * Interaction lors du click sur le - pour reduire la fenetre.
     *
     * @param evt
     */
    private void boutonFenetreReductionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boutonFenetreReductionMouseClicked
        this.setState(gui.ICONIFIED);
    }//GEN-LAST:event_boutonFenetreReductionMouseClicked

    /**
     * Lance le jar cible lorsque l'on clique sur le bouton.
     *
     * @param evt
     */
    private void lauch_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lauch_buttonActionPerformed
        final String fichierExecutable = this.cheminLogiciel + FICHIER_A_LANCER;
        System.out.println("LANCER " + fichierExecutable);
        try {
            Runtime.getRuntime().exec(new String[]{"java", "-jar", fichierExecutable});
            //cacher l interface du launcher
            this.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lauch_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DeplacementFenetreRegion;
    private javax.swing.JLabel boutonFenetreFermer;
    private javax.swing.JLabel boutonFenetreReduction;
    private javax.swing.JLabel credit;
    private javax.swing.JLabel guiFond_bas_fenetre;
    private javax.swing.JLabel haut_fenetre_fond_repeat;
    private javax.swing.JButton lauch_button;
    private javax.swing.JLabel message;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JLabel titre_logiciel;
    // End of variables declaration//GEN-END:variables
}
