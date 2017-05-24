
import gui.FenetrePrincipale;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * Classe principale de lancement du programme.
 *
 * @author Rémi
 */
public class Client_jeu_hex {

    /**
     * Main de creation de l'interface
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame gui_main = new FenetrePrincipale();
        gui_main.setVisible(true);
    }

}
