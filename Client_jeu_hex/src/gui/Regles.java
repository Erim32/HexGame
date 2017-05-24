package gui;

/**
 * Creation d'un JPanel pour présenter les regles du jeu
 *
 * @author Rémi Mesté
 */
public class Regles extends javax.swing.JPanel {

    /**
     * Creation d'une nouvelle interface Regles
     */
    public Regles() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        imageRegle1 = new javax.swing.JLabel();
        imageRegle2 = new javax.swing.JLabel();
        regle_source = new javax.swing.JLabel();
        relgesContent = new javax.swing.JLabel();
        explicationTitreLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Règles du jeu:");

        imageRegle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        imageRegle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/hexRegle1.png"))); // NOI18N
        imageRegle1.setText("Plateau en début de partie.");
        imageRegle1.setMaximumSize(new java.awt.Dimension(169, 106));
        imageRegle1.setMinimumSize(new java.awt.Dimension(169, 106));
        imageRegle1.setPreferredSize(new java.awt.Dimension(169, 106));
        imageRegle1.setRequestFocusEnabled(false);

        imageRegle2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        imageRegle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ressources/hexRegle2.png"))); // NOI18N
        imageRegle2.setText("Victoire du joueur bleu.");
        imageRegle2.setMaximumSize(new java.awt.Dimension(169, 106));
        imageRegle2.setMinimumSize(new java.awt.Dimension(169, 106));
        imageRegle2.setPreferredSize(new java.awt.Dimension(169, 106));

        regle_source.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        regle_source.setText("Sources: wikipedia et jeuxstrategieter.free.fr");

        explicationTitreLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        explicationTitreLabel.setText("Déroulement d'une partie:");

        jLabel3.setText("Au départ, le plateau est vide. Les Bleu ont le privilège de jouer les premiers. ");

        jLabel4.setText("À tour de rôle, chacun des joueurs pose un pion de sa couleur sur la case de son choix - à la seule condition que cette case, ");

        jLabel5.setText("ne soit pas déjà occupée. Une fois posé, un pion ne peut plus être déplacé.");

        jLabel6.setText(" Si deux pions se trouvent dans des cases ayant un côté commun, on dit qu'ils sont adjacents ou \"liés\". ");

        jLabel7.setText("Une chaîne de pions liés est appelée \"chaîne continue\". ");

        jLabel8.setText("La partie s'arrête quand un joueur a relié entre eux, par une chaîne continue de pions lui appartenant, ");

        jLabel9.setText(" Il ne peut y avoir de partie nulle, puisque l'un des deux adversaires finit toujours par y parvenir.");

        jLabel2.setText("les deux bords de sa couleur.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regle_source, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageRegle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageRegle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(relgesContent)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(explicationTitreLabel)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2))
                        .addGap(0, 45, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relgesContent)
                        .addGap(18, 18, 18)
                        .addComponent(imageRegle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(imageRegle2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(explicationTitreLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(regle_source))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel explicationTitreLabel;
    private javax.swing.JLabel imageRegle1;
    private javax.swing.JLabel imageRegle2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel regle_source;
    private javax.swing.JLabel relgesContent;
    // End of variables declaration//GEN-END:variables
}
