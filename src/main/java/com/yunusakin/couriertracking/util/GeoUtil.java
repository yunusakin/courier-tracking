package com.yunusakin.couriertracking.util;

public class GeoUtil {
    /**
     * This method uses The Haversine formula to calculate the distance between two locations on Earth.
     *  it returns distance between locations in meters
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double EARTH_RADIUS = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c * 1000;

        return Math.abs(distance);
    }
}
