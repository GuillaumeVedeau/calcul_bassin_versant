/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Utilitaires;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geometry.WPoint;
import org.geometry.WTriangle;

/**
 *
 * @author Antoine
 */
public class EcritureWrl {
    
    private final  String source;
    private ArrayList<WTriangle> listeTriangle;
    private ArrayList<WPoint> listePoints;
    private int nombrePoints;
    private int nombreTriangles;
    

    /**
     * Constructor of EcritureWrl
     * @param s
     * @param listeTriangle 
     */
    public EcritureWrl(String s,ArrayList<WTriangle> listeTriangle,ArrayList<WPoint> listePoints){
        source=s;
        this.listeTriangle=listeTriangle;
        this.listePoints=listePoints;
        nombrePoints=-1;
        nombreTriangles=-1;
        ecriture();
    }

    public int getNombrePoints() {
        return nombrePoints;
    }

    public void setNombrePoints(int nombrePoints) {
        this.nombrePoints = nombrePoints;
    }

    public int getNombreTriangles() {
        return nombreTriangles;
    }

    public void setNombreTriangles(int nombreTriangles) {
        this.nombreTriangles = nombreTriangles;
    }

    
    
    public ArrayList<WTriangle> getListeTriangle() {
        return listeTriangle;
    }

    public void setListeTriangle(ArrayList<WTriangle> listeTriangle) {
        this.listeTriangle = listeTriangle;
    }
    
    
    
    /**
     * Add the triangles of listeTriangle in a file resultats.wrl. 
     */
    public void ecriture(){
        
        try{
        String ligne;
        
            BufferedReader fichier = new BufferedReader(new FileReader(source));
            BufferedWriter resultats = new BufferedWriter(new FileWriter("resultats.wrl"));
                
                while((ligne=fichier.readLine())!=null){
                   
                    
                    if(ligne.equals("] # end point")){
                        ecrirePoints(resultats);
                    }
                   
                    if(ligne.equals("] # end coordIndex")){
                        ecrireTriangles(resultats);
                    }
                    
                    if(ligne.equals("] # end colorIndex")){
                        ecrireCouleur(resultats);
                    }
                    
                    
                    nombreItems(ligne);
                    resultats.write(ligne);
                    resultats.newLine();
            }
                System.out.println(nombreTriangles);
                fichier.close();
                resultats.close();
                
              
                
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Add the new points to resultats.wrl
     * @param resultats 
     */
    private void ecrirePoints(BufferedWriter resultats) {
        int taille = this.getNombrePoints();
        
        for (WPoint p : listePoints) {
            try {
                taille++;
                resultats.write(" #Point "+taille);
                resultats.newLine();
                resultats.write(" "+p.getPosx()+" "+p.getPosy()+" "+(p.getPosz()+1));
                resultats.newLine();
                
            } catch (IOException ex) {
                Logger.getLogger(EcritureWrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    /**
     * Add the new triangles to resultats.wrl
     * @param resultats 
     */
    private void ecrireTriangles(BufferedWriter resultats) {
        int taille = this.getNombreTriangles();
        
        for (WTriangle t : listeTriangle) {
            try {
                taille++;
                resultats.write("#triangle "+taille);
                resultats.newLine();
                resultats.write((t.getPoint1()+1)+"\t"+(t.getPoint2()+1)+"\t"+(t.getPoint3()+1+"\t-1"));
                resultats.newLine();
            } catch (IOException ex) {
                Logger.getLogger(EcritureWrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
/**
 * Give a different colour to the watershed
 * @param resultats 
 */
    private void ecrireCouleur(BufferedWriter resultats) {
        int taille = this.getListeTriangle().size();
        
        for(int i=this.getNombreTriangles()/2+1;i<taille+this.getNombreTriangles()/2+1;i++){
            try {
                resultats.write("1 #triangle "+i);
                resultats.newLine();
            } catch (IOException ex) {
                Logger.getLogger(EcritureWrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Give the number of Points and Triangles 
     * @param s 
     */
    private void nombreItems(String s) {
       
        String delimiteurs = " ,;\t";
        Boolean b=true;
        
        StringTokenizer tokenizer = new StringTokenizer(s,delimiteurs);
        
        if(s.equals("] # end coordIndex")){
            b=false;
        }
        
        while(tokenizer.hasMoreTokens()){
            
            String mot = tokenizer.nextToken();
   
            if(mot.equals("#Point")){
                this.setNombrePoints(this.getNombrePoints()+1);
            }
            if(mot.equals("#triangle")&&b==true){
                this.setNombreTriangles(this.getNombreTriangles()+1);
            }
        }
    }
}
