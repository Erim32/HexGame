package gui;

import gui.grille.Plateau;
import java.awt.event.KeyEvent;
import static interfaces.InterfaceJavaC.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 * Premiere interfacee de la partie.
 * 
 * @author Rémi Mesté
 */
public final class InterfacePartieV0 extends javax.swing.JPanel {

    private final int taille;
    private final String typeJoueur1;
    private final String typeJoueur2;
    private static int NumeroTour = 1;
    private static int difficulteIa;
    private Plateau plat;

    /**
     * Creates new form guiInterfacePartie
     *
     * @param taille
     * @param typeJ1
     * @param typeJ2
     * @param difficulteIA
     */
    //Constructeur sans IA
    public InterfacePartieV0(int taille, String typeJ1, String typeJ2, int difficulteIA) {
        initComponents();

        this.taille = taille;
        this.typeJoueur1 = typeJ1;
        this.typeJoueur2 = typeJ2;
        this.difficulteIa = difficulteIA;

        // this.plat   = new Plateau(this.taille);
        grillePanel = new JScrollPane();
        grillePanel.add(plat);
        grillePanel.setAutoscrolls(true);
        grillePanel.setPreferredSize(new Dimension(800, 300));
        grillePanel.repaint();
        debutPartie(this.taille);
        rafraichirAffichageGrille();
    }

    boolean verificationErreur(int ligne, int col) {
        //gestion des erreurs
        erreurText.setText("");
        Boolean erreur = false;
        String erreurMessage = "";
        String saisieJoueur[] = saisie.getText().split(" ");
        /* *******************************
         *Verification des erreurs
         *********************************/

        //verification  de la saisie
        if (saisieJoueur.length != 2) {
            erreur = true;
            erreurMessage = "Format de la saisie invalide.";
        }
        //Verification des coordonées
        if ((col - 1 < 0) || (col - 1 >= this.taille)
                || (ligne - 1 < 0) || (ligne - 1 >= this.taille)) {
            erreur = true;
            erreurMessage = "Les coordonnes ne sont pas correctes.";
        }
        //Verificiation que la case n'est pas deja occupée
        if (getCase(ligne - 1, col - 1) != '.') {
            erreur = true;
            erreurMessage = "La case est déja pleine.";
        }

        //Si il y eu une erreur on intéromp le déroulement du coup
        if (erreur) {
            erreurText.setText(erreurMessage);
            erreur = true;
        }

        return erreur;
    }

    void jouerUnCoup() {

        char couleur;
        String typeJoueur;
        if (NumeroTour % 2 == 1) {
            //joueur 1
            couleur = 'o';
            typeJoueur = this.typeJoueur1;
        } else {
            //joueur 2 
            couleur = 'x';
            typeJoueur = this.typeJoueur2;
        }

        boolean coupValide = false;
        if (typeJoueur.equals("JOUEUR")) {

            //recuperation des valeurs
            String saisieJoueur[] = saisie.getText().split(" ");
            int ligne = Integer.parseInt(saisieJoueur[1]);
            int col = Integer.parseInt(saisieJoueur[0]);;
            if (verificationErreur(ligne, col)) {
                return;
            }
            System.out.println("Au joueur de jouer:");
            coupValide = jouer(ligne, col, couleur);
        } else {
            System.out.println("A l'ordinateur de jouer:");
            coupIA(couleur, this.difficulteIa);
            coupValide = true;
        }

        //Si le coup est valide on change de joueur
        if (coupValide) {
            //si la partie est terminer on intéromp
            if (estTerminePartie()) {
                System.out.print("Fin de partie");
                valider.setVisible(false);
            }
            NumeroTour += 1;
        } else {
            //Si le coup n'est pas valide oncontinu de jouer
            erreurText.setText("une erreur est survenue");
        }

    }

    void rafraichirAffichageGrille() {
        plat.repaint();
        // version 1
        String grille = "";
        int NbrDecalage = 0;
        for (int y = -1; y <= this.taille; y++) {
            if (y == -1 || y == this.taille) {
                for (int cptEspace = 0; cptEspace < NbrDecalage; cptEspace++) {
                    grille += " ";
                }
                for (int col = 0; col < this.taille + 2; col++) {
                    grille += "B";
                }
            } else {
                for (int x = -1; x <= this.taille; x++) {
                    if (x >= 0 && x < this.taille) {
                        grille += " " + getCase(x, y);
                    } else {
                        if (x == -1) {
                            for (int cptEspace = 0; cptEspace < NbrDecalage; cptEspace++) {
                                grille += " ";
                            }
                        }
                        grille += "B";
                    }

                }
            }
            NbrDecalage += 1;
            grille += "\n";
        }
        console.setText(grille);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        saisie = new javax.swing.JTextField();
        valider = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        erreurText = new javax.swing.JLabel();
        grillePanel = new javax.swing.JScrollPane();

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        saisie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saisieKeyPressed(evt);
            }
        });

        valider.setText("valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        jLabel2.setText("Saisie: ");

        erreurText.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grillePanel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(erreurText))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saisie, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valider)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grillePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(erreurText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(saisie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saisieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saisieKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            valider();
        }
    }//GEN-LAST:event_saisieKeyPressed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        valider();
    }//GEN-LAST:event_validerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JLabel erreurText;
    private javax.swing.JScrollPane grillePanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField saisie;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables

    private void valider() {
        jouerUnCoup();
        rafraichirAffichageGrille();
        if (estTerminePartie()) {
            erreurText.setText("Termine");
            erreurText.setForeground(Color.green);

        }
    }
}
