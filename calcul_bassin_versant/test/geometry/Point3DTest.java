/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometry;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Utilisateur
 */
public class Point3DTest {
    
    public Point3DTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of equals method, of class Point3D.
     */
    @Test
    public void testEquals() {
        
        Point3D point1 = new Point3D(0,0,0);
        Point3D point2 = new Point3D(1,0,0);
        Point3D point3 = new Point3D(0,1,0);
        Point3D point4 = new Point3D(0,0,1);
        Point3D point5 = new Point3D(1,1,1);
        Point3D point6 = new Point3D(0,0,0);
        Point3D point7 = new Point3D(1,1,1);
        
        Assert.assertTrue(point1.equals(point6));
        Assert.assertTrue(point5.equals(point7));
        Assert.assertFalse(point1.equals(point2));
        Assert.assertFalse(point1.equals(point3));
        Assert.assertFalse(point1.equals(point4));
        Assert.assertFalse(point1.equals(point5));
        
    }

    /**
     * Test of toString method, of class Point3D.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of getPosz method, of class Point3D.
     */
    @Test
    public void testGetPosz() {
        Point3D point0 = new Point3D(0,0,0);
        Point3D point1 = new Point3D(0,0,1);
        
        Assert.assertTrue(point0.getPosz() == 0);
        Assert.assertTrue(point1.getPosz() == 1);
    }

    /**
     * Test of setPosz method, of class Point3D.
     */
    @Test
    public void testSetPosz() {
        Point3D point0 = new Point3D();
        point0.setPosz(10);
        Assert.assertTrue(point0.getPosz() == 10);
    }

    /**
     * Test of getPosy method, of class Point3D.
     */
    @Test
    public void testGetPosy() {
        Point3D point0 = new Point3D(0,0,0);
        Point3D point1 = new Point3D(0,1,0);
        
        Assert.assertTrue(point0.getPosy() == 0);
        Assert.assertTrue(point1.getPosy() == 1);
    }

    /**
     * Test of setPosy method, of class Point3D.
     */
    @Test
    public void testSetPosy() {
        Point3D point0 = new Point3D();
        point0.setPosy(10);
        Assert.assertTrue(point0.getPosy() == 10);
    }

    /**
     * Test of getPosx method, of class Point3D.
     */
    @Test
    public void testGetPosx() {
        Point3D point0 = new Point3D(0,0,0);
        Point3D point1 = new Point3D(1,0,0);
        
        Assert.assertTrue(point0.getPosx() == 0);
        Assert.assertTrue(point1.getPosx() == 1);
    }

    /**
     * Test of setPosx method, of class Point3D.
     */
    @Test
    public void testSetPosx() {
        
        Point3D point0 = new Point3D();
        point0.setPosx(10);
        Assert.assertTrue(point0.getPosx() == 10);
    }

    /**
     * Test of intersection method, of class Point3D.
     */
    @Test
    public void testIntersection() {
        
        Point3D pointA = new Point3D(0,0,0); 
        Point3D pointB = new Point3D(10,10,10);
        Point3D pointC = new Point3D(5,5,5);
        Point3D pointD = new Point3D(10,0,0);
        ArrayList<Point3D> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        Vecteur vecteur1 = new Vecteur(0,1);
        
        Segment segment = new Segment(0, 1);
        
        Assert.assertTrue(Point3D.intersection(segment, pointD, vecteur1, points).equals(pointC));
    }

    /**
     * Test of calculBassin method, of class Point3D.
     */
    @Test
    public void testCalculBassin() {
        
         Point3D pointA = new Point3D(0, 0, 10);
        Point3D pointB = new Point3D(1, 0, 8);
        Point3D pointC = new Point3D(0, 1, 8);
        Point3D pointD = new Point3D(1, 1, 5);
        Point3D pointE = new Point3D(2, 0, 5);
        Point3D pointF = new Point3D(2, 1, 2);
        Point3D pointG = new Point3D(0, 2, 5);
        Point3D pointH = new Point3D(1, 2, 2);
        Point3D pointI = new Point3D(2, 2, 0);
        
        ArrayList<Point3D> points = new ArrayList<>();
        points.add(new Point3D(0,0,0));
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        points.add(pointE);
        points.add(pointF);
        points.add(pointG);
        points.add(pointH);
        points.add(pointI);

        
        Triangle triABC = new Triangle(1, 2, 3);
        Triangle triBDC = new Triangle(2,4,3);
        Triangle triBFD = new Triangle(2,6,4);
        Triangle triBEF = new Triangle(2,5,6);
        Triangle triCDG = new Triangle(3,4,7);
        Triangle triDFH = new Triangle(4,6,8);
        Triangle triFIH = new Triangle(6,9,8);
        Triangle triDHG = new Triangle(4,8,7);
        
        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Segment> segments = new ArrayList<>();
        
        segments.add(new Segment(0,0));
        triangles.add(new Triangle(0,0,0));
        triangles.add(triABC);
        triangles.add(triBDC);
        triangles.add(triBFD);
        triangles.add(triBEF);
        triangles.add(triCDG);
        triangles.add(triDHG);
        triangles.add(triDFH);
        triangles.add(triFIH);
        
        Triangle.construction(triangles, segments);
        ArrayList<Triangle> resultat = new ArrayList<>();
        int segHI = (new Segment(8,9)).search(segments, points);
        
       

        triFIH.calculProjete(segHI,triangles, segments, points, resultat);
        Assert.assertTrue(resultat.size()==6);

         
    }
    
}
