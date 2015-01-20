/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5 triangulation of Delaunay
 * 
 * This library is developed at Ecole Centrales de Nantes as part of a practical project.
 * 
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package org.geometry;

import static java.lang.Math.*;
import java.util.ArrayList;
import org.watershed.error.WatershedError;
import static org.watershed.error.WatershedError.WATERSHED_ERROR_ANGLE;

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
     * Constructor based on WEdge using an ArrayList of WPoint
     *
     * @param segment the 
     * @param points
     */
    public Vecteur(WEdge segment, ArrayList<WPoint> points) {
        this.valx = points.get(segment.getPoint2()).getPosx() - points.get(segment.getPoint1()).getPosx();
        this.valy = points.get(segment.getPoint2()).getPosy() - points.get(segment.getPoint1()).getPosy();
    }

    /**
     * Constructor based on 2 WPoint
     *
     * @param point1
     * @param point2
     */
    public Vecteur(WPoint point1, WPoint point2) {
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
