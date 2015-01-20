/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5
 * triangulation of Delaunay
 *
 * This library is developed at Ecole Centrales de Nantes as part of a practical
 * project.
 *
 * Watershed is a free software: you can redistribute it and/or modify it.
 */

package org.calcul_bassin_versant;

import java.util.ArrayList;
import org.Utilitaires.LectureWrl;
import org.geometry.WEdge;
import org.geometry.WPoint;
import org.geometry.WTriangle;
import org.watershed.error.WatershedError;

/**
 * 
 * @author Guillaume Vedeau
 * @author Antoine Rigoureau
 */
public class Watershed {
    
   //the list of WTriangle that are part of the watershed
    private ArrayList<WTriangle> watershed;
     
   // root to a 
    private String rootFileWrl = "";
    private ArrayList<WTriangle> wTriangles;
    private ArrayList<WEdge> wEdges;
    private ArrayList<WPoint> wPoints;
    private WPoint exutory;
    
    
    public Watershed(String lien, WPoint exutory) throws WatershedError{
       
        LectureWrl file = new LectureWrl(lien);
        wTriangles = file.getListeTriangle();
        wPoints = file.getListePoint();
        this.exutory = exutory;
        wEdges = new ArrayList<>();
        wEdges.add(new WEdge());
        
        WTriangle.construction(wTriangles, wEdges);
        
   
        
    }
}
