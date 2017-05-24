package gui.boites_de_dialogues;

/**
 * Fenetre graphique en cas d'erreur interne d'initialisation.
 *
 * @author Rémi Mesté
 */
public class DialogErreurInterne extends javax.swing.JFrame {

    /**
     * Création d'une nouvelle fenetre de dialogue d'erreur interne.
     */
    public DialogErreurInterne() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titre = new javax.swing.JLabel();
        exitBouton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titre.setFont(titre.getFont().deriveFont(titre.getFont().getSize()+9f));
        titre.setForeground(new java.awt.Color(227, 24, 31));
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titre.setText("Une erreur interne est survenue");

        exitBouton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        exitBouton.setText("Quitter");
        exitBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBoutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(exitBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Interaction lorsque l'on clique sur le bouton quitter.
     *
     * @param evt
     */
    private void exitBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBoutonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBoutonActionPerformed

    /**
     * Invocation de la Dialoginterne
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogErreurInterne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Creation et affichage de la form */
        java.awt.EventQueue.invokeLater(() -> {
            new DialogErreurInterne().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBouton;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
