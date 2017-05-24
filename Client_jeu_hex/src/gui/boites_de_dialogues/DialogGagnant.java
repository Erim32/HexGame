package gui.boites_de_dialogues;

import gui.FenetrePrincipale;
import gui.MenuPrincipal;
import gui.Regles;
import joueurs.Personnage;
import static joueurs.Personnage.TypePersonnages.IA;
import static interfaces.InterfaceJavaC.supprimerPlateau;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * Fenetre message Gagnant
 *
 * @author remi
 */
public class DialogGagnant extends javax.swing.JDialog {

    //attributs
    private int xSouris;
    private int ySouris;

    /**
     * Creation nouvelle fenetre pour le gagnant
     *
     * @param gagnant
     */
    public DialogGagnant(Personnage gagnant) {
        initComponents();

        if (gagnant.getType().equals(IA)) {
            titre.setText("Défaite:");
            titre.setForeground(Color.RED);
            message.setForeground(Color.RED);
        } else {
            titre.setText("Victoire:");
        }

        message.setText(gagnant.getMessageGagnant());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boutonFenetreFermer = new javax.swing.JLabel();
        DeplacementFenetreRegion = new javax.swing.JLabel();
        credit = new javax.swing.JLabel();
        titre_logiciel = new javax.swing.JLabel();
        haut_fenetre_fond_repeat = new javax.swing.JLabel();
        guiFond_bas_fenetre = new javax.swing.JLabel();
        titre = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        boutonRetourMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boutonFenetreFermer.setFont(new java.awt.Font("Napalm Strike", 1, 12)); // NOI18N
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
        getContentPane().add(boutonFenetreFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, 20));

        DeplacementFenetreRegion.setMaximumSize(new java.awt.Dimension(400, 40));
        DeplacementFenetreRegion.setMinimumSize(new java.awt.Dimension(400, 40));
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
        getContentPane().add(DeplacementFenetreRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 30));

        credit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        credit.setForeground(new java.awt.Color(240, 240, 240));
        credit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        credit.setText(" © Projet S4");
        credit.setMaximumSize(new java.awt.Dimension(1000, 17));
        credit.setMinimumSize(new java.awt.Dimension(1000, 17));
        credit.setPreferredSize(new java.awt.Dimension(1000, 17));
        getContentPane().add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 400, 20));

        titre_logiciel.setFont(new java.awt.Font("Enter The Grid", 1, 34)); // NOI18N
        titre_logiciel.setForeground(new java.awt.Color(255, 255, 255));
        titre_logiciel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre_logiciel.setText("   hex game");
        titre_logiciel.setMaximumSize(new java.awt.Dimension(1000, 40));
        titre_logiciel.setMinimumSize(new java.awt.Dimension(1000, 40));
        titre_logiciel.setPreferredSize(new java.awt.Dimension(400, 40));
        getContentPane().add(titre_logiciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 30));

        haut_fenetre_fond_repeat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        haut_fenetre_fond_repeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/haut_de_fenetre_fond.png"))); // NOI18N
        haut_fenetre_fond_repeat.setMinimumSize(new java.awt.Dimension(1000, 32));
        haut_fenetre_fond_repeat.setPreferredSize(new java.awt.Dimension(1000, 32));
        getContentPane().add(haut_fenetre_fond_repeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 30));

        guiFond_bas_fenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guiFond_bas_fenetre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/bas_de_fenetre_fond.png"))); // NOI18N
        guiFond_bas_fenetre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(guiFond_bas_fenetre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 400, -1));

        titre.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        titre.setForeground(java.awt.Color.green);
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 400, 20));

        message.setForeground(java.awt.Color.green);
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 400, 20));

        boutonRetourMenu.setText("Revenir au menu");
        boutonRetourMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonRetourMenuActionPerformed(evt);
            }
        });
        getContentPane().add(boutonRetourMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boutonFenetreFermerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boutonFenetreFermerMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_boutonFenetreFermerMouseClicked

    /**
     * Fonction qui met a jour la position de la fenetre si on cherche a la
     * déplacer.
     *
     * @param evt
     */
    private void DeplacementFenetreRegionMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeplacementFenetreRegionMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
       this.setLocation(x - xSouris, y - ySouris);    }//GEN-LAST:event_DeplacementFenetreRegionMouseDragged

    /**
     * Fonction qui recupere la postion de la souris pour ne pas decaler la
     * fenetre lors du deplacement de la fenetre.
     *
     * @param evt
     */
    private void DeplacementFenetreRegionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeplacementFenetreRegionMousePressed
        xSouris = evt.getX();
        ySouris = evt.getY();
    }//GEN-LAST:event_DeplacementFenetreRegionMousePressed

    private void boutonRetourMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonRetourMenuActionPerformed
        retourMenuPrincipal();
    }//GEN-LAST:event_boutonRetourMenuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DeplacementFenetreRegion;
    private javax.swing.JLabel boutonFenetreFermer;
    private javax.swing.JButton boutonRetourMenu;
    private javax.swing.JLabel credit;
    private javax.swing.JLabel guiFond_bas_fenetre;
    private javax.swing.JLabel haut_fenetre_fond_repeat;
    private javax.swing.JLabel message;
    private javax.swing.JLabel titre;
    private javax.swing.JLabel titre_logiciel;
    // End of variables declaration//GEN-END:variables

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

}
