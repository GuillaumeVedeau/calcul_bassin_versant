/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5
 * triangulation of Delaunay
 *
 * This library is developed at Ecole Centrales de Nantes as part of a practical
 * project.
 *
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package geometry;

import org.geometry.WTriangle;
import org.geometry.WPoint;
import org.geometry.Vecteur;
import org.geometry.WEdge;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.watershed.error.WatershedError;

/**
 *
 * @author Utilisateur
 */
public class WPointTest {

    public WPointTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of equals method, of class WPoint.
     *
     * @author Antoine Rigoureau
     * @author Guillaume Vedeau
     */
    @Test
    public void testEquals() throws WatershedError {

        WPoint point1 = new WPoint(0, 0, 0);
        WPoint point2 = new WPoint(1, 0, 0);
        WPoint point3 = new WPoint(0, 1, 0);
        WPoint point4 = new WPoint(0, 0, 1);
        WPoint point5 = new WPoint(1, 1, 1);
        WPoint point6 = new WPoint(0, 0, 0);
        WPoint point7 = new WPoint(1, 1, 1);

        Assert.assertTrue(point1.equals(point6));
        Assert.assertTrue(point5.equals(point7));
        Assert.assertFalse(point1.equals(point2));
        Assert.assertFalse(point1.equals(point3));
        Assert.assertFalse(point1.equals(point4));
        Assert.assertFalse(point1.equals(point5));

    }

    /**
     * Test of toString method, of class WPoint.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of getPosz method, of class WPoint.
     */
    @Test
    public void testGetPosz() throws WatershedError {
        WPoint point0 = new WPoint(0, 0, 0);
        WPoint point1 = new WPoint(0, 0, 1);

        Assert.assertTrue(point0.getPosz() == 0);
        Assert.assertTrue(point1.getPosz() == 1);
    }

    /**
     * Test of setPosz method, of class WPoint.
     */
    @Test
    public void testSetPosz() throws WatershedError {
        WPoint point0 = new WPoint();
        point0.setPosz(10);
        Assert.assertTrue(point0.getPosz() == 10);
    }

    /**
     * Test of getPosy method, of class WPoint.
     */
    @Test
    public void testGetPosy() throws WatershedError {
        WPoint point0 = new WPoint(0, 0, 0);
        WPoint point1 = new WPoint(0, 1, 0);

        Assert.assertTrue(point0.getPosy() == 0);
        Assert.assertTrue(point1.getPosy() == 1);
    }

    /**
     * Test of setPosy method, of class WPoint.
     */
    @Test
    public void testSetPosy() throws WatershedError {
        WPoint point0 = new WPoint();
        point0.setPosy(10);
        Assert.assertTrue(point0.getPosy() == 10);
    }

    /**
     * Test of getPosx method, of class WPoint.
     */
    @Test
    public void testGetPosx() throws WatershedError {
        WPoint point0 = new WPoint(0, 0, 0);
        WPoint point1 = new WPoint(1, 0, 0);

        Assert.assertTrue(point0.getPosx() == 0);
        Assert.assertTrue(point1.getPosx() == 1);
    }

    /**
     * Test of setPosx method, of class WPoint.
     */
    @Test
    public void testSetPosx() throws WatershedError {

        WPoint point0 = new WPoint();
        point0.setPosx(10);
        Assert.assertTrue(point0.getPosx() == 10);
    }

    /**
     * Test of intersection method, of class WPoint.
     */
    @Test
    public void testIntersection() throws WatershedError {

        WPoint pointA = new WPoint(0, 0, 0);
        WPoint pointB = new WPoint(10, 10, 10);
        WPoint pointC = new WPoint(5, 5, 5);
        WPoint pointD = new WPoint(10, 0, 0);
        ArrayList<WPoint> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        Vecteur vecteur1 = new Vecteur(0, 1);

        WEdge segment = new WEdge(0, 1);

        Assert.assertTrue(WPoint.intersection(segment, pointD, vecteur1, points).equals(pointC));
    }

    /**
     * Test of calculBassin method, of class WPoint.
     */
    @Test
    public void testCalculBassin() throws WatershedError {

        WPoint pointA = new WPoint(0, 0, 10);
        WPoint pointB = new WPoint(1, 0, 8);
        WPoint pointC = new WPoint(0, 1, 8);
        WPoint pointD = new WPoint(1, 1, 5);
        WPoint pointE = new WPoint(2, 0, 5);
        WPoint pointF = new WPoint(2, 1, 2);
        WPoint pointG = new WPoint(0, 2, 5);
        WPoint pointH = new WPoint(1, 2, 2);
        WPoint pointI = new WPoint(2, 2, 0);

        ArrayList<WPoint> points = new ArrayList<>();
        points.add(new WPoint(0, 0, 0));
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        points.add(pointE);
        points.add(pointF);
        points.add(pointG);
        points.add(pointH);
        points.add(pointI);

        WTriangle triABC = new WTriangle(1, 2, 3);
        WTriangle triBDC = new WTriangle(2, 4, 3);
        WTriangle triBFD = new WTriangle(2, 6, 4);
        WTriangle triBEF = new WTriangle(2, 5, 6);
        WTriangle triCDG = new WTriangle(3, 4, 7);
        WTriangle triDFH = new WTriangle(4, 6, 8);
        WTriangle triFIH = new WTriangle(6, 9, 8);
        WTriangle triDHG = new WTriangle(4, 8, 7);

        ArrayList<WTriangle> triangles = new ArrayList<>();
        ArrayList<WEdge> segments = new ArrayList<>();

        triangles.add(triABC);
        triangles.add(triBDC);
        triangles.add(triBFD);
        triangles.add(triBEF);
        triangles.add(triCDG);
        triangles.add(triDHG);
        triangles.add(triDFH);
        triangles.add(triFIH);

        WTriangle.construction(triangles, segments);
        ArrayList<WTriangle> resultat = new ArrayList<>();
        int segHI = (new WEdge(8, 9)).search(segments, points);

        triFIH.calculProjete(segHI, triangles, segments, points, resultat);
        Assert.assertTrue(resultat.size() == 6);

    }

}
