package services;

import gui.FenetrePrincipale;
import gui.boites_de_dialogues.DialogAskQuitter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Detection de la pression sur la touche echap du clavier.
 *
 * @author Rémi
 */
public class EchapClavierRetourMenu {

    /**
     * Nouvelle instance de l ecoute de la pression de la touche echap
     */
    public EchapClavierRetourMenu() {
        //retour en arriere par pression de ecape
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        Action action = new AbstractAction("RetourMenuPrincipal") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("-- Detection press echap.");
                new DialogAskQuitter(null, true).setVisible(true);
            }
        };
        FenetrePrincipale.getGuiInterfacePartie().getActionMap().put("RetourMenuPrincipal", action);
        FenetrePrincipale.getGuiInterfacePartie().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "RetourMenuPrincipal");

    }

}
