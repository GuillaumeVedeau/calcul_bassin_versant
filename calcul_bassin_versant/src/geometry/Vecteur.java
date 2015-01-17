/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import static java.lang.Math.*;
import java.util.ArrayList;

/**
 * gere les objets de type vecteur
 *
 * @author Utilisateur
 */
public class Vecteur {

    private double valx;
    private double valy;

    /**
     * Default constructor
     */
    public Vecteur() {
        valx = 0;
        valy = 0;
    }

    /**
     * Classic constructor
     *
     * @param valx
     * @param valy
     */
    public Vecteur(double valx, double valy) {
        this.valx = valx;
        this.valy = valy;
    }

    /**
     * Constructor based on Segment using an ArrayList of Point3D
     *
     * @param segment the 
     * @param points
     */
    public Vecteur(Segment segment, ArrayList<Point3D> points) {
        this.valx = points.get(segment.getPoint2()).getPosx() - points.get(segment.getPoint1()).getPosx();
        this.valy = points.get(segment.getPoint2()).getPosy() - points.get(segment.getPoint1()).getPosy();
    }

    /**
     * Constructor based on 2 Point
     *
     * @param point1
     * @param point2
     */
    public Vecteur(Point3D point1, Point3D point2) {
        this.valx = point2.getPosx() - point1.getPosx();
        this.valy = point2.getPosy() - point1.getPosy();
    }

    /**
     * Get the value of valy
     *
     * @return the value of valy
     */
    public double getValy() {
        return valy;
    }

    /**
     * Set the value of valy
     *
     * @param valy new value of valy
     */
    public void setValy(double valy) {
        this.valy = valy;
    }

    /**
     * Get the value of valx
     *
     * @return the value of valx
     */
    public double getValx() {
        return valx;
    }

    /**
     * Set the value of valx
     *
     * @param valx new value of valx
     */
    public void setValx(double valx) {
        this.valx = valx;
    }

    /**
     * renvoie l'angle d'un vecteur en radians
     * @return 
     */
    public double calculAngle() {
        double angle = 0;
        if (this.getValx() == 0 && this.getValy() == 0) {
            // ce cas ne doit pas etre atteint normalement
        } else {
            if (asin(this.getValy() / (sqrt(this.getValx() * this.getValx() + this.getValy() * this.getValy()))) >= 0) {
                angle =  acos(this.getValx() / (sqrt(this.getValx() * this.getValx() + this.getValy() * this.getValy())));
            } else {
                angle =  acos(this.getValx() / (sqrt(this.getValx() * this.getValx() + this.getValy() * this.getValy())));
                angle =  (-angle + 2*Math.PI);
            }
        }
        return angle;
    }
    /**
     * calcul de la distance angulaire entre 2 angles dans le sens de rotation trigonométrique (de l'angle
     * @param angle1
     * @param angle2
     * @return distance distance angulaire entre angle1 et angle2
     */
    public static double distAngle(double angle1, double angle2){
 
        double distance;
        distance = angle2 - angle1;
        if(distance < 0){
            distance = (distance + 2*Math.PI);
        }
        return distance;
    }

}
