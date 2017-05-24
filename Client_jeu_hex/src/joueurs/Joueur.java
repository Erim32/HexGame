package joueurs;

import static joueurs.Personnage.TypePersonnages.JOUEUR;
import java.awt.Color;

/**
 * Creation de l'entité IA
 *
 * @author Mesté Rémi
 */
public class Joueur extends Personnage {

    /**
     * Creation d'un personnage de type JOUEUR
     *
     * @param nom
     * @param couleur
     * @param caractere
     */
    public Joueur(String nom, Color couleur, char caractere) {
        super(JOUEUR, nom, couleur, caractere);
    }

    @Override
    public String getMessageGagnant() {
        return "Bravo: " + getNom() + " vous avez gangné !!";
    }

    @Override
    public String getMessageTour() {
        return "C'est au tour de " + getNom() + " de jouer.";
    }
}
