/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.watershed.error;

/**
 * The exception that is used to describe the kind of errors that can happen
 * when building or using a ConstrainedMesh or its elements in this library.
 *
 *
 * @author Guillaume Vedeau
 * @author Antoine Rigoureau
 */
public class WatershedError extends Exception {

    // error code saving 
    private int code;
    // error codes 
    /**
     * An error has been thrown, but it shouldn't happen 5
     */
    public static final int WATERSHED_ERROR_NO_ERROR = 0;

    /**
     * Failed at generating a WPoint due to incorrect coordinates
     */
    public static final int DELAUNAY_ERROR_ERROR_POINT_XYZ = 1;

    /**
     * Creates a new instance of <code>WatershedError</code> without detail
     * message.
     */
    public WatershedError() {
    }

    /**
     * Constructs an instance of <code>WatershedError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public WatershedError(String msg) {
        super(msg);
    }
    
    
    /**
     * 
     * @return 
     */
    @Override 
         public final String getMessage() { 
                String ret; 
                 switch (code) { 
                         case WATERSHED_ERROR_NO_ERROR: 
                                 ret = "no error"; 
                                 break; 

}
