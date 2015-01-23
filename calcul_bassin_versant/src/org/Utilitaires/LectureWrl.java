/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5
 * triangulation of Delaunay
 *
 * This library is developed at Ecole Centrales de Nantes as part of a practical
 * project.
 *
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package org.Utilitaires;

import org.geometry.WPoint;
import org.geometry.WTriangle;
import org.geometry.Vecteur;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.watershed.error.WatershedError;

/**
 * Used in the translation of a wrl file into a watershed
 *
 * @author Guillaume Vedeau
 * @author Antoine Vedeau
 */
public class LectureWrl {

    private final String source;
    private ArrayList<WPoint> listePoint;
    private ArrayList<WTriangle> listeTriangle;

    public LectureWrl(String source) throws WatershedError {
        this.source = source;
        listePoint = new ArrayList();
        listeTriangle = new ArrayList();
        lecture();
    }

    public ArrayList<WPoint> getListePoint() {
        return listePoint;
    }

    public void setListePoint(ArrayList<WPoint> listePoint) {
        this.listePoint = listePoint;
    }

    public ArrayList<WTriangle> getListeTriangle() {
        return listeTriangle;
    }

    public void setListeTriangle(ArrayList<WTriangle> listeTriangle) {
        this.listeTriangle = listeTriangle;
    }

    /**
     * Permet de lire un fichier au format wrl et de récupérer les informations
     * qu'il contient (triangles du maillage)
     */
    @SuppressWarnings("empty-statement")
    public void lecture() throws WatershedError {

        try {
            String ligne;

            BufferedReader fichier = new BufferedReader(new FileReader(source));

            while (!("#x y z pt".equals(fichier.readLine())));

            while (!("] # end point".equals(fichier.readLine()))) {
                ligne = fichier.readLine();
                lecturePoints(ligne);
            }

            for (int i = 0; i < 3; i++) {
                fichier.readLine();
            }

            while (!("".equals(fichier.readLine()))) {
                ligne = fichier.readLine();
                lectureTriangles(ligne);
            }

            fichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Permet de créer un nouveau point à partir de la ligne lue et de l'ajouter
     * à la liste
     *
     * @param ligne
     */
    private void lecturePoints(String ligne) throws WatershedError {

        String delimiteurs = " ,;";

        StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteurs);

        double x;
        double y;
        double z;

        x = Double.parseDouble(tokenizer.nextToken());
        y = Double.parseDouble(tokenizer.nextToken());
        z = Double.parseDouble(tokenizer.nextToken());

        WPoint p = new WPoint(x, y, z);

        listePoint.add(p);

    }

    /**
     * Permet de créer un nouveau triangle à partir de la ligne lue et de
     * l'ajouter à la liste
     *
     * @param ligne
     */
    private void lectureTriangles(String ligne) throws WatershedError {

        String delimiteurs = " ,;  \t";

        StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteurs);

        int a;
        int b;
        int c;

        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());

        WPoint pointa = listePoint.get(a);
        WPoint pointb = listePoint.get(b);
        WPoint pointc = listePoint.get(c);
        WTriangle t = new WTriangle();
        if (Vecteur.distAngle((new Vecteur(pointa, pointb)).calculAngle(), (new Vecteur(pointa, pointc)).calculAngle()) < Math.PI) {

            t.setPoint1(a);
            t.setPoint2(b);
            t.setPoint3(c);

        } else {

            t.setPoint1(a);
            t.setPoint2(c);
            t.setPoint3(b);

        }
        t.setSegment1(-1);
        t.setSegment2(-1);
        t.setSegment3(-1);
        listeTriangle.add(t);
    }

}
