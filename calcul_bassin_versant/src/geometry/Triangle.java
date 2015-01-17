/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import java.util.ArrayList;

/**
 * gere les objet de type Triangle
 *
 * @author Utilisateur
 */
public class Triangle {

    private int segment1;
    private int segment2;
    private int segment3;

    private int point1;
    private int point2;
    private int point3;
    // approximation par defaut faite sur les calcul d'angle (1°)
    private static double approximationAngulaire = 0.16;

    /**
     * Basic constructor based on Segment :the order of the point is based on
     * the common point between each segment
     *
     * @param segment1
     * @param segment2
     * @param segment3
     * @param segments
     * @param points
     */
    public Triangle(int segment1, int segment2, int segment3, ArrayList<Segment> segments, ArrayList<Point3D> points) {

        this.segment1 = segment1;
        this.segment2 = segment2;
        this.segment3 = segment3;

        if (segments.get(segment1).getPoint1() == segments.get(segment2).getPoint1() || segments.get(segment1).getPoint1() == segments.get(segment2).getPoint2()) {
            this.point1 = segments.get(segment1).getPoint2();
            this.point2 = segments.get(segment1).getPoint1();

            if (segments.get(segment1).getPoint1() == segments.get(segment2).getPoint1()) {
                this.point3 = segments.get(segment2).getPoint2();
            } else {
                this.point3 = segments.get(segment2).getPoint1();
            }
        } else {
            this.point1 = segments.get(segment1).getPoint1();
            this.point2 = segments.get(segment1).getPoint2();

            if (segments.get(segment1).getPoint2() == segments.get(segment2).getPoint1()) {
                this.point3 = segments.get(segment2).getPoint2();
            } else {
                this.point3 = segments.get(segment2).getPoint1();
            }
        }
    }

    /**
     * basic constructor based on Point It cannot be used as it is in the
     * program, but need to be called by the other method construction
     * beforehand.
     *
     * @param point1
     * @param point2
     * @param point3
     */
    public Triangle(int point1, int point2, int point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.segment1 = 0;
        this.segment2 = 0;
        this.segment3 = 0;
    }

    /**
     * default constructor
     */
    public Triangle() {
        this.point1 = 0;
        this.point2 = 0;
        this.point3 = 0;
        this.segment1 = 0;
        this.segment2 = 0;
        this.segment3 = 0;
    }

    /**
     * Get the value of segment3
     *
     * @return the value of segment3
     */
    public int getSegment3() {
        return segment3;
    }

    /**
     * Set the value of segment3
     *
     * @param segment3 new value of segment3
     */
    public void setSegment3(int segment3) {
        this.segment3 = segment3;
    }

    /**
     * Get the value of segment2
     *
     * @return the value of segment2
     */
    public int getSegment2() {
        return segment2;
    }

    /**
     * Set the value of segment2
     *
     * @param segment2 new value of segment2
     */
    public void setSegment2(int segment2) {
        this.segment2 = segment2;
    }

    /**
     * Get the value of segment1
     *
     * @return the value of segment1
     */
    public int getSegment1() {
        return segment1;
    }

