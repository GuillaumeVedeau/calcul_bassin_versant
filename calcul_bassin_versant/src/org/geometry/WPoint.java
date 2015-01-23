/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5
 * triangulation of Delaunay
 *
 * This library is developed at Ecole Centrales de Nantes as part of a practical
 * project.
 *
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package org.geometry;

import java.util.ArrayList;
import org.watershed.error.WatershedError;
import static org.watershed.error.WatershedError.WATERSHED_ERROR_POINT_NOT_FOUND;

/**
 * the smallest element of a WTriangle
 *
 * @author Antoine Rigoureau
 * @author Guillaume Vedeau
 */
public class WPoint {

    private double posx;
    private double posy;
    private double posz;
    private boolean started = false;

    /**
     * Initialize point
     *
     * @param x
     * @param y
     * @param z
     * @throws WatershedError If x, y or z is not set
     */
    private void init(double x, double y, double z) throws WatershedError {
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
            throw new WatershedError(WatershedError.WATERSHED_ERROR_ERROR_POINT_XYZ);
        }
        this.posx = x;
        this.posy = y;
        this.posz = z;
    }

    /**
     * Build a point at coordinates x,y,z
     *
     * @param posx
     * @param posy
     * @param posz
     * @throws WatershedError WatershedError
     */
    public WPoint(double posx, double posy, double posz) throws WatershedError {
        init(posx, posy, posz);
    }

    /**
     * Build a point at the origin
     *
     * @throws WatershedError WatershedError
     */
    public WPoint() throws WatershedError {
        init(0, 0, 0);
    }

    /**
     * Build a point as a copy of another point
     *
     * @param pt
     * @throws WatershedError WatershedError
     */
    public WPoint(WPoint pt) throws WatershedError {
        init(pt.getPosx(), pt.getPosy(), pt.getPosz());
    }

    /**
     * Get the value of posx
     *
     * @return the value of posx
     */
    public double getPosx() {
        return posx;
    }

    /**
     * Set the value of posx
     *
     * @param posx new value of posx
     */
    public void setPosx(double posx) {
        this.posx = posx;
    }

    /**
     * Get the value of posy
     *
     * @return the value of posy
     */
    public double getPosy() {
        return posy;
    }

    /**
     * Set the value of posy
     *
     * @param posy new value of posy
     */
    public void setPosy(double posy) {
        this.posy = posy;
    }

    /**
     * Get the value of posz
     *
     * @return the value of posz
     */
    public double getPosz() {
        return posz;
    }

    /**
     * Set the value of posz
     *
     * @param posz new value of posz
     */
    public void setPosz(double posz) {
        this.posz = posz;
    }

    /**
     * Get the value of started
     *
     * @return
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Set the value of started
     *
     * @param started
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Check the egality between 2 WPoint
     *
     * @param point
     * @return
     */
    public boolean equals(WPoint point) {

        if (point == null) {
            return false;
        } else {

            return (this.getPosx() == point.getPosx()
                    && this.getPosy() == point.getPosy()
                    && this.getPosz() == point.getPosz());
        }
    }

    @Override
    /**
     * surcharge de toString
     */
    public String toString() {
        return ("\tx:" + posx + "\ty:" + posy + "\tz:" + posz); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * renvoie le point de projection d'un point sur un segment suivant un
     * vecteur
     *
     * @param segment
     * @param point
     * @param vecteur
     * @return
     */
    public static WPoint intersection(WEdge segment, WPoint point, Vecteur vecteur, ArrayList<WPoint> points) throws WatershedError {
        double a1, b1, c1;
        double a2, b2, c2;
        WPoint intersect = new WPoint(0, 0, 0);
        int pointSeg1 = segment.getPoint1();
        int pointSeg2 = segment.getPoint2();

        a1 = vecteur.getValy();
        b1 = -vecteur.getValx();
        c1 = (a1 * point.getPosx() + b1 * point.getPosy());

        Vecteur vect = new Vecteur(segment, points);

        a2 = vect.getValy();
        b2 = -vect.getValx();
        c2 = (a2 * points.get(pointSeg1).getPosx() + b2 * points.get(pointSeg1).getPosy());
        if ((a1 == a2) && (b1 == b2)) {
            //TODO gerer l'erreur dû au parrallélisme
        } else {
            intersect.setPosx((c1 * b2 - c2 * b1) / (a1 * b2 - a2 * b1));
            intersect.setPosy((c1 * a2 - c2 * a1) / (b1 * a2 - b2 * a1));

            if (points.get(pointSeg1).getPosx() == points.get(pointSeg2).getPosx()) {
                intersect.setPosz(points.get(pointSeg1).getPosz() + (points.get(pointSeg2).getPosz() - points.get(pointSeg1).getPosz()) * ((points.get(pointSeg1).getPosy() - intersect.getPosy()) / (points.get(pointSeg1).getPosy() - points.get(pointSeg2).getPosy())));

            } else {
                intersect.setPosz(points.get(pointSeg1).getPosz() + (points.get(pointSeg2).getPosz() - points.get(pointSeg1).getPosz()) * ((points.get(pointSeg1).getPosx() - intersect.getPosx()) / (points.get(pointSeg1).getPosx() - points.get(pointSeg2).getPosx())));
            }

        }
        return intersect;

    }

    /**
     * This function is the trigger to the calcul of the watershed if the layout
     * of the datas is respected
     *
     * @param triangles
     * @param segments
     * @param points
     * @param watershed
     * @throws WatershedError
     */
    public void initialisation(ArrayList<WTriangle> triangles, ArrayList<WEdge> segments, ArrayList<WPoint> points, ArrayList<WTriangle> watershed) throws WatershedError {

        int depart = -1;
        int pos = 0;
        for (WPoint point : points) {
            if (this.equals(point)) {
                depart = pos;
            }
            pos++;
        }
        if (depart == -1) {
            throw new WatershedError(WatershedError.WATERSHED_ERROR_POINT_NOT_FOUND);
        }

        for (WEdge segment : segments) {

            if (segment.getPoint1() == depart) {
                points.get(segment.getPoint1()).setStarted(true);

                double direction = (new Vecteur(points.get(segment.getPoint1()), points.get(segment.getPoint2())).calculAngle());

                if (points.get(segment.getPoint1()).getPosz() < points.get(segment.getPoint2()).getPosz()) {
                    if (segment.getTridroit() >= 0) {
                        double penteDroit = triangles.get(segment.getTridroit()).calculPente(points).calculAngle();
                        if (Vecteur.distAngle(direction, penteDroit) <= Math.PI) {
                            triangles.get(segment.getTridroit()).calculProjete(segments.indexOf(segment), triangles, segments, points, watershed);

                        }
                        
                    }
                    if (segment.getTrigauche() >= 0) {
                        double penteGauche = triangles.get(segment.getTridroit()).calculPente(points).calculAngle();
                        if (Vecteur.distAngle(direction, penteGauche) <= Math.PI) {
                            triangles.get(segment.getTrigauche()).calculProjete(segments.indexOf(segment), triangles, segments, points, watershed);

                        }
                        
                    }

                }
                if (points.get(segment.getPoint1()).getPosz() == points.get(segment.getPoint2()).getPosz() && !points.get(segment.getPoint2()).isStarted()) {
                    points.get(segment.getPoint2()).initialisation(triangles, segments, points, watershed);
                }

            }
            
            if (segment.getPoint2() == depart) {
                points.get(segment.getPoint2()).setStarted(true);

                double direction = (new Vecteur(points.get(segment.getPoint1()), points.get(segment.getPoint2())).calculAngle());

                if (points.get(segment.getPoint2()).getPosz() < points.get(segment.getPoint1()).getPosz()) {
                    if (segment.getTridroit() >= 0) {
                        double penteDroit = triangles.get(segment.getTridroit()).calculPente(points).calculAngle();
                        if (Vecteur.distAngle(direction, penteDroit) <= Math.PI) {
                            triangles.get(segment.getTridroit()).calculProjete(segments.indexOf(segment), triangles, segments, points, watershed);

                        }
                      
                    }
                    if (segment.getTrigauche() >= 0) {
                        double penteGauche = triangles.get(segment.getTrigauche()).calculPente(points).calculAngle();
                        if (Vecteur.distAngle(direction, penteGauche) <= Math.PI) {
                            triangles.get(segment.getTridroit()).calculProjete(segments.indexOf(segment), triangles, segments, points, watershed);

                        }
                      
                    }

                }
                if (points.get(segment.getPoint2()).getPosz() == points.get(segment.getPoint1()).getPosz() && !points.get(segment.getPoint1()).isStarted()) {
                    points.get(segment.getPoint1()).initialisation(triangles, segments, points, watershed);
                }

            }

        }
    }
}
