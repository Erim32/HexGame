package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Menu principal du jeu.
 *
 * @author Mesté Rémi
 */
public class MenuPrincipal extends javax.swing.JPanel {

    /**
     * Construction d'un nouveau JPanel guiMenuPrincipal().
     */
    public MenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jouerBouton = new javax.swing.JButton();
        chargerSauvegardeBouton = new javax.swing.JButton();
        quitterBouton = new javax.swing.JButton();

        jouerBouton.setText("JOUER");
        jouerBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jouerBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jouerBoutonActionPerformed(evt);
            }
        });

        chargerSauvegardeBouton.setText("CHARGER");
        chargerSauvegardeBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chargerSauvegardeBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chargerSauvegardeBoutonActionPerformed(evt);
            }
        });

        quitterBouton.setText("QUITTER");
        quitterBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitterBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterBoutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quitterBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chargerSauvegardeBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jouerBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jouerBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chargerSauvegardeBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quitterBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Interaction lorsque l'on clique sur le bouton jouer.
     *
     * @param evt
     */
    private void jouerBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jouerBoutonActionPerformed

        JPanel partiePanel = FenetrePrincipale.getGuiInterfacePartie();

        partiePanel.removeAll();
        partiePanel.setLayout(new BorderLayout());
        partiePanel.add(new ConfigPartie(), BorderLayout.CENTER);

        partiePanel.repaint();
        partiePanel.revalidate();
    }//GEN-LAST:event_jouerBoutonActionPerformed

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
        System.out.println("-- Fermeture du jeu.");
        System.exit(0);
    }//GEN-LAST:event_quitterBoutonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chargerSauvegardeBouton;
    private javax.swing.JButton jouerBouton;
    private javax.swing.JButton quitterBouton;
    // End of variables declaration//GEN-END:variables
}
