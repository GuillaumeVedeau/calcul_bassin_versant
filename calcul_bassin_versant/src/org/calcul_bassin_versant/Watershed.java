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
import org.Utilitaires.EcritureWrl;
import org.Utilitaires.LectureWrl;
import org.geometry.WEdge;
import org.geometry.WPoint;
import org.geometry.WTriangle;
import org.watershed.error.WatershedError;

/**
 * this class is used in order to determinate the watershed of an exutory given
 * a Delaunay network of the environment Call the proper constructor, then get
 * the result
 *
 * @author Guillaume Vedeau
 * @author Antoine Rigoureau
 */
public class Watershed {

    //the list of WTriangle that are part of the watershed
    private ArrayList<WTriangle> watershed;

    // root to a wrl file
    private String rootFileWrl = "";
    private ArrayList<WTriangle> wTriangles;
    private ArrayList<WEdge> wEdges;
    private ArrayList<WPoint> wPoints;
    private WPoint exutory;

    /**
     * Constructor in case of use of a srl file
     *
     * @param lien
     * @param exutory
     * @throws WatershedError
     */
    public Watershed(String lien, WPoint exutory) throws WatershedError {

        LectureWrl file = new LectureWrl(lien);
        wTriangles = file.getListeTriangle();
        wPoints = file.getListePoint();
        this.exutory = exutory;
        wEdges = new ArrayList<>();
        wEdges.add(new WEdge());

        WTriangle.construction(wTriangles, wEdges);

        exutory.initialisation(wTriangles, wEdges, wPoints, watershed);

    }

    /**
     * Constructor based in allready existing WTriangles and WPoints
     *
     * @param wTriangles
     * @param wPoints
     * @param exutory
     * @throws WatershedError
     */
    public Watershed(ArrayList<WTriangle> wTriangles, ArrayList<WPoint> wPoints, WPoint exutory) throws WatershedError {

        this.wTriangles = wTriangles;
        this.wPoints = wPoints;

        for (WTriangle triangle : wTriangles) {
            int i = wPoints.size();
            if (triangle.getPoint1() < 0 || triangle.getPoint2() < 0 || triangle.getPoint3() < 0
                    || triangle.getPoint1() >= i || triangle.getPoint2() >= i || triangle.getPoint3() >= i) {
                throw new WatershedError(WatershedError.WATERSHED_ERROR_TRIANGLE_INCORRECT);
            }

            WTriangle.construction(wTriangles, wEdges);

            exutory.initialisation(wTriangles, wEdges, wPoints, watershed);
        }

    }

    /**
     * Get the value of watershed
     *
     * @return watershed
     */
    public ArrayList<WTriangle> getWatershed() {
        return watershed;
    }

    /**
     * Set the value of watershed
     *
     * @param watershed new value of watershed
     */
    public void setWatershed(ArrayList<WTriangle> watershed) {
        this.watershed = watershed;
    }

    /**
     * Get the value of rootFileWrl
     *
     * @return rootFileWrl
     */
    public String getRootFileWrl() {
        return rootFileWrl;
    }

    /**
     * Set the value of rootFileWrl
     *
     * @param rootFileWrl
     */
    public void setRootFileWrl(String rootFileWrl) {
        this.rootFileWrl = rootFileWrl;
    }

    /**
     * Get the value of wTriangles
     *
     * @return wTriangles
     */
    public ArrayList<WTriangle> getwTriangles() {
        return wTriangles;
    }

    /**
     * Set the value of wTriangles
     *
     * @param wTriangles
     */
    public void setwTriangles(ArrayList<WTriangle> wTriangles) {
        this.wTriangles = wTriangles;
    }

    /**
     * Get the value of wEdges
     *
     * @return wEdges
     */
    public ArrayList<WEdge> getwEdges() {
        return wEdges;
    }

    /**
     * Set the value of wEdges
     *
     * @param wEdges
     */
    public void setwEdges(ArrayList<WEdge> wEdges) {
        this.wEdges = wEdges;
    }

    /**
     * Get the value of wPoints
     *
     * @return wPoints
     */
    public ArrayList<WPoint> getwPoints() {
        return wPoints;
    }

    /**
     * Set the value of wPoints
     *
     * @param wPoints
     */
    public void setwPoints(ArrayList<WPoint> wPoints) {
        this.wPoints = wPoints;
    }

    /**
     * Get the value of exutory
     *
     * @return exutory
     */
    public WPoint getExutory() {
        return exutory;
    }

    /**
     * Set the value of exutory
     *
     * @param exutory
     */
    public void setExutory(WPoint exutory) {
        this.exutory = exutory;
    }

    /**
     * create a wrl file representating the watershed
     * @param root 
     */
    public void makeWrl(String root) {
        EcritureWrl file = new EcritureWrl(root, watershed, wPoints);
    }
}
