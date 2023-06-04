package com.yunusakin.couriertracking.util;

public class GeoUtil {
    /**
     * This method uses The Haversine formula to calculate the distance between two locations on Earth.
     * Haversine Formula :
     * a = sin²(Δφ/2) + cos(φ₁) * cos(φ₂) * sin²(Δλ/2)
     * c = 2 * atan2(√a, √(1-a))
     * d = R * c
     *
     * Explanation *
     *
     * φ₁ and φ₂ are the latitudes of the two points.
     * Δφ is the difference between the latitudes.
     * Δλ is the difference between the longitudes.
     * R is the radius of the Earth (approximately 6,371 kilometers).
     * a, c, and d are intermediate variables used in the calculation.
     * d is the final distance between the two points.
     * /

     /**
     * it returns distance between locations in meters
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