    /**
     * Set the value of segment1
     *
     * @param segment1 new value of segment1
     */
    public void setSegment1(int segment1) {
        this.segment1 = segment1;
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
     * Set the value of segment1
     *
     * @param point1 new value of point1
     */
    public void setPoint1(int point1) {
        this.point1 = point1;
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
     * Set the value of segment2
     *
     * @param point2 new value of point2
     */
    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    /**
     * Get the value of point3
     *
     * @return the value of point3
     */
    public int getPoint3() {
        return point3;
    }

    /**
     * Set the value of segment3
     *
     * @param point3 new value of point3
     */
    public void setPoint3(int point3) {
        this.point3 = point3;
    }

    /**
     * Build the objects Segment needed in the program and
     *
     * @param triangles
     * @param segments if there are allready some
     */
    public static void construction(ArrayList<Triangle> triangles, ArrayList<Segment> segments) {

        boolean found1, found2, found3;
        int posSegment;
        int posTriangle = 0;

        for (Triangle triangle : triangles) {

            if (triangle != null) {

                if (triangle.getSegment1() <= 0) {

                    found1 = false;
                    posSegment = 0;
                    for (Segment segment : segments) {

                        if (segment.getPoint1() == triangle.getPoint1() && segment.getPoint2() == triangle.getPoint2()) {
                            triangle.setSegment1(posSegment);
                            segment.setTrigauche(posTriangle);
                            found1 = true;
                        }
                        if ((segment.getPoint2() == triangle.getPoint1() && segment.getPoint1() == triangle.getPoint2())) {
                            triangle.setSegment1(posSegment);
                            segment.setTridroit(posTriangle);
                            found1 = true;
                        }
                        posSegment++;
                    }
                    if (!found1) {

                        triangle.setSegment1(segments.size());
                        segments.add(new Segment(triangle.getPoint1(), triangle.getPoint2(), 0, posTriangle));
                    }
                }

                if (triangle.getSegment2() <= 0) {

                    found2 = false;
                    posSegment = 0;
                    for (Segment segment : segments) {

                        if (segment.getPoint1() == triangle.getPoint2() && segment.getPoint2() == triangle.getPoint3()) {
                            triangle.setSegment2(posSegment);
                            segment.setTrigauche(posTriangle);
                            found2 = true;
                        }
                        if ((segment.getPoint2() == triangle.getPoint2() && segment.getPoint1() == triangle.getPoint3())) {
                            triangle.setSegment2(posSegment);
                            segment.setTridroit(posTriangle);
                            found2 = true;
                        }
                        posSegment++;
                    }
                    if (!found2) {

                        triangle.setSegment2(segments.size());
                        segments.add(new Segment(triangle.getPoint2(), triangle.getPoint3(), 0, posTriangle));
                    }
                }

                if (triangle.getSegment3() <= 0) {

                    found3 = false;
                    posSegment = 0;
                    for (Segment segment : segments) {

                        if (segment.getPoint1() == triangle.getPoint3() && segment.getPoint2() == triangle.getPoint1()) {
                            triangle.setSegment3(posSegment);
                            segment.setTrigauche(posTriangle);
                            found3 = true;
                        }
                        if ((segment.getPoint2() == triangle.getPoint3() && segment.getPoint1() == triangle.getPoint1())) {
                            triangle.setSegment3(posSegment);
                            segment.setTridroit(posTriangle);
                            found3 = true;
                        }
                        posSegment++;
                    }
                    if (!found3) {

                        triangle.setSegment3(segments.size());
                        segments.add(new Segment(triangle.getPoint3(), triangle.getPoint1(), 0, posTriangle));
                    }
                }
            }
            posTriangle++;
        }

    }

    @Override
    /**
     * surcharge de toString
     */
    public String toString() {
        return ("\n\nTriangle:" + "\n\tpoint1\t" + point1 + "\n\tpoint2\t" + point2 + "\n\tpoint3\t" + point3 + "\n\tseg1\t" + segment1 + "\n\tseg2\t" + segment2 + "\n\tseg3\t" + segment3);
    }

    /**
     * Renvoie l'égalité complete entre 2 objets de type Triangle ( les objets
     * le constituant sont identiques)
     *
     * @param triangle
     * @return
     */
    public boolean equals(Triangle triangle) {

        if (triangle == null) {
            return false;
        } else {

            return ((this.getPoint1() == triangle.getPoint1()
                    && this.getPoint2() == triangle.getPoint2()
                    && this.getPoint3() == triangle.getPoint3())
                    || (this.getPoint1() == (triangle.getPoint2())
                    && this.getPoint2() == (triangle.getPoint3())
                    && this.getPoint3() == (triangle.getPoint1()))
                    || (this.getPoint1() == (triangle.getPoint3())
                    && this.getPoint2() == (triangle.getPoint1())
                    && this.getPoint3() == (triangle.getPoint2())));
        }
    }

    /**
     * Get the value of approximation angulaire
     *
     * @return the value of approximation angulaire
     */
    public static double getApproximationAngulaire() {
        return approximationAngulaire;
    }

    /**
     * Set the value of approximation angulaire
     *
     * @param appro new value of approximation angulaire
     */
    public static void setApproximationAngulaire(double appro) {
        approximationAngulaire = appro;
    }

    /**
     * calcule le vecteur pente en 2D indiquant la direction de plus forte
     * descente d'une surface en 2.5D
     *
     * @return
     */
    public Vecteur calculPente(ArrayList<Point3D> points) {
        Vecteur pente = new Vecteur(0, 0);
        double x1 = points.get(this.getPoint1()).getPosx() - points.get(this.getPoint2()).getPosx();
        double x2 = points.get(this.getPoint1()).getPosx() - points.get(this.getPoint3()).getPosx();
        double y1 = points.get(this.getPoint1()).getPosy() - points.get(this.getPoint2()).getPosy();
        double y2 = points.get(this.getPoint1()).getPosy() - points.get(this.getPoint3()).getPosy();
        double z1 = points.get(this.getPoint1()).getPosz() - points.get(this.getPoint2()).getPosz();
        double z2 = points.get(this.getPoint1()).getPosz() - points.get(this.getPoint3()).getPosz();

        if (x1 * y2 - y1 * x2 >= 0) {
            pente.setValx((y1 * z2 - z1 * y2));
            pente.setValy((z1 * x2 - x1 * z2));
        } else {
            pente.setValx(-(y1 * z2 - z1 * y2));
            pente.setValy(-(z1 * x2 - x1 * z2));
        }

        return pente;
    }

    /**
     * calcule la part d'un triangle faisant partie du bassin versant sachant le
     * segment qui l'y relie, et propage ensuite selon les nouveaux segments qui
     * y sont rattaché
     *
     * @param segment
     * @param triangles
     * @param segments
     * @param points
     * @param bassinVersant
     */
    public void calculProjete(int segment, ArrayList<Triangle> triangles, ArrayList<Segment> segments, ArrayList<Point3D> points, ArrayList<Triangle> bassinVersant) {
        
        
        if ((!segments.get(segment).gettraiteDroit() && this.equals(triangles.get(segments.get(segment).getTridroit()))) || (!segments.get(segment).getTraiteGauche()
                && this.equals(triangles.get(segments.get(segment).getTrigauche())))) {

            if (this.equals(triangles.get(segments.get(segment).getTridroit()))) {
                segments.get(segment).setTraiteDroit(true);
            } else {
                segments.get(segment).setTraiteGauche(true);
            }

            int pointA = 0, pointB = 0, pointC = 0;
            int segmentAB = 0, segmentBC = 0, segmentAC = 0;

            Vecteur pente = this.calculPente(points);

            if (segments.get(segment).getPoint1() == this.getPoint1()) {

                if (segments.get(segment).getPoint2() == this.getPoint2()) {

                    pointA = this.getPoint3();
                    pointB = this.getPoint1();
                    pointC = this.getPoint2();
                    segmentAB = this.getSegment3();
                    segmentBC = this.getSegment1();
                    segmentAC = this.getSegment2();
                } else {
                    pointA = this.getPoint2();
                    pointB = this.getPoint3();
                    pointC = this.getPoint1();
                    segmentAB = this.getSegment2();
                    segmentBC = this.getSegment3();
                    segmentAC = this.getSegment1();
                }
            }

            if (segments.get(segment).getPoint1() == this.getPoint2()) {

                if (segments.get(segment).getPoint2() == this.getPoint3()) {

                    pointA = this.getPoint1();
                    pointB = this.getPoint2();
                    pointC = this.getPoint3();
                    segmentAB = this.getSegment1();
                    segmentBC = this.getSegment2();
                    segmentAC = this.getSegment3();
                } else {
                    pointA = this.getPoint3();
                    pointB = this.getPoint1();
                    pointC = this.getPoint2();
                    segmentAB = this.getSegment3();
                    segmentBC = this.getSegment1();
                    segmentAC = this.getSegment2();
                }
            }

            if (segments.get(segment).getPoint1() == this.getPoint3()) {

                if (segments.get(segment).getPoint2() == this.getPoint1()) {

                    pointA = this.getPoint2();
                    pointB = this.getPoint3();
                    pointC = this.getPoint1();
                    segmentAB = this.getSegment2();
                    segmentBC = this.getSegment3();
                    segmentAC = this.getSegment1();
                } else {
                    pointA = this.getPoint1();
                    pointB = this.getPoint2();
                    pointC = this.getPoint3();
                    segmentAB = this.getSegment1();
                    segmentBC = this.getSegment2();
                    segmentAC = this.getSegment3();
                }
            }

            double angleBC = (new Vecteur(points.get(pointB), points.get(pointC)).calculAngle());
            double angleCB = (new Vecteur(points.get(pointC), points.get(pointB)).calculAngle());

            double angleAB = (new Vecteur(points.get(pointA), points.get(pointB)).calculAngle());
            double angleAC = (new Vecteur(points.get(pointA), points.get(pointC)).calculAngle());

            Vecteur pente1 = this.calculPente(points);

            if ((pente1.getValx() == 0) && (pente1.getValy() == 0)) {
                // projection sur le triangle entier dans le cas d'une surface horizontale
                bassinVersant.add(this);

                if (segments.get(segmentAB).getTridroit() > 0) {
                    if (triangles.get(segments.get(segmentAB).getTridroit()).equals(this)) {
                        if (segments.get(segmentAB).getTrigauche() > 0) {
                            triangles.get(segments.get(segmentAB).getTrigauche()).calculProjete(segmentAB, triangles, segments, points, bassinVersant);

                        }
                    } else {
                        triangles.get(segments.get(segmentAB).getTridroit()).calculProjete(segmentAB, triangles, segments, points, bassinVersant);
                    }
                }

                if (segments.get(segmentAC).getTridroit() > 0) {
                    if (triangles.get(segments.get(segmentAC).getTridroit()).equals(this)) {
                        if (segments.get(segmentAC).getTrigauche() > 0) {

                            triangles.get(segments.get(segmentAC).getTrigauche()).calculProjete(segmentAC, triangles, segments, points, bassinVersant);

                        }
                    } else {
                        triangles.get(segments.get(segmentAC).getTridroit()).calculProjete(segmentAC, triangles, segments, points, bassinVersant);
                    }
                }
            } else {

               
                double anglePente = pente1.calculAngle();
                
                if (((Vecteur.distAngle(angleBC, anglePente) - Vecteur.distAngle(angleBC, angleCB)) < approximationAngulaire) || (Math.abs(angleBC - anglePente) < approximationAngulaire)) {
                    //1er cas: pas de propagation:
                    //rien pour le moment, il n'y a pas de propagation donc pas d'appel récursif

                } else {
                    //2eme cas : propagation totale du triangle
                    if (((Vecteur.distAngle(angleAB, anglePente) - Vecteur.distAngle(angleAB, angleAC)) < approximationAngulaire) || (Math.abs(angleAB - anglePente) < approximationAngulaire)) {

                        // projection sur le triangle entier
                        bassinVersant.add(this);

                        if (segments.get(segmentAB).getTridroit() > 0) {
                            if (triangles.get(segments.get(segmentAB).getTridroit()).equals(this)) {
                                if (segments.get(segmentAB).getTrigauche() > 0) {
                                    triangles.get(segments.get(segmentAB).getTrigauche()).calculProjete(segmentAB, triangles, segments, points, bassinVersant);

                                }
                            } else {
                                triangles.get(segments.get(segmentAB).getTridroit()).calculProjete(segmentAB, triangles, segments, points, bassinVersant);
                            }
                        }

                        if (segments.get(segmentAC).getTridroit() > 0) {
                            if (triangles.get(segments.get(segmentAC).getTridroit()).equals(this)) {
                                if (segments.get(segmentAC).getTrigauche() > 0) {
                                    triangles.get(segments.get(segmentAC).getTrigauche()).calculProjete(segmentAC, triangles, segments, points, bassinVersant);

                                }
                            } else {
                                triangles.get(segments.get(segmentAC).getTridroit()).calculProjete(segmentAC, triangles, segments, points, bassinVersant);
                            }
                        }

                    } else { // Les 2 cas précédents ont priorité sur les 2 qui suivent pour les cas qui sont en commun

                        Point3D separation = new Point3D(0, 0, 0);

                        //3eme cas : propagation partielle sur le triangle, comprenant une partie du segment AC
                        if (Vecteur.distAngle(angleCB, anglePente) <= Vecteur.distAngle(angleCB, angleAB)) {

                            //projection de B sur le segmentAC suivant la pente 
                            separation = Point3D.intersection(segments.get(segmentAC), points.get(pointB), pente, points);
                            int separateur = points.size();
                            points.add(separation);
                            segments.get(segmentAC).decoupe(separateur, triangles.indexOf(this), pointB, bassinVersant, points, segments, triangles);

                        }

                        //4ème cas : propagation partielle sur le triangle, comprenant une partie du segment AB
                        if (Vecteur.distAngle(angleAC, anglePente) <= Vecteur.distAngle(angleAC, angleBC)) {

                            // projection de C sur le segmentAB suivant la pente
                            separation = Point3D.intersection(segments.get(segmentAB), points.get(pointC), pente, points);
                            int separateur = points.size();
                            points.add(separation);
                            segments.get(segmentAB).decoupe(separateur, triangles.indexOf(this), pointB, bassinVersant, points, segments, triangles);

                        }
                    }
                }
            }
        }
    }

    public void seekSegment(ArrayList<Segment> segments, ArrayList<Point3D> points) {

        boolean found1 = false, found2 = false, found3 = false;
        int position = 1;
        for (Segment segment : segments) {

            if ((segment.getPoint1() == this.getPoint1() && segment.getPoint2() == this.getPoint2())
                    || (segment.getPoint2() == this.getPoint1() && segment.getPoint1() == this.getPoint2())) {

            }

        }
    }

}
