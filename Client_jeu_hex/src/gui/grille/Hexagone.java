package gui.grille;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/*
* @author Rémi Mesté
 */
public class Hexagone extends Polygon {

    private final static Color COULEUR_DEFAUT = Color.GRAY;
    private final Point centre;
    private final Point coordonnees[] = new Point[8];
    private final Color couleurBordure = Color.BLACK;
    private final int hauteur;
    private Color couleurInterne = Color.GRAY;

    public Hexagone(int xCentre, int yCentre, int hauteur) {
        this.centre = new Point(xCentre, yCentre);
        this.hauteur = hauteur;

        //calcul des coordonnees
        double calcul;
        int x, y;
        for (int i = 0; i < 7; i++) {
            calcul = Math.PI / 3.0 * i;
            x = (int) (Math.round(this.centre.getX() + Math.sin(calcul) * this.hauteur));
            y = (int) (Math.round(this.centre.getY() + Math.cos(calcul) * this.hauteur));
            this.coordonnees[i] = new Point(x, y);
        }

    }

    public void paintComponent(Graphics g) {
        //Creation des points
        for (int i = 0; i < 7; i++) {
            addPoint(coordonnees[i].getX(), coordonnees[i].getY());
        }

        //Dessins
        Graphics2D a = (Graphics2D) g;
        a.setStroke(new BasicStroke(2)); //gras
        a.setColor(this.couleurInterne);
        a.fillPolygon(this);
        a.setColor(this.couleurBordure);
        a.drawPolygon(this);
    }

    public void setCouleurInterne(Color couleurInterne) {
        this.couleurInterne = couleurInterne;
    }

    public Color getCouleurInterne() {
        return this.couleurInterne;
    }

    public Color getCouleurDefaut() {
        return this.COULEUR_DEFAUT;
    }

    public Point getPoint(int numero) {
        assert (numero >= 0 && numero < 8);
        return this.coordonnees[numero];
    }
}
