/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcul_bassin_versant;

import Utilitaires.LectureWrl;
import geometry.*;
import java.util.ArrayList;

/**
 * Calcul le bassin versant d'un point
 *
 * @author Guillaume Vedeau
 * @author Antoine Rigoureau
 */
public class Calcul_bassin_versant {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

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
        
        
        
        
        
        /*for(Triangle triangle : triangles){
        System.out.println(triangle);
        }*/
        
       /* for(Segment segment : segments){
        System.out.println(segment);
        }*/
        
       

        triFIH.calculProjete(segHI,triangles, segments, points, resultat);
        System.out.println(resultat.size());

         for(Triangle triangle : resultat){
        System.out.println(triangle);
        }
       
        
       
        LectureWrl test = new LectureWrl("C:/Users/Utilisateur/Documents/NetBeansProjects/calcul_bassin_versant/calcul_bassin_versant/Chezine.wrl");
        ArrayList<Segment> segments2 = new ArrayList<>();
        Triangle.construction(test.getListeTriangle(),segments2);
        
        System.out.println(segments2.size());
        
        ArrayList<Triangle> resultat2 = new ArrayList<>();
        
        test.getListeTriangle().get(segments2.get(75).getTridroit())
                .calculProjete(75, test.getListeTriangle(),
                        segments2, test.getListePoint(), resultat2);
        System.out.println(resultat2.size());
    }

}
