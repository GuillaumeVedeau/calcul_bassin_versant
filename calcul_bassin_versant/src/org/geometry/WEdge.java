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

/**
 * An edge in the triangulation. a WEdge is formed with two WPoint3D instances.
 *
 * A WEdge is linked to up to two WTriangle : one on its left, and one on its
 * right. This WEdge is an edge of these WTriangle. The left and right sides are
 * relative to the orientation of the edge. *
 *
 *
 * @author Antoine Rigoureau
 * @author Guillaume Vedeau
 */
public class WEdge {

    private int point1;
    private int point2;
    private int tridroit;
    private int trigauche;
    //an Wedge is considered to be treated if it the method 
    private boolean traiteDroit;
    private boolean traiteGauche;
    private boolean disparu = false;

    /**
     * classic constructor
     *
     * @param point1 index of the first WPoint
     * @param point2 index of the second WPoint
     */
    public WEdge(int point1, int point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.tridroit = -1;
        this.trigauche = -1;
        this.traiteDroit = false;
        this.traiteGauche = false;
    }

    /**
     * complete constructor
     *
     * @param point1 index of the first WPoint
     * @param point2 index of the second WPoint
     * @param tridroit index of the WTriangle on the right
     * @param trigauche index of the WTriangle on the left
     */
    public WEdge(int point1, int point2, int tridroit, int trigauche) {
        this.point1 = point1;
        this.point2 = point2;
        this.tridroit = tridroit;
        this.trigauche = trigauche;
        this.traiteDroit = false;
        this.traiteGauche = false;
    }

    /**
     * Default constructor
     */
    public WEdge() {
        this.point1 = -1;
        this.point2 = -1;
        this.tridroit = -1;
        this.trigauche = -1;
        this.traiteDroit = false;
        this.traiteGauche = false;
    }

    @Override
    public String toString() {
        return "Segment{" + "point1=" + point1 + ", point2=" + point2 + ", tridroit=" + tridroit + ", trigauche=" + trigauche + ", traiteDroit=" + traiteDroit + ", traiteGauche=" + traiteGauche + '}';
    }

    /**
     * Compares the specified WEdge with this WEdge for equality. Returns true
     * if all their elements are egals
     *
     *
     * @param segment
     * @return
     */
    public boolean equals(WEdge segment) {
        return ((this.getPoint1() == segment.getPoint1())
                && (this.getPoint2() == segment.getPoint2())
                && this.getTridroit() == segment.getTridroit()
                && this.getTrigauche() == segment.getTrigauche()
                && this.getTraiteGauche() == segment.getTraiteGauche()
                && this.gettraiteDroit() == segment.gettraiteDroit());
    }

    public int search(ArrayList<WEdge> segments, ArrayList<WPoint> points) {
        int position = 0;
        int posSegment = 0;
        for (WEdge segment : segments) {
            if ((points.get(this.getPoint1()).equals(points.get(segment.getPoint1()))) && points.get(this.getPoint2()).equals(points.get(segment.getPoint2()))
                    || (points.get(this.getPoint1()).equals(points.get(segment.getPoint2())) && points.get(this.getPoint2()).equals(points.get(segment.getPoint1())))) {
                position = posSegment;
            }
            posSegment++;
        }
        return position;
    }

    /**
     * Get the value of trigauche
     *
     * @return the value of trigauche
     */
    public int getTrigauche() {
        return trigauche;
    }

    /**
     * Set the value of trigauche
     *
     * @param trigauche new value of trigauche
     */
    public void setTrigauche(int trigauche) {
        this.trigauche = trigauche;
    }

    /**
     * Get the value of tridroit
     *
     * @return the value of tridroit
     */
    public int getTridroit() {
        return tridroit;
    }

    /**
     * Set the value of tridroit
     *
     * @param tridroit new value of tridroit
     */
    public void setTridroit(int tridroit) {
        this.tridroit = tridroit;
    }

    /**
     * Get the value of point2
     *
     * @return the value of point2
     */
    public int getPoint2() {
        return point2;
    }

    /**
     * Set the value of point2
     *
     * @param point2 new value of point2
     */
    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    /**
     * Get the value of point1
     *
     * @return the value of point1
     */
    public int getPoint1() {
        return point1;
    }

