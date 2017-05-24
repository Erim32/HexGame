package gui;

import gui.boites_de_dialogues.DialogAPropos;
import services.EchapClavierRetourMenu;
import gui.boites_de_dialogues.DialogErreurInterne;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import javax.swing.JPanel;
import services.Musique;

/**
 * Interface graphique fixe et constante qui ne change jamais.
 *
 * @author Rémi Mesté
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    public static final String NOM_LOGICIEL = "HEX GAME";

    //attributs
    private int xSouris;
    private int ySouris;

    //musique
    private static boolean musiqueOn = false;
    private Musique son = new Musique();
    private final EchapClavierRetourMenu echap;

    /**
     * Creation de l'interface graphique de base et charge une police d'écriture
     * custom.
     */
    public FenetrePrincipale() {
        //Initialisation de l'interface
        initComponents();

        //mofication de la police du titre
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("ressources/titreFont.ttf")).deriveFont(Font.PLAIN, 14);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            //Initalisation de l'interface
            menu_principal.setLayout(new BorderLayout());
            menu_principal.add(new MenuPrincipal(), BorderLayout.CENTER);
            guiInterfacePartie.setLayout(new BorderLayout());
            guiInterfacePartie.add(new Regles(), BorderLayout.CENTER);

        } catch (FontFormatException | IOException e) {
            new DialogErreurInterne().setVisible(true);
            System.err.println("Erreur interne: " + e);
        }

        //Detection echap
        this.echap = new EchapClavierRetourMenu();
    }

    /**
     * Accesseurs
     */
    /**
     * Permet de récuperer le JPanel de la partie InterfacePartie.
     *
     * @return JPanel
     */
    public static JPanel getGuiInterfacePartie() {
        return guiInterfacePartie;
    }

    /**
     * Permet de récuperer le JPanel de la partie Menu_principal.
     *
     * @return JPanel
     */
    public static JPanel getMenu_principal() {
        return menu_principal;
    }

    /**
     * Autres méthodes
     */
    //Construction de l'interface
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boutonFenetreReduction = new javax.swing.JLabel();
        boutonFenetreFermer = new javax.swing.JLabel();
        DeplacementFenetreRegion = new javax.swing.JLabel();
        menu_principal = new javax.swing.JPanel();
        guiInterfacePartie = new javax.swing.JPanel();
        credit = new javax.swing.JLabel();
        titre_logiciel = new javax.swing.JLabel();
        guiFond_bas_fenetre = new javax.swing.JLabel();
        haut_fenetre_fond_repeat = new javax.swing.JLabel();
        musiqueBouton = new javax.swing.JButton();
        aProposBouton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(NOM_LOGICIEL);
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(boutonFenetreReduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 20, 20));

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
        getContentPane().add(boutonFenetreFermer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, -1, 20));

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
        getContentPane().add(DeplacementFenetreRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 20));

        javax.swing.GroupLayout menu_principalLayout = new javax.swing.GroupLayout(menu_principal);
        menu_principal.setLayout(menu_principalLayout);
        menu_principalLayout.setHorizontalGroup(
            menu_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        menu_principalLayout.setVerticalGroup(
            menu_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        getContentPane().add(menu_principal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 520));

        javax.swing.GroupLayout guiInterfacePartieLayout = new javax.swing.GroupLayout(guiInterfacePartie);
        guiInterfacePartie.setLayout(guiInterfacePartieLayout);
        guiInterfacePartieLayout.setHorizontalGroup(
            guiInterfacePartieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        guiInterfacePartieLayout.setVerticalGroup(
            guiInterfacePartieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        getContentPane().add(guiInterfacePartie, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 910, 620));

        credit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        credit.setForeground(new java.awt.Color(240, 240, 240));
        credit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        credit.setText(" © Projet S4: groupe numero 39.");
        credit.setMaximumSize(new java.awt.Dimension(1000, 17));
        credit.setMinimumSize(new java.awt.Dimension(1000, 17));
        credit.setPreferredSize(new java.awt.Dimension(1000, 17));
        getContentPane().add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 1160, 20));
        credit.getAccessibleContext().setAccessibleName(" © Groupe n°39. ");

        titre_logiciel.setFont(new java.awt.Font("Enter The Grid", 1, 34)); // NOI18N
        titre_logiciel.setForeground(new java.awt.Color(255, 255, 255));
        titre_logiciel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre_logiciel.setText("hex game");
        titre_logiciel.setMaximumSize(new java.awt.Dimension(1000, 40));
        titre_logiciel.setMinimumSize(new java.awt.Dimension(1000, 40));
        titre_logiciel.setPreferredSize(new java.awt.Dimension(1000, 40));
        getContentPane().add(titre_logiciel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 30));

        guiFond_bas_fenetre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guiFond_bas_fenetre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/bas_de_fenetre_fond.png"))); // NOI18N
        guiFond_bas_fenetre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(guiFond_bas_fenetre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 1200, -1));

        haut_fenetre_fond_repeat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        haut_fenetre_fond_repeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/haut_de_fenetre_fond.png"))); // NOI18N
        haut_fenetre_fond_repeat.setMinimumSize(new java.awt.Dimension(1000, 32));
        haut_fenetre_fond_repeat.setPreferredSize(new java.awt.Dimension(1000, 32));
        getContentPane().add(haut_fenetre_fond_repeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 30));

        musiqueBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/musique_off.png"))); // NOI18N
        musiqueBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        musiqueBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musiqueBoutonActionPerformed(evt);
            }
        });
        getContentPane().add(musiqueBouton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 90, 50));

        aProposBouton.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        aProposBouton.setText("A propos");
        aProposBouton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aProposBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aProposBoutonActionPerformed(evt);
            }
        });
        getContentPane().add(aProposBouton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 130, 50));

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
        this.setLocation(x - xSouris, y - ySouris);
    }//GEN-LAST:event_DeplacementFenetreRegionMouseDragged

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
     * Fermer la fenetre et le programme lorsque l'on clique sur la croix de la
     * fenetre.
     *
     * @param evt
     */
    private void boutonFenetreFermerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boutonFenetreFermerMouseClicked
        System.exit(0);
    }//GEN-LAST:event_boutonFenetreFermerMouseClicked

    /**
     * Reduit la fenetre et lorsque l'on clique sur le - de la fenetre.
     *
     * @param evt
     */
    private void boutonFenetreReductionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boutonFenetreReductionMouseClicked
        this.setState(FenetrePrincipale.ICONIFIED);
    }//GEN-LAST:event_boutonFenetreReductionMouseClicked

    /**
     * Bouton de gestion de la musique
     *
     * @param evt
     */
    private void musiqueBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musiqueBoutonActionPerformed
        if (musiqueOn) {
            System.out.println("-- Interuption de la musique.");
            son.arreter();
            musiqueBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/musique_off.png")));
            musiqueOn = false;
        } else {
            System.out.println("-- Lancement de la musique.");
            son.demarrer();
            musiqueBouton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/musique_on.png")));
            musiqueOn = true;
        }
    }//GEN-LAST:event_musiqueBoutonActionPerformed

    /**
     * Interaction lorsque l'on clique sur le bouton "a propos".
     *
     * @param evt
     */
    private void aProposBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aProposBoutonActionPerformed
        new DialogAPropos(null, false).setVisible(true);
    }//GEN-LAST:event_aProposBoutonActionPerformed

    //Attributs spécifiques à l'interface.
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DeplacementFenetreRegion;
    private javax.swing.JButton aProposBouton;
    private javax.swing.JLabel boutonFenetreFermer;
    private javax.swing.JLabel boutonFenetreReduction;
    private javax.swing.JLabel credit;
    private javax.swing.JLabel guiFond_bas_fenetre;
    private static javax.swing.JPanel guiInterfacePartie;
    private javax.swing.JLabel haut_fenetre_fond_repeat;
    private static javax.swing.JPanel menu_principal;
    private javax.swing.JButton musiqueBouton;
    private javax.swing.JLabel titre_logiciel;
    // End of variables declaration//GEN-END:variables
}
