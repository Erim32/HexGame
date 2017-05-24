package gui.grille;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Planeau de la partie.
 *
 * @author Mesté Rémi.
 */
public class Plateau extends JPanel {

    private final Hexagone grille[];
    private final Triangle bordure[];
    private static int taille; //largeur ou longueur
    private static final int HAUTEUR = 20; //pixels
    private final static Color COULEUR_J1 = Color.BLUE;
    private final static Color COULEUR_J2 = Color.RED;

    public Plateau(int taillePlateau) {
        //attributs
        this.taille = taillePlateau;
        this.grille = new Hexagone[this.taille * this.taille];
        this.bordure = new Triangle[this.taille * this.taille - 4];

        setOpaque(true);

        //parametres du jpanel
        //creation des hexagones
        int decalageMinmum = 20;
        int decalageHaut = 10;

        //On ajoute les hexagonnes
        for (int i = 0; i < (taille * taille); i++) {
            //calcul des points
            if (i % taille == 0) {
                decalageHaut += 30;
            }

            if ((i != 0) && (i % taille == 0)) {
                decalageMinmum += 20;
            }

            //ajouts
            grille[i] = new Hexagone(decalageMinmum + ((i % taille) * (2 * HAUTEUR - 4)), decalageHaut, HAUTEUR);
        }

        for (int i = 0; i < 7; i++) {
            int x = grille[0].getPoint(i).getX();
            int y = grille[0].getPoint(i).getY();
        }

        /*=================================================
                            Bordures
        ===================================================*/
        //haut
        for (int i = 1; i < this.taille; i++) {
            Point p1 = new Point(grille[i - 1].getPoint(3).getX(), grille[i - 1].getPoint(3).getY());
            Point p2 = new Point(grille[i].getPoint(3).getX(), grille[i].getPoint(3).getY());
            Point p3 = new Point(grille[i - 1].getPoint(2).getX(), grille[i - 1].getPoint(2).getY());
            bordure[i - 1] = new Triangle(p1, p2, p3, COULEUR_J2);
        }

        //bas
        int debut = (this.taille) * ((this.taille) - 1);
        for (int i = debut + 1; i < debut + (this.taille); i++) {
            Point p1 = new Point(grille[i].getPoint(5).getX(), grille[i - 1].getPoint(5).getY());
            Point p2 = new Point(grille[i].getPoint(0).getX(), grille[i].getPoint(0).getY());
            Point p3 = new Point(grille[i - 1].getPoint(0).getX(), grille[i - 1].getPoint(0).getY());
            bordure[i - debut + (this.taille) - 2] = new Triangle(p1, p2, p3, COULEUR_J2);
        }

        //gauche
        int t = (this.taille); //alias pour reduire la longueur de la ligne
        for (int i = 1; i < (this.taille); i++) {
            Point p1 = new Point(grille[t * i].getPoint(5).getX(), grille[t * i].getPoint(5).getY());
            Point p2 = new Point(grille[t * (i - 1)].getPoint(5).getX(), grille[t * (i - 1)].getPoint(5).getY());
            Point p3 = new Point(grille[t * (i - 1)].getPoint(0).getX(), grille[t * (i - 1)].getPoint(0).getY());
            bordure[(this.taille * 2) - 2 + i - 1] = new Triangle(p1, p2, p3, COULEUR_J1);
        }

        //droite
        t = (this.taille); //alias pour reduire la longueur de la ligne
        for (int i = 1; i < (this.taille); i++) {
            Point p1 = new Point(grille[(t * i) + (t - 1)].getPoint(2).getX(), grille[(t * i) + (t - 1)].getPoint(2).getY());
            Point p2 = new Point(grille[(t * (i - 1)) + (t - 1)].getPoint(1).getX(), grille[(t * (i - 1)) + (t - 1)].getPoint(1).getY());
            Point p3 = new Point(grille[(t * (i - 1)) + (t - 1)].getPoint(2).getX(), grille[(t * (i - 1)) + (t - 1)].getPoint(2).getY());
            bordure[(this.taille * 3) - 3 + i - 1] = new Triangle(p1, p2, p3, COULEUR_J1);
        }

        this.setPreferredSize(new Dimension(decalageMinmum + (this.taille * this.HAUTEUR), decalageHaut + (this.taille * HAUTEUR) - decalageHaut / 2));

    }

    //Dessin du plateau
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < taille * taille; i++) {
            grille[i].paintComponent(g);
        }

        for (int i = 0; i < (this.taille * 4) - 4; i++) {
            bordure[i].paintComponent(g);
        }

    }

    public Hexagone getHexagone(int numero) {
        assert numero >= 0;
        assert numero < (this.taille * this.taille);
        return this.grille[numero];
    }
}
