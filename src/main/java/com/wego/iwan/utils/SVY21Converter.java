package com.wego.iwan.utils;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;

public class SVY21Converter {

    private static final CRSFactory crsFactory = new CRSFactory();
    private static final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

    private static final CoordinateReferenceSystem svy21CRS = crsFactory.createFromName("EPSG:3414");
    private static final CoordinateReferenceSystem wgs84CRS = crsFactory.createFromName("EPSG:4326");

    /**
     * Converts SVY21 coordinates to Latitude/Longitude (WGS84).
     *
     * @param easting  SVY21 Easting value
     * @param northing SVY21 Northing value
     * @return Array containing [latitude, longitude]
     */
    public static double[] svy21ToLatLon(double easting, double northing) {
        CoordinateTransform transform = ctFactory.createTransform(svy21CRS, wgs84CRS);
        
        ProjCoordinate srcCoord = new ProjCoordinate(easting, northing);
        ProjCoordinate destCoord = new ProjCoordinate();
        
        transform.transform(srcCoord, destCoord);
        
        return new double[]{destCoord.y, destCoord.x};
    }

    /**
     * Converts Latitude/Longitude (WGS84) to SVY21 coordinates.
     *
     * @param latitude  Latitude value in degrees
     * @param longitude Longitude value in degrees
     * @return Array containing [easting, northing]
     */
    public static double[] latLonToSvy21(double latitude, double longitude) {
        CoordinateTransform transform = ctFactory.createTransform(wgs84CRS, svy21CRS);
        
        ProjCoordinate srcCoord = new ProjCoordinate(longitude, latitude);
        ProjCoordinate destCoord = new ProjCoordinate();
        
        transform.transform(srcCoord, destCoord);
        
        return new double[]{destCoord.x, destCoord.y};
    }

    public static void main(String[] args) {
 
        double easting = 28001.64;
        double northing = 38744.57;
        
        double[] latLon = svy21ToLatLon(easting, northing);
        System.out.printf("Lat: %.6f, Lon: %.6f\n", latLon[0], latLon[1]);

        double[] svy21 = latLonToSvy21(latLon[0], latLon[1]);
        System.out.printf("Easting: %.2f, Northing: %.2f\n", svy21[0], svy21[1]);
    }
}


