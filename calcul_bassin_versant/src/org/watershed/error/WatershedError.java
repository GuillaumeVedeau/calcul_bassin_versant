/**
 * Watershed is a library dedicated to the processing of watershed in a 2.5
 * triangulation of Delaunay
 *
 * This library is developed at Ecole Centrales de Nantes as part of a practical
 * project.
 *
 * Watershed is a free software: you can redistribute it and/or modify it.
 */
package org.watershed.error;

/**
 * The exception that is used to describe the kind of errors that can happen
 * when building a Watershed or its elements in this library.
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

    public static final int WATERSHED_ERROR_MISC = 101;
    /**
     * Failed at generating a WPoint due to incorrect coordinates
     */
    public static final int WATERSHED_ERROR_ERROR_POINT_XYZ = 200;

    public static final int WATERSHED_ERROR_POINT_NOT_FOUND = 201;

    public static final int WATERSHED_ERROR_TRIANGLE_INCORRECT = 202;

    public static final int WATERSHED_ERROR_PROJECTION_ERROR = 300;

    public static final int WATERSHED_ERROR_ANGLE = 301;

    public static final int WATERSHED_ERROR_INTERNAL_ERROR = 999;

    private String message = "";

    /**
     * Default constructor, the associated message is DELAUNAY_INTERNAL_ERROR
     */
    public WatershedError() {
        super();
        code = WATERSHED_ERROR_INTERNAL_ERROR;
    }

    /**
     * WatershedError instanciated with a custom message. The inner error code
     * is WATERSHED_EROR_MISC
     *
     * @param s
     */
    public WatershedError(String s) {
        super(s);
        message = s;
        code = WATERSHED_ERROR_MISC;
    }

    /**
     * WatershedError created with the wanted error code.
     *
     * @param errorCode
     */
    public WatershedError(int errorCode) {
        super();
        code = errorCode;
    }

    /**
     * WatershedError created with both a custom message and a given error code.
     *
     * @param errorCode
     * @param s
     */
    public WatershedError(int errorCode, String s) {
        super();
        message = s;
        code = errorCode;
    }

    /**
     *
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public final String getMessage() {
        String ret;
        switch (code) {
            case WATERSHED_ERROR_NO_ERROR:
                ret = "no error";
                break;

            case WATERSHED_ERROR_MISC:
                ret = "no error";
                break;
            case WATERSHED_ERROR_INTERNAL_ERROR:
                ret = "internal errorr";
                break;
            case WATERSHED_ERROR_ERROR_POINT_XYZ:
                ret = "internal errorr";
                break;

            case WATERSHED_ERROR_POINT_NOT_FOUND:
                ret = "WPoint is not part of the triangulation";
                break;
            case WATERSHED_ERROR_TRIANGLE_INCORRECT:
                ret = "Wtriangle are missing Wpoint in the initialisation";
                break;
            case WATERSHED_ERROR_PROJECTION_ERROR:
                ret = "Parallel line and vector";
                break;
            case WATERSHED_ERROR_ANGLE:
                ret = "Invalid Vector";
                break;

            default:
                return message;

        }
        if (message.isEmpty()) {
            return ret;
        } else {
            return (ret + ", " + message);
        }
    }
}
