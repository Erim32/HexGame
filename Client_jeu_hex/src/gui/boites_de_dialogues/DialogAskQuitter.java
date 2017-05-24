package gui.boites_de_dialogues;

import gui.FenetrePrincipale;
import gui.MenuPrincipal;
import gui.Regles;
import static gui.FenetrePrincipale.NOM_LOGICIEL;
import static interfaces.InterfaceJavaC.supprimerPlateau;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Fenetre de demande confirmation de fermeture du jeu.
 *
 * @author Rémi
 */
public class DialogAskQuitter extends javax.swing.JDialog {

    /**
     * Creation du la boite de dialogue
     *
     * @param parent
     * @param modal
     */
    public DialogAskQuitter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel_titre = new javax.swing.JLabel();

        setTitle(NOM_LOGICIEL);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        confirmButton.setText("Quitter");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Annuler");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel_titre.setText("Voulez-vous vraiment revenir au menu principal ?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel_titre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel_titre)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelButton, confirmButton});

        getRootPane().setDefaultButton(confirmButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Interaction lorsque l'on clique sur le bouton confirmer.
     *
     * @param evt
     */
    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        retourMenuPrincipal();
    }//GEN-LAST:event_confirmButtonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton annuler.
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        fermetureAnnule();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton pour fermer la boite de
     * dialogue.
     *
     * @param evt
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        fermetureAnnule();
    }//GEN-LAST:event_closeDialog

    /**
     * Ferme la boite de dialogue.
     */
    private void fermetureAnnule() {
        setVisible(false);
        dispose();
    }

    private void retourMenuPrincipal() {
        //suppression de la partie
        System.out.println("-- Suppression de la partie");
        supprimerPlateau();
        //retour au menu principal
        JPanel menuPanel = FenetrePrincipale.getMenu_principal();
        JPanel interfacePartie = FenetrePrincipale.getGuiInterfacePartie();

        menuPanel.removeAll();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(new MenuPrincipal(), BorderLayout.CENTER);

        menuPanel.repaint();
        menuPanel.revalidate();

        interfacePartie.removeAll();
        interfacePartie.setLayout(new BorderLayout());
        interfacePartie.add(new Regles(), BorderLayout.CENTER);
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel_titre;
    // End of variables declaration//GEN-END:variables

}
