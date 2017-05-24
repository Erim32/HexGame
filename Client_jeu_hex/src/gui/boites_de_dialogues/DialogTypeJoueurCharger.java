package gui.boites_de_dialogues;

import gui.FenetrePrincipale;
import gui.InterfacePartie;
import joueurs.IA;
import joueurs.Joueur;
import joueurs.Personnage;
import interfaces.InterfaceJavaC;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 * Fenetre de demande si le type de joueur2.
 *
 * @author Mesté Rémi
 */
public class DialogTypeJoueurCharger extends javax.swing.JDialog {

    private int xSouris;
    private int ySouris;

    /**
     * Creation nouvelle fenetre
     */
    public DialogTypeJoueurCharger() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DeplacementFenetreRegion = new javax.swing.JLabel();
        credit = new javax.swing.JLabel();
        titre_logiciel = new javax.swing.JLabel();
        haut_fenetre_fond_repeat = new javax.swing.JLabel();
        guiFond_bas_fenetre = new javax.swing.JLabel();
        titre = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        boutonHumain = new javax.swing.JButton();
        boutonIA = new javax.swing.JButton();
        ou = new javax.swing.JLabel();
        typeJ2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        titre.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        titre.setForeground(java.awt.Color.black);
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre.setText("Charger:");
        getContentPane().add(titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 400, 20));

        message.setForeground(java.awt.Color.black);
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        message.setText("Quel est le type du joueur 2:");
        getContentPane().add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 400, 20));

        boutonHumain.setText("HUMAIN");
        boutonHumain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonHumainActionPerformed(evt);
            }
        });
        getContentPane().add(boutonHumain, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 100, 40));

        boutonIA.setText("ORDINATEUR");
        boutonIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonIAActionPerformed(evt);
            }
        });
        getContentPane().add(boutonIA, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, 40));

        ou.setText("ou");
        getContentPane().add(ou, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        typeJ2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facile", "Moyen", "Difficile" }));
        getContentPane().add(typeJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    /**
     * Interaction lorsque l'on clique sur le bouton Humain
     *
     * @param evt
     */
    private void boutonHumainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonHumainActionPerformed
        Joueur j2 = new Joueur("joueur 2", Color.RED, 'o');
        lancerPartieCharge(j2);
    }//GEN-LAST:event_boutonHumainActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton IA
     *
     * @param evt
     */
    private void boutonIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonIAActionPerformed
        int niveauDifficulteIa;
        String valeurDifficulteIa = typeJ2.getItemAt(typeJ2.getSelectedIndex());
        switch (valeurDifficulteIa) {
            case "Facile":
                niveauDifficulteIa = 0;
                break;
            case "Moyen":
                niveauDifficulteIa = 1;
                break;
            case "Difficile":
                niveauDifficulteIa = 2;
                break;
            default:
                niveauDifficulteIa = 0;
                break;
        }

        IA j2 = new IA(niveauDifficulteIa, Color.RED, 'o');
        lancerPartieCharge(j2);
    }//GEN-LAST:event_boutonIAActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DeplacementFenetreRegion;
    private javax.swing.JButton boutonHumain;
    private javax.swing.JButton boutonIA;
    private javax.swing.JLabel credit;
    private javax.swing.JLabel guiFond_bas_fenetre;
    private javax.swing.JLabel haut_fenetre_fond_repeat;
    private javax.swing.JLabel message;
    private javax.swing.JLabel ou;
    private javax.swing.JLabel titre;
    private javax.swing.JLabel titre_logiciel;
    private javax.swing.JComboBox<String> typeJ2;
    // End of variables declaration//GEN-END:variables

    private void lancerPartieCharge(Personnage j2) {
        Joueur j1 = new Joueur("joueur 1", Color.blue, 'x');
        //on ouvre la fenetre de la partie
        System.out.println("-- maj de la partie.");
        JPanel partiePanel = FenetrePrincipale.getGuiInterfacePartie();
        partiePanel.removeAll();
        partiePanel.setLayout(new BorderLayout());
        partiePanel.add(new InterfacePartie(InterfaceJavaC.getTaille(), j2), BorderLayout.CENTER);

        partiePanel.repaint();
        partiePanel.revalidate();
        this.setVisible(false);
    }

}