    /**
     * Set the value of point1
     *
     * @param point1 new value of point1
     */
    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    /**
     * Get the value of traiteDroit
     *
     * @return the value of point1
     */
    public boolean gettraiteDroit() {
        return traiteDroit;
    }

    /**
     * Set the value of traiteDroit
     *
     * @param traiteDroit new value of traiteDroit
     */
    public void setTraiteDroit(boolean traiteDroit) {
        this.traiteDroit = traiteDroit;
    }

    /**
     * Get the value of traiteGauche
     *
     * @return the value of traiteGauche
     */
    public boolean getTraiteGauche() {
        return traiteGauche;
    }

    /**
     * Set the value of traiteGauche
     *
     * @param traiteGauche new value of traiteGauche
     */
    public void setTraiteGauche(boolean traiteGauche) {
        this.traiteGauche = traiteGauche;
    }

    /**
     * Get the value of disparu
     *
     * @return the value of disparu
     */
    public boolean isDisparu() {
        return disparu;
    }

    /**
     * Set the value of disparu
     *
     * @param disparu new value of disparu
     */
    public void setDisparu(boolean disparu) {
        this.disparu = disparu;
    }

    /**
     * cherche et complete trigauche et tridroit du segment selon une liste de
     * triangle donné, et met à jour les triangle concernés
     *
     * @param triangles liste de triangles dans lequel il faut chercher ceux
     * juxtaposés au segment manipulé
     */
    public void chercheTriangle(ArrayList<WTriangle> triangles, ArrayList<WEdge> segments) {

        int pos = 0;
        for (WTriangle triangle : triangles) {
            if (triangle.getPoint2() == point1 && triangle.getPoint1() == point2) {
                this.setTridroit(pos);
                triangle.setSegment1(segments.indexOf(this));
            }
            if (triangle.getPoint3() == point1 && triangle.getPoint2() == point2) {
                this.setTridroit(pos);
                triangle.setSegment2(segments.indexOf(this));
            }
            if (triangle.getPoint1() == point1 && triangle.getPoint3() == point2) {
                this.setTridroit(pos);
                triangle.setSegment3(segments.indexOf(this));
            }

            if (triangle.getPoint1() == point1 && triangle.getPoint2() == point2) {
                this.setTrigauche(pos);
                triangle.setSegment1(segments.indexOf(this));
            }
            if (triangle.getPoint2() == point1 && triangle.getPoint3() == point2) {
                this.setTrigauche(pos);
                triangle.setSegment2(segments.indexOf(this));
            }
            if (triangle.getPoint3() == point1 && triangle.getPoint1() == point2) {
                this.setTrigauche(pos);
                triangle.setSegment3(segments.indexOf(this));
            }

            pos++;
        }

    }

