/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5 triangulation of Delaunay
 * 
 * This library is developed at Ecole Centrales de Nantes as part of a practical project.
 * 
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package org.calcul_bassin_versant;

import java.util.ArrayList;
import org.Utilitaires.EcritureWrl;
import org.Utilitaires.LectureWrl;
import org.geometry.WEdge;
import org.geometry.WPoint;
import org.geometry.WTriangle;
import org.watershed.error.WatershedError;

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
    public static void main(String[] args) throws WatershedError {
      

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
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        points.add(pointE);
        points.add(pointF);
        points.add(pointG);
        points.add(pointH);
        points.add(pointI);

        
        WTriangle triABC = new WTriangle(0, 1, 2);
        WTriangle triBDC = new WTriangle(1,3,2);
        WTriangle triBFD = new WTriangle(1,5,3);
        WTriangle triBEF = new WTriangle(1,4,5);
        WTriangle triCDG = new WTriangle(2,3,6);
        WTriangle triDFH = new WTriangle(3,5,7);
        WTriangle triFIH = new WTriangle(5,8,7);
        WTriangle triDHG = new WTriangle(3,7,6);
        
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
        int segHI = (new WEdge(7,8)).search(segments, points);
        
for(WTriangle triangle : triangles){
        System.out.println(triangle);
        }
for(WEdge segment : segments){
        System.out.println(segment);
        }

       triFIH.calculProjete(segHI,triangles, segments, points, resultat);
        System.out.println(resultat.size());

         for(WTriangle triangle : resultat){
        System.out.println(triangle);
        }
     /*  
         for(WPoint segment : points){
        System.out.println(segment);
        } 
        */
       /*ArrayList<WTriangle> triangles = new ArrayList<>();
       triangles.add(new WTriangle());
       ArrayList<WPoint> points = new ArrayList<>();
       points.add(new WPoint());
       ArrayList<WEdge> segments = new ArrayList<>();
        
        LectureWrl test = new LectureWrl("C:\\Users\\Utilisateur\\Documents\\NetBeansProjects\\calcul_bassin_versant2.0\\calcul_bassin_versant\\Arete.wrl");
        
        points = test.getListePoint();
        triangles = test.getListeTriangle();
        
        System.out.println(points.size());
        
        WTriangle.construction(triangles, segments);
        
        for(WEdge edge : segments){
            System.out.println(edge);
        }
        for(WTriangle triangle : triangles){
        System.out.println(triangle);
        }
        
        ArrayList<WTriangle> resultat = new ArrayList<>();
        System.out.println(segments.size());
         int ppo = points.size();
        triangles.get(4).calculProjete((triangles.get(4).getSegment1()), triangles, segments, points, resultat);
        System.out.println(resultat.size());
        
        for(WTriangle tri : resultat){
            System.out.println(resultat);
        }
        
        
        ArrayList<WPoint> point2 = new ArrayList<>();
        
        for(int i = ppo+1; i< points.size();i++){
            point2.add(points.get(i));
        }
        
        
       EcritureWrl sauvegarde = new EcritureWrl("C:\\Users\\Utilisateur\\Documents\\NetBeansProjects\\calcul_bassin_versant2.0\\calcul_bassin_versant\\Arete.wrl",resultat,point2);
    */}

}

