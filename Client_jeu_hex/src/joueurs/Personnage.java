package joueurs;

import java.awt.Color;

/**
 * Classe abstrecte definissant les personnages
 *
 * @author Mesté Rémi
 */
public abstract class Personnage {

    /**
     * Type de personnages
     */
    public enum TypePersonnages {
        IA, JOUEUR
    };

    //attributs
    private TypePersonnages type;
    private final String NOM;
    private final Color COULEUR;
    private final char CARACTERE;

    /**
     * COnstructeur d'un personnage
     *
     * @param type type IA ou joueur
     * @param nom nom du personnage
     * @param couleur Couleur de jeu
     * @param caractere Caractere de plateau.
     */
    public Personnage(TypePersonnages type, String nom, Color couleur, char caractere) {
        this.type = type;
        this.NOM = nom;
        this.COULEUR = couleur;
        this.CARACTERE = caractere;
    }

    //accesseurs
    /**
     * Accesseur au type de personnage
     *
     * @return IA ou JOUEUR
     */
    public TypePersonnages getType() {
        return type;
    }

    /**
     * Accesseur au nom propre au personage
     *
     * @return String nom
     */
    public String getNom() {
        return NOM;
    }

    /**
     * Accesseur a la couleur propre au personnage
     *
     * @return Color
     */
    public Color getCouleur() {
        return COULEUR;
    }

    /**
     * Accesseur au caractere propre au personnage
     *
     * @return cjar
     */
    public char getCaractere() {
        return CARACTERE;
    }

    /**
     * Message lorsque le personnage doit jouer
     *
     * @return String
     */
    public abstract String getMessageTour();

    /**
     * Message lorsque le personnage a gagné la partie
     *
     * @return String
     */
    public abstract String getMessageGagnant();
}
