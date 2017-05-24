package gui.grille;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Construction d'un triangle
 *
 * @author remi
 */
public class Triangle extends Polygon {

    private final Point coordonnees[] = new Point[3];
    private Color couleurInterne;
    private final static Color couleurExterne = Color.BLACK;

    Triangle(Point p1, Point p2, Point p3, Color couleur) {
        //affectation des attributs
        this.couleurInterne = couleur;
        this.coordonnees[0] = p1;
        this.coordonnees[1] = p2;
        this.coordonnees[2] = p3;

    }

    /**
     * Dessine le triangle
     *
     * @param g
     */
    public void paintComponent(Graphics g) {

        //Creation des points
        for (int i = 0; i < 3; i++) {
            addPoint(this.coordonnees[i].getX(), this.coordonnees[i].getY());
        }

        //Dessins
        Graphics2D a = (Graphics2D) g;
        a.setStroke(new BasicStroke(1)); //gras
        a.setColor(this.couleurInterne);
        a.fillPolygon(this);
        a.setColor(couleurExterne);
        a.drawPolygon(this);
    }

    /**
     * Modifie la couleur interne du triangle
     *
     * @param couleurInterne
     */
    public void setCouleurInterne(Color couleurInterne) {
        this.couleurInterne = couleurInterne;
    }
}