    /**
     * gere la propagation d'un bassin versant dans le cas où le triangle du
     * maillage necessite d'etre découpé Le segment est celui qui peut étendre
     * le bassin versant
     *
     * @param pointPos séparateur du segment en 2, calculé precedemment selon le
     * bassin versant
     * @param bassin triangle où le bassin versant c'est propagé, et dont seule
     * une partie est intégré dans le bassin versant, nécessitant un redecoupage
     * du segment d'où l'eau peut provenir et des 2 triangles adjacent (triangle
     * appelant la methode calculprojete)
     * @param passant point du triangle "bassin" , appartenant égalemment au
     * segment à découper et au segment parametre de la methode calculProjete
     * ayant entrainé l'appel de cette methode
     * @param bassinVersant
     * @param points
     * @param segments
     * @param triangles
     */
    public void decoupe(int pointPos, int bassin, int passant, ArrayList<WTriangle> bassinVersant, ArrayList<WPoint> points, ArrayList<WEdge> segments, ArrayList<WTriangle> triangles) throws WatershedError {

        if (!this.isDisparu()) {
            this.setDisparu(true);
            int pointExtGauche = -1, pointExtDroit = -1;
            int seg1 = -1, seg2 = -1;
            int segDroitExt1 = -1, segGaucheExt1 = -1, segDroitExt2 = -1, segGaucheExt2 = -1, segDroitMid = -1, segGaucheMid = -1;
            int triDroit1 = -1, triDroit2 = -1, triGauche1 = -1, triGauche2 = -1;

            seg1 = segments.size();
            segments.add(new WEdge(this.getPoint2(), pointPos));
            seg2 = segments.size();
            segments.add(new WEdge(pointPos, this.getPoint1()));

            // découpe du 1er triangle  juxtaposé (droit)
            if (this.getTridroit() >= 0) {

                if ((triangles.get(this.getTridroit()).getPoint1() == this.getPoint2() && triangles.get(this.getTridroit()).getPoint2() == this.getPoint1())
                        || (triangles.get(this.getTridroit()).getPoint1() == this.getPoint1() && triangles.get(this.getTridroit()).getPoint2() == this.getPoint2())) {
                    pointExtDroit = triangles.get(this.getTridroit()).getPoint3();
                    segDroitExt1 = triangles.get(this.getTridroit()).getSegment3();
                    segDroitExt2 = triangles.get(this.getTridroit()).getSegment2();
                }

                if ((triangles.get(this.getTridroit()).getPoint2() == this.getPoint2() || triangles.get(this.getTridroit()).getPoint3() == this.getPoint1())
                        || (triangles.get(this.getTridroit()).getPoint2() == this.getPoint1() || triangles.get(this.getTridroit()).getPoint3() == this.getPoint2())) {
                    pointExtDroit = triangles.get(this.getTridroit()).getPoint1();
                    segDroitExt1 = triangles.get(this.getTridroit()).getSegment1();
                    segDroitExt2 = triangles.get(this.getTridroit()).getSegment3();

                }
                if ((triangles.get(this.getTridroit()).getPoint3() == this.getPoint2() || triangles.get(this.getTridroit()).getPoint1() == this.getPoint1())
                        || (triangles.get(this.getTridroit()).getPoint3() == this.getPoint1() || triangles.get(this.getTridroit()).getPoint1() == this.getPoint2())) {
                    pointExtDroit = triangles.get(this.getTridroit()).getPoint2();
                    segDroitExt1 = triangles.get(this.getTridroit()).getSegment2();
                    segDroitExt2 = triangles.get(this.getTridroit()).getSegment1();

                }

                segDroitMid = segments.size();
                segments.add(new WEdge(pointPos, pointExtDroit));

                triDroit1 = triangles.size();
                triangles.add(new WTriangle(seg1, segDroitMid, segDroitExt1, segments, points));

                triDroit2 = triangles.size();
                triangles.add(new WTriangle(segDroitExt2, segDroitMid, seg2, segments, points));

                //ajoute les 2 nouveaux triangles au nouveau segment qui les sépare
                segments.get(segDroitMid).setTridroit(triDroit2);
                segments.get(segDroitMid).setTrigauche(triDroit1);

                // modifie les segment extérieurs pour qu'ils prennent en compte les 2 nouveaux triangles
                if (this.getTridroit() == segments.get(segDroitExt2).getTridroit()) {
                    segments.get(segDroitExt2).setTridroit(triDroit1);
                } else {
                    segments.get(segDroitExt2).setTrigauche(triDroit1);

                }

                if (this.getTridroit() == segments.get(segDroitExt1).getTridroit()) {
                    segments.get(segDroitExt1).setTridroit(triDroit2);
                } else {
                    segments.get(segDroitExt1).setTrigauche(triDroit2);
                }

            }

            // découpe du 2eme triangle  juxtaposé
            if (this.getTrigauche() >= 0) {
                if ((triangles.get(this.getTrigauche()).getPoint1() == this.getPoint1() && triangles.get(this.getTrigauche()).getPoint2() == this.getPoint2())
                        || (triangles.get(this.getTrigauche()).getPoint1() == this.getPoint2() && triangles.get(this.getTrigauche()).getPoint2() == this.getPoint1())) {
                    pointExtGauche = triangles.get(this.getTrigauche()).getPoint3();
                    segGaucheExt1 = triangles.get(this.getTrigauche()).getSegment3();
                    segGaucheExt2 = triangles.get(this.getTrigauche()).getSegment2();
                }
                if ((triangles.get(this.getTrigauche()).getPoint2() == this.getPoint1() && triangles.get(this.getTrigauche()).getPoint3() == this.getPoint2())
                        || (triangles.get(this.getTrigauche()).getPoint2() == this.getPoint2() && triangles.get(this.getTrigauche()).getPoint3() == this.getPoint1())) {
                    pointExtGauche = triangles.get(this.getTrigauche()).getPoint1();
                    segGaucheExt1 = triangles.get(this.getTrigauche()).getSegment1();
                    segGaucheExt2 = triangles.get(this.getTrigauche()).getSegment3();

                }
                if ((triangles.get(this.getTrigauche()).getPoint3() == this.getPoint1() && triangles.get(this.getTrigauche()).getPoint1() == this.getPoint2())
                        || (triangles.get(this.getTrigauche()).getPoint3() == this.getPoint2() && triangles.get(this.getTrigauche()).getPoint1() == this.getPoint1())) {
                    pointExtGauche = triangles.get(this.getTrigauche()).getPoint2();
                    segGaucheExt1 = triangles.get(this.getTrigauche()).getSegment2();
                    segGaucheExt2 = triangles.get(this.getTrigauche()).getSegment1();

                }
                System.out.println(this + "   " + segments.indexOf(this) + triangles.get(this.getTrigauche()) + "  " + this.getTrigauche() + " yop ");
                segGaucheMid = segments.size();
                segments.add(new WEdge(pointPos, pointExtGauche));

                triGauche1 = triangles.size();
                triangles.add(new WTriangle(seg2, segGaucheMid, segGaucheExt1, segments, points));
                triGauche2 = triangles.size();
                triangles.add(new WTriangle(segGaucheExt2, segGaucheMid, seg1, segments, points));

                //add 2 news WTriangle to the 
                segments.get(segGaucheMid).setTridroit(triGauche2);
                segments.get(segGaucheMid).setTrigauche(triGauche1);

                // modifie les segment extérieurs pour qu'ils prennent en compte les 2 nouveaux triangles
                if (this.getTrigauche() == segments.get(segGaucheExt1).getTridroit()) {
                    segments.get(segGaucheExt1).setTridroit(triGauche1);
                } else {
                    segments.get(segGaucheExt1).setTrigauche(triGauche1);

                }

                if (this.getTrigauche() == segments.get(segGaucheExt2).getTridroit()) {
                    segments.get(segGaucheExt2).setTridroit(triGauche2);
                } else {
                    segments.get(segGaucheExt2).setTrigauche(triGauche2);
                }
            }

            // ajout des 4 triangles créés aux 2 segments séparant les 2 anciens triangles
            if (this.getTridroit() >= 0) {
                segments.get(seg2).setTrigauche(triDroit2);
                segments.get(seg1).setTrigauche(triDroit1);
            }
            if (this.getTrigauche() >= 0) {
                segments.get(seg2).setTridroit(triGauche1);
                segments.get(seg1).setTridroit(triGauche2);
            }

            // Ajout du triangle correspondant au bassin versant et propagation du bassin (cas où le triangle qui a provoqué la séparation était le triangle droit du segment actuel)
            if ((bassin == this.getTridroit())) {

                if (triangles.get(triDroit1).getPoint1() == passant || triangles.get(triDroit1).getPoint2() == passant || triangles.get(triDroit1).getPoint3() == passant) {
                    bassinVersant.add(triangles.get(triDroit1));
                    if (triDroit1 == segments.get(triangles.get(triDroit1).getSegment1()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit1).getSegment1()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment1()).getTrigauche()).calculProjete(triangles.get(triDroit1).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit1).getSegment1()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment1()).getTridroit()).calculProjete(triangles.get(triDroit1).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (triDroit1 == segments.get(triangles.get(triDroit1).getSegment2()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit1).getSegment2()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment2()).getTrigauche()).calculProjete(triangles.get(triDroit1).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit1).getSegment2()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment2()).getTridroit()).calculProjete(triangles.get(triDroit1).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (triDroit1 == segments.get(triangles.get(triDroit1).getSegment3()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit1).getSegment3()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment3()).getTrigauche()).calculProjete(triangles.get(triDroit1).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit1).getSegment3()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit1).getSegment3()).getTridroit()).calculProjete(triangles.get(triDroit1).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    }

                } else {
                    bassinVersant.add(triangles.get(triDroit2));
                    if (triDroit2 == segments.get(triangles.get(triDroit2).getSegment1()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit2).getSegment1()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment1()).getTrigauche()).calculProjete(triangles.get(triDroit2).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit2).getSegment1()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment1()).getTridroit()).calculProjete(triangles.get(triDroit2).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (triDroit2 == segments.get(triangles.get(triDroit2).getSegment2()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit2).getSegment2()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment2()).getTrigauche()).calculProjete(triangles.get(triDroit2).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit2).getSegment2()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment2()).getTridroit()).calculProjete(triangles.get(triDroit2).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (triDroit2 == segments.get(triangles.get(triDroit2).getSegment3()).getTridroit()) {
                        if (segments.get(triangles.get(triDroit2).getSegment3()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment3()).getTrigauche()).calculProjete(triangles.get(triDroit2).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    } else {
                        if (segments.get(triangles.get(triDroit2).getSegment3()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triDroit2).getSegment3()).getTridroit()).calculProjete(triangles.get(triDroit2).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    }
                }
            }

            // Ajout du triangle correspondant au bassin versant et propagation du bassin (cas où le triangle qui a provoqué la séparation était le triangle gauche du segment actuel)
            if (bassin == this.getTrigauche()) {

                if (triangles.get(triGauche1).getPoint1() == passant || triangles.get(triGauche1).getPoint2() == passant || triangles.get(triGauche1).getPoint3() == passant) {
                    bassinVersant.add(triangles.get(triGauche1));

                    if (segments.get(triangles.get(triGauche1).getSegment1()).getTridroit() == triGauche1) {

                        if (segments.get(triangles.get(triGauche1).getSegment1()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment1()).getTrigauche()).calculProjete(triangles.get(triGauche1).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche1).getSegment1()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment1()).getTridroit()).calculProjete(triangles.get(triGauche1).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (segments.get(triangles.get(triGauche1).getSegment2()).getTridroit() == triGauche1) {

                        if (segments.get(triangles.get(triGauche1).getSegment2()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment2()).getTrigauche()).calculProjete(triangles.get(triGauche1).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche1).getSegment2()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment2()).getTridroit()).calculProjete(triangles.get(triGauche1).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (segments.get(triangles.get(triGauche1).getSegment3()).getTridroit() == triGauche1) {

                        if (segments.get(triangles.get(triGauche1).getSegment3()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment3()).getTrigauche()).calculProjete(triangles.get(triGauche1).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche1).getSegment3()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche1).getSegment3()).getTridroit()).calculProjete(triangles.get(triGauche1).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    }

                } else {
                    bassinVersant.add(triangles.get(triGauche2));
                    if (triGauche2 == segments.get(triangles.get(triGauche2).getSegment1()).getTridroit()) {

                        if (segments.get(triangles.get(triGauche2).getSegment1()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment1()).getTrigauche()).calculProjete(triangles.get(triGauche2).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche2).getSegment1()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment1()).getTridroit()).calculProjete(triangles.get(triGauche2).getSegment1(), triangles, segments, points, bassinVersant);
                        }
                    }

                    if (triGauche2 == segments.get(triangles.get(triGauche2).getSegment2()).getTridroit()) {

                        if (segments.get(triangles.get(triGauche2).getSegment2()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment2()).getTrigauche()).calculProjete(triangles.get(triGauche2).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche2).getSegment2()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment2()).getTridroit()).calculProjete(triangles.get(triGauche2).getSegment2(), triangles, segments, points, bassinVersant);
                        }
                    }
                    if (triGauche2 == segments.get(triangles.get(triGauche2).getSegment3()).getTridroit()) {

                        if (segments.get(triangles.get(triGauche1).getSegment3()).getTrigauche() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment3()).getTrigauche()).calculProjete(triangles.get(triGauche2).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    } else {

                        if (segments.get(triangles.get(triGauche2).getSegment3()).getTridroit() >= 0) {
                            triangles.get(segments.get(triangles.get(triGauche2).getSegment3()).getTridroit()).calculProjete(triangles.get(triGauche2).getSegment3(), triangles, segments, points, bassinVersant);
                        }
                    }
                }

            }
        }
    }
}
