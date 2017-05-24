package gui;

import gui.boites_de_dialogues.DialogGagnant;
import gui.grille.Hexagone;
import gui.grille.Plateau;
import joueurs.IA;
import joueurs.Joueur;
import joueurs.Personnage;
import joueurs.Personnage.TypePersonnages;
import static joueurs.Personnage.TypePersonnages.JOUEUR;
import interfaces.InterfaceJavaC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logs.InfosPlateau;
import static interfaces.InterfaceJavaC.*;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;

/**
 * Programme principal d'une partie
 *
 * @author Rémi Mesté
 */
public final class InterfacePartie extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    //Constantes
    private final static int SOURIS_DECALAGE_X_PIXELS = -15;
    private final static int SOURIS_DECALAGE_Y_PIXELS = -15;
    private final static Color COULEUR_ERREUR_MESSAGE = Color.RED;
    //attributs
    private final int taille;
    private static int numeroTour = 1;
    private Plateau plat;
    private static Personnage personnageTourActuel;
    private static Personnage personnageTourSuivant;
    private MenuDurantParie menuPartie;
    private boolean termine = false;
    //Attributs
    private final Personnage joueur1;
    private final Personnage joueur2;

    /**
     * Création de l'Interface de la partie losque l'on charge la grille.
     *
     * @param taille
     * @param j2
     */
    public InterfacePartie(int taille, Personnage j2) {
        initComponents();
        addMouseListener(this);
        addMouseMotionListener(this);

        //Affectation des attributs
        InterfacePartie.numeroTour = InterfaceJavaC.getNbrCoup() + 1;

        //Affectation des attributs
        this.joueur1 = new Joueur("vous", Color.BLUE, 'x');
        this.joueur2 = j2;
        this.taille = taille;
        System.out.println("-- Recuperation de la partie charge.");
        initilisaterPartie(taille);

        //Initialisation de la partie.
        rafraichirAffichageGrille();
    }

    /**
     * Création de l'Interface de la partie depuis le menu de configuration de
     * la partie.
     *
     * @param taille
     * @param typeJ1
     * @param typeJ2
     * @param niveauDifficulteIA
     */
    InterfacePartie(int taille, Personnage j1, Personnage j2) {
        initComponents();
        addMouseListener(this);
        addMouseMotionListener(this);

        //Affectation des attributs
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.taille = taille;
        System.out.println("-- Initialisation " + j1.getMessageTour());
        System.out.println("-- Au tour du " + j2.getMessageTour());

        initilisaterPartie(taille);

        //Initialisation de la partie.
        debutPartie(this.taille);
        rafraichirAffichageGrille();
    }

    /**
     * Initilisation du contenu graphique de la partie en fonction de la taille
     * de la la grille.
     *
     * @param taille
     */
    private void initilisaterPartie(int taille) {

        //decalage de 1 du numero car on parle pour le prochain coup (t + 1).
        System.out.println("-- Initilisation de la partie." + (numeroTour + 1) % 2);
        personnageTourActuel  = getPersonnageTour((numeroTour + 1) % 2);
        personnageTourSuivant = getPersonnageTour(numeroTour % 2);
        
        labelJoueur.setText(getMessageTourJoueur((numeroTour + 1) % 2));
        labelJoueur.setForeground(personnageTourActuel.getCouleur());
        sousTexte.setForeground(personnageTourActuel.getCouleur());
        if(personnageTourActuel.getType().equals(JOUEUR))
            sousTexte.setVisible(false);

        //personalisation graphique de lancement;
        erreurText.setForeground(COULEUR_ERREUR_MESSAGE);
        erreurText.setText("");

        //Chargement du plateau des cases.
        grillePanel.removeAll();
        grillePanel.setLayout(new BorderLayout());
        grillePanel.add((plat = new Plateau(taille)), BorderLayout.NORTH);

        //Ajout du menu de la partie
        JPanel menuPanel = FenetrePrincipale.getMenu_principal();

        menuPartie = new MenuDurantParie(this);
        menuPanel.removeAll();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(menuPartie, BorderLayout.CENTER);

        menuPanel.repaint();
        menuPanel.revalidate();

        menuPartie.verrouillerBoutonRetour();
        
        
    }

    /*====================================
                Accesseur:
    =======================================*/
 /* ======================================
                 Getteurs
    ==========================================*/
    /**
     * Recupere le champs message pour le joueur.
     *
     * @return String
     */
    public JLabel getMessageJoueurLabelPartie() {
        return labelJoueur;
    }

    /**
     * Recupere le champs dedié au message d'erreur.
     *
     * @return String
     */
    public JLabel getMessageErreurLabelPartie() {
        return erreurText;
    }

    /**
     * Permet d'acceder a la taille du plateau.
     *
     * @return int
     */
    public int getTaillePlateau() {
        return this.taille;
    }

    /**
     * Permet d'acceder au plateau de la partie.
     *
     * @return Platrau
     */
    public Plateau getPlateau() {
        return this.plat;
    }

    //tour impair message + texte pour j2
    /**
     * Retourne la couleur du joueur qui doit joueur en fonction du numero du
     * tour.
     *
     * @param n
     * @return Color
     */
    public Color getColorMessageTourJoueur(int n) {
        switch (n) {
            case 0:
                return joueur1.getCouleur();
            case 1:
                return joueur2.getCouleur();
            default:
                System.err.println("Numero tour invalide. (n = " + n + ").");
                return Color.BLACK;
        }
    }

    public Personnage getPersonnageTour(int n) {
        if (n % 2 == 0) {
            return joueur1;
        } else {
            return joueur2;
        }
    }

    /**
     * Retourne le message du joueur qui doit joueur en fonction du numero du
     * tour.
     *
     * @param n
     * @return String
     */
    public String getMessageTourJoueur(int n) {
        switch (n) {
            case 0:
                return joueur1.getMessageTour();
            case 1:
                return joueur2.getMessageTour();
            default:
                System.err.println("-- n = " + n);
                return "Numéro de joueur invalide.";
        }
    }

    /**
     * Retourne la couleur du joueur en fonction de son numero.
     *
     * @param n
     * @return Color
     */
    public Color getCouleurJoueur(int n) {
        if (n == 1) {
            return joueur1.getCouleur();
        } else {
            return joueur2.getCouleur();
        }
    }

    /**
     * Permet d'acceder au numero du tour actuel de la partie.
     *
     * @return int
     */
    public static int getNumeroTour() {
        return numeroTour;
    }

    /* ================================================
    Setteurs
    ===================================================*/
    /**
     * Permet de modifer la valeur du numero de coup joué.
     *
     * @param n
     */
    public void setNumeroCoup(int n) {
        numeroTour = n;
    }

    public void setPersonnageCourant(Personnage p) {
        this.personnageTourActuel = p;
    }

    /**
     * Permet de modifier la couleur du champ contenant le message de joueurs.
     *
     * @param c
     */
    public void setLabelMessageJoueurCouleur(Color c) {
        labelJoueur.setForeground(c);
    }

    /**
     * Permet de modifier le texte du champ contenant le message de joueurs.
     *
     * @param texte
     */
    public void setLabelMessageJoueurTexte(String texte) {
        labelJoueur.setText(texte);
    }

    /*====================================
    Methodes du programme principal:
    =======================================*/
    /**
     * Sous programme qui va verifier si un coup est valide.
     *
     * @param ligne
     * @param col
     * @return boolean validite du coup.
     */
    private boolean verificationErreur(int ligne, int col) {
        /* Gestion des erreurs
        ------------------------
        - Pas besoin de verifier les coordonnees.
        - Pas besoin de verifier le format de saisie.
         */

        Boolean erreur = false;
        erreurText.setText(""); //vider le champs
        String erreurMessage = "";

        //Verificiation que la case n'est pas deja occupée
        if (getCase(ligne - 1, col - 1) != '.') {
            erreur = true;
            erreurMessage = "La case est déja pleine.";
        }
        //Si il y eu une erreur on intérromp le déroulement du coup.
        if (erreur) {
            erreurText.setText(erreurMessage);
        }
        return erreur;
    }

    /**
     * Gestion d'un coup jouer.
     *
     * @param ligne
     * @param col
     * @return
     */
    private boolean jouerUnCoup(int ligne, int col) {
        char charCouleur;
        TypePersonnages typeJoueur;
        boolean coupValide;

        if (numeroTour % 2 == 1) {
            personnageTourActuel = joueur1;
            personnageTourSuivant = joueur2;
        } else {
            personnageTourActuel = joueur2;
            personnageTourSuivant = joueur1;
        }

        charCouleur = personnageTourActuel.getCaractere();
        typeJoueur = personnageTourActuel.getType();

        //Change la couleur du message du joueur qui doit jouer
        labelJoueur.setText(personnageTourSuivant.getMessageTour());
        labelJoueur.setForeground(personnageTourSuivant.getCouleur());

       if (typeJoueur.equals(JOUEUR)) {
           
            /* Verfication optionnelle car les cases accessible à l'utilisateur
               sont uniquement les jouables.
            
           if (verificationErreur(ligne, col)) {
                return false;
            }
            */
            System.out.println("-- Jouer un coup par l'interface.");
            coupValide = jouer(ligne, col, charCouleur);
            sousTexte.setVisible(true);
        } else {
            sousTexte.setForeground(personnageTourActuel.getCouleur());
            sousTexte.setVisible(false);
            System.out.println("-- A l'ordinateur de jouer:");
            System.out.println("-- type joueur:" + personnageTourActuel.getMessageTour());
            coupIA(charCouleur, ((IA) personnageTourActuel).getNiveauDifficulte());
            coupValide = true;
        }

        //Si le coup est valide on change de joueur
        if (coupValide) {
            //si la partie est terminer on intéromp
            if (estTerminePartie()) {
                termine = true;
                System.out.println("-- Fin de partie");
                erreurText.setText("Partie termine.");
                erreurText.setForeground(Color.green);
                labelJoueur.setText("");

                //verrouillage des boutons
                menuPartie.verrouillerBoutonRetour();
                menuPartie.verrouillerBoutonSauvegarder();

                new DialogGagnant(personnageTourActuel).setVisible(true);
            } else {
                numeroTour += 1;
                //gestion du bouton retour
                if (numeroTour > 2) {
                    menuPartie.deverrouillerBoutonRetour();
                } else {
                    menuPartie.verrouillerBoutonRetour();
                }
            }
        } else {
            //Si le coup n'est pas valide on continu de jouer
            erreurText.setText("Une erreur est survenue");
        }
        //Logs: affichage de la grille dans le terminal
        InfosPlateau.afficherGrillePlateau();
        return true;
    }

    /**
     * Regenere l'affichage de la grille.
     */
    private void rafraichirAffichageGrille() {
        for (int indiceLigne = 0; indiceLigne < this.taille; indiceLigne++) {
            for (int indiceCol = 0; indiceCol < this.taille; indiceCol++) {
                char valeurCase;
                valeurCase = getCase(indiceLigne, indiceCol);
                Hexagone h = this.plat.getHexagone(indiceLigne * this.taille + indiceCol);
                if (valeurCase == joueur1.getCaractere()) {
                    h.setCouleurInterne(joueur1.getCouleur());
                } else if (valeurCase == joueur2.getCaractere()) {
                    h.setCouleurInterne(joueur2.getCouleur());
                }
            }
        }
        this.repaint();
        this.revalidate(); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!termine) {
            Hexagone h;
            for (int i = 0; i < (this.taille * this.taille); i++) {
                h = plat.getHexagone(i);
                if ((h.contains(e.getX() + SOURIS_DECALAGE_X_PIXELS, e.getY() + SOURIS_DECALAGE_Y_PIXELS))
                        && (h.getCouleurInterne() == h.getCouleurDefaut())) {
                    int ligne = (i / this.taille);
                    int col = (i % this.taille);
                    System.out.println("--Jouer()  sur hexagonne numero " + i + " <=> ligne = " + ligne + " col =" + col);
                    if (!jouerUnCoup(ligne + 1, col + 1)) {
                        System.out.println("-- Coup invalide");
                    }
                    rafraichirAffichageGrille();
                    break;
                }
            }
            //TEMP rafraichir pour le bug d'affichage blanc.
            rafraichirAffichageGrille();
        }
    }

    //Gestion des clicks
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Fix probleme d'affichage blanc
        this.repaint();
        this.revalidate();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        Hexagone h;
        boolean surHexa = false;
        for (int i = 0; i < (this.taille * this.taille); i++) {
            h = plat.getHexagone(i);
            //le curseur n'est pas centré au bout du selectionneur
            if ((h.contains(e.getX() + SOURIS_DECALAGE_X_PIXELS, e.getY() + SOURIS_DECALAGE_Y_PIXELS))
                    && (h.getCouleurInterne() == h.getCouleurDefaut())) {
                surHexa = true;
                break;
            }
        }

        //maj du curseur
        if (surHexa) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }

    }
    /* Faire joueur l'ia de maniere automatique
       Problemes:  - Erreur de detection du joueur
                   - erreur de rafraichissement. 
    private Thread surveillanceIA(){
        Runnable tache = new Runnable(){

        public void run(){
            while(true){
                boolean premierJoueurIA = ((numeroTour == 1) && (personnageTourActuel.getType().equals("IA")));
                boolean tourNormal = (personnageTourSuivant.getType()).toString().equals("IA");
                System.out.println(" test "+premierJoueurIA+" "+tourNormal+" n ="+numeroTour);
                
                if((premierJoueurIA) || (tourNormal)){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        System.err.println("Impossible d'attendre lors de la surveillance de l'IA.");
                    }
                    System.out.println("-- L ia joue un coup.");
                    //delancher coup IA
                    jouerUnCoup(1, 1);    
                    getParent().repaint();
                    getParent().revalidate();
                }
                //attendre pour ne pas surcharger la memoire
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.err.println("Impossible d'attendre lors de la surveillance de l'IA.");
                }
            }
          }
       };

      Thread thread = new Thread(tache);
      return thread;
    
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillePanel = new javax.swing.JPanel();
        erreurText = new javax.swing.JLabel();
        labelJoueur = new javax.swing.JLabel();
        sousTexte = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout grillePanelLayout = new javax.swing.GroupLayout(grillePanel);
        grillePanel.setLayout(grillePanelLayout);
        grillePanelLayout.setHorizontalGroup(
            grillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        grillePanelLayout.setVerticalGroup(
            grillePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        erreurText.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N

        labelJoueur.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        labelJoueur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        sousTexte.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        sousTexte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sousTexte.setText("( cliquez sur un hexagonne valide pour le découvrir )");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grillePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(erreurText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelJoueur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sousTexte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grillePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(erreurText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelJoueur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sousTexte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel erreurText;
    private javax.swing.JPanel grillePanel;
    private javax.swing.JLabel labelJoueur;
    private javax.swing.JLabel sousTexte;
    // End of variables declaration//GEN-END:variables

}
