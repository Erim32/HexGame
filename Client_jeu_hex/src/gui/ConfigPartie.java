package gui;

import joueurs.IA;
import joueurs.Joueur;
import joueurs.Personnage;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Interface de configuration de la partie.
 *
 * @author Rémi Mesté
 */
public class ConfigPartie extends javax.swing.JPanel {

    private final static int TAILLE_MIN = 5;
    private final static int TAILLE_MAX = 15;
    private final static String TYPE_JOUEUR = "JOUEUR";
    private final static String TYPE_IA = "IA";
    private final static String MESSAGE_ERREUR_TAILLE
            = "La taille doit etre:\n- suppérieure à " + (TAILLE_MIN - 1) + "\n"
            + "- Inférieure à " + (TAILLE_MAX + 1) + ".";


    private String typeJ1String, typeJ2String;
    private static boolean modeHardcore = false;
    /**
     * Création du JPanel de configuration de partie.
     */
    public ConfigPartie() {
        initComponents();

        this.typeJ1String = TYPE_JOUEUR;
        this.typeJ2String = TYPE_JOUEUR;

        labeIaText.setVisible(false);
        selectIaDifficulte.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titre = new javax.swing.JLabel();
        textTaille = new javax.swing.JLabel();
        saisieTaille = new javax.swing.JTextField();
        textTypeJoueurs = new javax.swing.JLabel();
        textJ1 = new javax.swing.JLabel();
        textJ2 = new javax.swing.JLabel();
        typeJ1 = new javax.swing.JComboBox<>();
        typeJ2 = new javax.swing.JComboBox<>();
        boutonDemarrer = new javax.swing.JButton();
        labeIaText = new javax.swing.JLabel();
        selectIaDifficulte = new javax.swing.JComboBox<>();
        textNomJ2 = new javax.swing.JLabel();
        textNomJ1 = new javax.swing.JLabel();
        valeurNom1 = new javax.swing.JTextField();
        valeurNom2 = new javax.swing.JTextField();
        aideTaille = new javax.swing.JLabel();

        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 600));
        setRequestFocusEnabled(false);

        titre.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre.setText("Configuration de la partie:");
        titre.setToolTipText("");
        titre.setAlignmentX(0.5F);

        textTaille.setText("Taille de la grille:");

        saisieTaille.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saisieTaille.setText("11");
        saisieTaille.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        textTypeJoueurs.setText("Types de joueurs:");

        textJ1.setText("- Joueur 1:");

        textJ2.setText("- Joueur 2:");

        typeJ1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JOUEUR", "IA" }));
        typeJ1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        typeJ1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeJ1ItemStateChanged(evt);
            }
        });
        typeJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeJ1ActionPerformed(evt);
            }
        });

        typeJ2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JOUEUR", "IA" }));
        typeJ2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        typeJ2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeJ2ItemStateChanged(evt);
            }
        });

        boutonDemarrer.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        boutonDemarrer.setText("Commencer");
        boutonDemarrer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boutonDemarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonDemarrerActionPerformed(evt);
            }
        });

        labeIaText.setText("Difficulté de l'IA:");

        selectIaDifficulte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facile", "Moyen", "Difficile", "Hardcore" }));
        selectIaDifficulte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectIaDifficulte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectIaDifficulteItemStateChanged(evt);
            }
        });
        selectIaDifficulte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectIaDifficulteActionPerformed(evt);
            }
        });

        textNomJ2.setText("| nom:");

        textNomJ1.setText("| nom:");

        valeurNom1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valeurNom1.setText("joueur 1");
        valeurNom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valeurNom1ActionPerformed(evt);
            }
        });

        valeurNom2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valeurNom2.setText("joueur 2");
        valeurNom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valeurNom2ActionPerformed(evt);
            }
        });

        aideTaille.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aideTaille.setText("(entre 5 et 15 )");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labeIaText)
                                .addGap(336, 336, 336))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textTaille)
                                        .addGap(60, 60, 60)
                                        .addComponent(saisieTaille))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textTypeJoueurs)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textJ2)
                                                        .addComponent(textJ1))
                                                    .addGap(92, 92, 92)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(typeJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(typeJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(selectIaDifficulte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textNomJ1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                            .addComponent(textNomJ2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(valeurNom1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(valeurNom2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(221, 221, 221))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(aideTaille, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(boutonDemarrer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTaille)
                    .addComponent(saisieTaille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aideTaille, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTypeJoueurs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textJ1)
                    .addComponent(typeJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNomJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valeurNom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textJ2)
                    .addComponent(typeJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNomJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valeurNom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labeIaText)
                    .addComponent(selectIaDifficulte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boutonDemarrer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Determine si un string est un entier
     * @param String champ
     * @return boolean si c'est un entier
     */
     private boolean estEntier(String s){
         if(s.isEmpty()) return false;
         for(int i = 0; i < s.length(); i++){
             if( i == 0 && s.charAt(i) == '-')
               return false;
             
             if(Character.digit(s.charAt(i), 10) < 0)
                 return false;
         }
         return true;
     }
    
    /**
     * Boite de dialogue lors des erreur de taille saisie.
     */ 
    private void messageTaille(){
             JOptionPane.showMessageDialog(getParent(), MESSAGE_ERREUR_TAILLE, "Erreur", JOptionPane.WARNING_MESSAGE);
    }
     
    /**
     * Interaction lorsque l'on clique pour lancer la partie.
     *
     * @param evt
     */
    private void boutonDemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonDemarrerActionPerformed
        //verification validite de la saisie.
        String contenuSaisie = saisieTaille.getText();
        if(! estEntier(contenuSaisie)){
            messageTaille();
            return;
        }
        //recuperation de la taille du plateau
        int taille = Integer.parseInt(contenuSaisie);
        if ((taille < TAILLE_MIN || taille > TAILLE_MAX)) {
            messageTaille();
        } else {
            assert ((taille > TAILLE_MIN) && (taille < TAILLE_MAX));
            int niveauDifficulteIa;
            //plus explicite que de switch l'index, pour d'éventuelles améliorations.
            String valeurDifficulteIa = selectIaDifficulte.getItemAt(selectIaDifficulte.getSelectedIndex());
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
                case "Hardcore":
                    niveauDifficulteIa = 2;
                    break;
                default:
                    niveauDifficulteIa = 0;
                    break;
            }

            //affectation des iformations
            char charJ1 = 'x';
            char charJ2 = 'o';
            Color couleurJ1 = Color.BLUE;
            Color couleurJ2 = Color.RED;

            Personnage j1, j2;

            //Joueur 1
            if (typeJ1String.equals(TYPE_JOUEUR)) {
                 String nomJ1 = valeurNom1.getText();
                if(nomJ1.isEmpty())
                   j1 = new Joueur("Joueur 1", couleurJ1, charJ1);
                else
                   j1 = new Joueur(nomJ1, couleurJ1, charJ1);
            } else {
                if(this.modeHardcore)
                   j1 = new IA("Skynet",niveauDifficulteIa, couleurJ1, charJ1);
                else
                    j1 = new IA(niveauDifficulteIa, couleurJ1, charJ1);
            }

           
            //Joueur 2
            if (typeJ2String.equals(TYPE_JOUEUR)) {
                String nomJ2 = valeurNom2.getText();
                if(nomJ2.isEmpty())
                    j2 = new Joueur("Joueur 2", couleurJ2, charJ2);
                else
                    j2 = new Joueur(nomJ2, couleurJ2, charJ2);
            } else {
                j2 = new IA(niveauDifficulteIa, couleurJ2, charJ2);
            }

           //on ouvre la fenetre de la partie
            JPanel partiePanel = FenetrePrincipale.getGuiInterfacePartie();
            partiePanel.removeAll();
            partiePanel.setLayout(new BorderLayout());

            //partiePanel.add(new guiInterfacePartieV0(taille, this.typeJ1String, this.typeJ2String, this.niveauDifficulteIa), BorderLayout.CENTER);
            partiePanel.add(new InterfacePartie(taille, j1, j2), BorderLayout.CENTER);

            partiePanel.repaint();
            partiePanel.revalidate();
        }
    }//GEN-LAST:event_boutonDemarrerActionPerformed

    private void typeJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeJ1ActionPerformed
    }//GEN-LAST:event_typeJ1ActionPerformed

    /**
     * Interaction qui gere l'affichage du sous menu difficulte ia
     *
     * @param evt
     */
    private void typeJ1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeJ1ItemStateChanged
        this.typeJ1String = typeJ1.getItemAt(typeJ1.getSelectedIndex());
        
        majSelectTypeJoueur();
        majHardcore();
    
            
    }//GEN-LAST:event_typeJ1ItemStateChanged

    private void typeJ2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeJ2ItemStateChanged
        this.typeJ2String = typeJ2.getItemAt(typeJ2.getSelectedIndex());
        majSelectTypeJoueur();
        majHardcore();
        
    }//GEN-LAST:event_typeJ2ItemStateChanged

    private void selectIaDifficulteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectIaDifficulteActionPerformed
    }//GEN-LAST:event_selectIaDifficulteActionPerformed

    private void valeurNom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valeurNom1ActionPerformed
    }//GEN-LAST:event_valeurNom1ActionPerformed

    private void valeurNom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valeurNom2ActionPerformed
    }//GEN-LAST:event_valeurNom2ActionPerformed

    private void selectIaDifficulteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectIaDifficulteItemStateChanged
        int index = selectIaDifficulte.getSelectedIndex();
        
        //hardcore
        if(index == 3){
            typeJ1.setSelectedItem(TYPE_IA);
            typeJ2.setSelectedItem(TYPE_JOUEUR);
        }
        majSelectTypeJoueur();
        majHardcore();

    }//GEN-LAST:event_selectIaDifficulteItemStateChanged

    /**
     * Gestion de l'affichage du niveau de difficulte
     */
    private void majSelectTypeJoueur() {
        //niveau de l'ia
        if (((this.typeJ1String).equals(TYPE_IA)) || ((this.typeJ2String).equals(TYPE_IA))) {
            labeIaText.setVisible(true);
            selectIaDifficulte.setVisible(true);
        } else {
            labeIaText.setVisible(false);
            selectIaDifficulte.setVisible(false);
        }
        
         //les label de joueur   
        if(this.typeJ1String.equals(TYPE_IA)){
            this.valeurNom1.setVisible(false);
            this.textNomJ1.setVisible(false);
        }else{        
            this.valeurNom1.setVisible(true);
            this.textNomJ1.setVisible(true);
        }
    
        if(this.typeJ2String.equals(TYPE_IA)){
            this.valeurNom2.setVisible(false);
            this.textNomJ2.setVisible(false);
        }else{        
            this.valeurNom2.setVisible(true);
            this.textNomJ2.setVisible(true);
        }
    }
    
    /**
     * Verification de l'état du mode HARDCORE
     */
    private void majHardcore(){
        
        if( (this.typeJ1String.equals(TYPE_IA)) && (this.typeJ2String.equals(TYPE_JOUEUR)) && ((selectIaDifficulte.getSelectedIndex() == 2) || (selectIaDifficulte.getSelectedIndex() == 3))){
           if(! this.modeHardcore)
                System.out.println("-- MODE HARDCORE ACTIVE");
            this.modeHardcore = true;
        }else{
            if(this.modeHardcore){
                if(selectIaDifficulte.getSelectedIndex() == 3)
                    selectIaDifficulte.setSelectedIndex(selectIaDifficulte.getSelectedIndex()-1);
              System.out.println("-- MODE HARDCORE DESACTIVE");
            }
            this.modeHardcore = false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aideTaille;
    private javax.swing.JButton boutonDemarrer;
    private javax.swing.JLabel labeIaText;
    private javax.swing.JTextField saisieTaille;
    private javax.swing.JComboBox<String> selectIaDifficulte;
    private javax.swing.JLabel textJ1;
    private javax.swing.JLabel textJ2;
    private javax.swing.JLabel textNomJ1;
    private javax.swing.JLabel textNomJ2;
    private javax.swing.JLabel textTaille;
    private javax.swing.JLabel textTypeJoueurs;
    private javax.swing.JLabel titre;
    private javax.swing.JComboBox<String> typeJ1;
    private javax.swing.JComboBox<String> typeJ2;
    private javax.swing.JTextField valeurNom1;
    private javax.swing.JTextField valeurNom2;
    // End of variables declaration//GEN-END:variables
}
