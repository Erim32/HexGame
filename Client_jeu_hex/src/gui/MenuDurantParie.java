package gui;

import gui.boites_de_dialogues.DialogAskQuitter;
import gui.grille.Plateau;
import joueurs.Personnage;
import static interfaces.InterfaceJavaC.*;

/**
 * Menu du jeu durant la partie.
 *
 * @author Rémi Mesté
 */
public class MenuDurantParie extends javax.swing.JPanel {

    //attributs
    private static int taille;
    private final Plateau plat;
    private final InterfacePartie partie;

    /**
     * Construction d'un nouveau JPanel guiMenuDurantParie(). Qui est lié d'une
     * interface partie
     */
    MenuDurantParie(InterfacePartie partie) {
        initComponents();
        this.taille = partie.getTaillePlateau();
        this.plat = partie.getPlateau();
        this.partie = partie;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sauvegarderBouton = new javax.swing.JButton();
        chargerSauvegardeBouton = new javax.swing.JButton();
        quitterBouton = new javax.swing.JButton();
        resetBouton = new javax.swing.JButton();
        retourBouton1 = new javax.swing.JButton();

        sauvegarderBouton.setText("Sauvegarder");
        sauvegarderBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sauvegarderBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauvegarderBoutonActionPerformed(evt);
            }
        });

        chargerSauvegardeBouton.setText("Charger");
        chargerSauvegardeBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chargerSauvegardeBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chargerSauvegardeBoutonActionPerformed(evt);
            }
        });

        quitterBouton.setText("Quitter");
        quitterBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitterBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterBoutonActionPerformed(evt);
            }
        });

        resetBouton.setText("Recommencer");
        resetBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBoutonActionPerformed(evt);
            }
        });

        retourBouton1.setText("Retour en arriere");
        retourBouton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        retourBouton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourBouton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chargerSauvegardeBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sauvegarderBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quitterBouton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBouton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retourBouton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chargerSauvegardeBouton, quitterBouton, resetBouton, sauvegarderBouton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(resetBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sauvegarderBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chargerSauvegardeBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(retourBouton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(quitterBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chargerSauvegardeBouton, quitterBouton, resetBouton, sauvegarderBouton});

    }// </editor-fold>//GEN-END:initComponents

    /**
     * Interaction lorsque l'on clique sur le bouton sauvegarder.
     *
     * @param evt
     */
    private void sauvegarderBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauvegarderBoutonActionPerformed
        services.GestionSauvegardes.sauvegarderPartie();
    }//GEN-LAST:event_sauvegarderBoutonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton charger.
     *
     * @param evt
     */
    private void chargerSauvegardeBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chargerSauvegardeBoutonActionPerformed
        services.GestionSauvegardes.chargerPartie();
    }//GEN-LAST:event_chargerSauvegardeBoutonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton quitter.
     *
     * @param evt
     */
    private void quitterBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitterBoutonActionPerformed
        new DialogAskQuitter(null, true).setVisible(true);
    }//GEN-LAST:event_quitterBoutonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton recommencer.
     *
     * @param evt
     */
    private void resetBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBoutonActionPerformed

        System.out.println("-- Partie Reinitialiser.");
        System.out.println("---- Reinitialisation de l'affichage.");
        //reset de l'affichage 
        for (int i = 0; i < (this.taille) * (this.taille); i++) {
            this.plat.getHexagone(i).setCouleurInterne(this.plat.getHexagone(i).getCouleurDefaut());
        }

        plat.repaint();

        partie.setLabelMessageJoueurCouleur(partie.getCouleurJoueur(1));
        partie.setLabelMessageJoueurTexte(partie.getMessageTourJoueur(0));

        deverrouillerBoutonSauvegarder();

        System.out.println("---- Réinitialisation des données de la patie.");

        //reset des variables
        partie.setNumeroCoup(1);

        debutPartie(this.taille);
    }//GEN-LAST:event_resetBoutonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton retour en arriere.
     *
     * @param evt
     */
    private void retourBouton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourBouton1ActionPerformed
        if (retourArriere()) {
            //reset de l'affichage 
            for (int i = 0; i < (this.taille) * (this.taille); i++) {
                this.plat.getHexagone(i).setCouleurInterne(this.plat.getHexagone(i).getCouleurDefaut());
            }

            plat.repaint();
            partie.repaint();
            if (partie.getNumeroTour() > 1) {
                //MAJ des variables
                partie.setNumeroCoup(partie.getNumeroTour() - 1);

                int numeroNouveauTour = partie.getNumeroTour();

                System.out.println("-- Retour au coup numero " + numeroNouveauTour + ".");
                Personnage personnageJoueurPrecendent = partie.getPersonnageTour((numeroNouveauTour % 2) + 1);
                partie.setPersonnageCourant(personnageJoueurPrecendent);
                partie.setLabelMessageJoueurTexte(partie.getMessageTourJoueur((numeroNouveauTour % 2) + 1));
                partie.setLabelMessageJoueurCouleur(personnageJoueurPrecendent.getCouleur());
            }
        } else {
            System.err.println("-- Un problème a été rencontrer");
        }

    }//GEN-LAST:event_retourBouton1ActionPerformed

    /**
     * Permet de desactiver le bouton retour du menu.
     */
    public void verrouillerBoutonRetour() {
        retourBouton1.setEnabled(false);
    }

    /**
     * Permet d'activer le bouton retour du menu.
     */
    public void deverrouillerBoutonRetour() {
        retourBouton1.setEnabled(true);
    }

    /**
     * Permet de desactiver le bouton sauvegarder du menu.
     */
    public void verrouillerBoutonSauvegarder() {
        sauvegarderBouton.setEnabled(false);
    }

    /**
     * Permet d'activer le bouton sauvegarder du menu.
     */
    public void deverrouillerBoutonSauvegarder() {
        sauvegarderBouton.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chargerSauvegardeBouton;
    private javax.swing.JButton quitterBouton;
    private javax.swing.JButton resetBouton;
    private javax.swing.JButton retourBouton1;
    private javax.swing.JButton sauvegarderBouton;
    // End of variables declaration//GEN-END:variables

}
