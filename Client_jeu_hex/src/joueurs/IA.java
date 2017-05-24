package joueurs;

import static joueurs.Personnage.TypePersonnages.IA;
import java.awt.Color;

/**
 * Creation de l'entité IA
 *
 * @author Mesté Rémi
 */
public class IA extends Personnage {

    //attributs
    private static int niveauDifficulte;

    /**
     * Creation d'un personnage de type IA.
     *
     * @param niveauDifficulte
     * @param couleur
     * @param caractere
     */
    public IA(int niveauDifficulte, Color couleur, char caractere) {
        super(IA, "L'odirdnateur", couleur, caractere);

        this.niveauDifficulte = niveauDifficulte;
    }

    public IA(String nom, int niveauDifficulte, Color couleur, char caractere) {
        super(IA, nom, couleur, caractere);

        this.niveauDifficulte = niveauDifficulte;
    }

    //accesseur
    public static int getNiveauDifficulte() {
        return niveauDifficulte;
    }

    @Override
    public String getMessageGagnant() {
        return getNom() + ": Je gagne toujours simple humain !!";
    }

    @Override
    public String getMessageTour() {
        return getNom() + ": Je prépare mon prochain coup ...";
    }
}
