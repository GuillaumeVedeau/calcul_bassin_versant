/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires;

import geometry.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Antoine
 */
public class LectureWrl {
   
    private final  String source;
    private ArrayList<Point3D> listePoint;
    private ArrayList<Triangle> listeTriangle;
    
    public LectureWrl(String source){
        this.source=source;
        listePoint=new ArrayList();
        listeTriangle=new ArrayList();
        lecture();
   }

    public ArrayList<Point3D> getListePoint() {
        return listePoint;
    }

    public void setListePoint(ArrayList<Point3D> listePoint) {
        this.listePoint = listePoint;
    }

    public ArrayList<Triangle> getListeTriangle() {
        return listeTriangle;
    }

    public void setListeTriangle(ArrayList<Triangle> listeTriangle) {
        this.listeTriangle = listeTriangle;
    }
    
    
    
    /**
     * Permet de lire un fichier au format wrl et de récupérer les informations qu'il contient (triangles du maillage)
     */
    @SuppressWarnings("empty-statement")
    public void lecture(){
        
        try{
        String ligne;
        
        BufferedReader fichier = new BufferedReader(new FileReader(source));
        
        while(!("#x y z pt".equals(fichier.readLine())));
        
        while(!("] # end point".equals(fichier.readLine()))){
            ligne=fichier.readLine();
            lecturePoints(ligne);
        }
        
        for (int i=0;i<3;i++){
                fichier.readLine();
                }
        
        while(!("".equals(fichier.readLine()))){
            ligne=fichier.readLine();
           // lectureTriangles(ligne);
        }
        
        
        
        fichier.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
    }

    /**
     * Permet de créer un nouveau point à partir de la ligne lue et de l'ajouter à la liste
     * @param ligne 
     */
    private void lecturePoints(String ligne) {
        
        String delimiteurs = " ,;";
        
        StringTokenizer tokenizer = new StringTokenizer(ligne,delimiteurs);

            double x;
            double y;
            double z;
            
            x=Double.parseDouble(tokenizer.nextToken());
            y=Double.parseDouble(tokenizer.nextToken());
            z=Double.parseDouble(tokenizer.nextToken());
            
        Point3D p= new Point3D(x,y,z);
        
        listePoint.add(p);
        
    }

    /**
     * Permet de créer un nouveau triangle à partir de la ligne lue et de l'ajouter à la liste
     * @param ligne 
     */
    
    /**
    private void lectureTriangles(String ligne) {
        
        String delimiteurs = " ,;  \t";
        
        StringTokenizer tokenizer = new StringTokenizer(ligne,delimiteurs);

            int a;
            int b;
            int c;
            
            a=Integer.parseInt(tokenizer.nextToken());
            b=Integer.parseInt(tokenizer.nextToken());
            c=Integer.parseInt(tokenizer.nextToken());
            
            Point3D pointa = listePoint.get(a);
            Point3D pointb = listePoint.get(b);
            Point3D pointc = listePoint.get(c);
            Triangle t = new Triangle();
            if(Vecteur.distAngle((new Vecteur(pointa, pointb)).calculAngle(),(new Vecteur(pointa, pointc)).calculAngle())<Math.PI){
            
            t.setPoint1(pointa);
            t.setPoint2(pointb);
            t.setPoint3(pointc);
            
            }else{
                
            t.setPoint1(pointa);
            t.setPoint2(pointc);
            t.setPoint3(pointb);
            
            
            } 
            t.setSegment1(null);
            t.setSegment2(null);
            t.setSegment3(null); 
            listeTriangle.add(t); 
    }
      */ 
}
