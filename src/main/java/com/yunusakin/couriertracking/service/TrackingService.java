package com.yunusakin.couriertracking.service;

import com.yunusakin.couriertracking.constants.CourierTrackingConstants;
import com.yunusakin.couriertracking.controller.data.request.CourierLogRequest;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.model.CourierLog;
import com.yunusakin.couriertracking.model.Store;
import com.yunusakin.couriertracking.repository.CourierLogRepository;
import com.yunusakin.couriertracking.repository.CourierRepository;
import com.yunusakin.couriertracking.repository.StoreRepository;
import com.yunusakin.couriertracking.util.GeoUtil;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {
    private final CourierRepository courierRepository;
    private final CourierLogRepository courierLogRepository;
    private final StoreRepository storeRepository;

    public TrackingService(CourierRepository courierRepository, CourierLogRepository courierLogRepository, StoreRepository storeRepository) {
        this.courierRepository = courierRepository;
        this.courierLogRepository = courierLogRepository;
        this.storeRepository = storeRepository;
    }

    public void logCourier(CourierLogRequest logRequest) {
        double latitude = logRequest.getLatitude();
        double longitude = logRequest.getLongitude();

        Courier courier = courierRepository.findByToken(logRequest.getCourier().getCourierToken());
        if (courier == null) {
            throw new IllegalArgumentException("Courier not found");
        }
        Store nearestStore = findNearestStore(latitude, longitude);

        if (nearestStore != null && isWithinStoreRadius(latitude, longitude, nearestStore)) {
            LocalDateTime currentTime = logRequest.getTime();
            if (!hasCourierEnteredStore(courier, nearestStore, currentTime)) {
                CourierLog courierLog = createCourierLog(courier, nearestStore, logRequest);
                courierLogRepository.save(courierLog);
            }
        }
    }

    private Store findNearestStore(double latitude, double longitude) {
        List<Store> stores = storeRepository.findAll();
        double minDistance = Double.MAX_VALUE;
        Store nearestStore = null;

        for (Store store : stores) {
            double distance = GeoUtil.calculateDistance(latitude, longitude, store.getLatitude(), store.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestStore = store;
            }
        }

        return nearestStore;
    }

    boolean isWithinStoreRadius(double latitude, double longitude, Store store) {
        double distance = GeoUtil.calculateDistance(latitude, longitude, store.getLatitude(), store.getLongitude());
        return distance <= CourierTrackingConstants.STORE_RADIUS;
    }

    boolean hasCourierEnteredStore(Courier courier, Store store, LocalDateTime currentTime) {
        LocalDateTime thresholdTime = currentTime.minus(CourierTrackingConstants.THRESHOLD);
        List<CourierLog> courierLogs = courierLogRepository.findByCourierIdAndStoreIdAndTimeAfterOrderByTimeDesc(courier.getId(), store.getId(), thresholdTime);

        return !courierLogs.isEmpty();
    }

    private CourierLog createCourierLog(Courier courier, Store store, CourierLogRequest logRequest) {
        CourierLog courierLog = new CourierLog();
        courierLog.setCourier(courier);
        courierLog.setTime(logRequest.getTime());
        courierLog.setLatitude(logRequest.getLatitude());
        courierLog.setLongitude(logRequest.getLongitude());
        courierLog.setStore(store);
        return courierLog;
    }

    public Double getTotalTravelDistance(String courierToken) {
        Courier courier = courierRepository.findByToken(courierToken);
        if (courier == null) {
            throw new IllegalArgumentException("Courier not found");
        }
        List<CourierLog> courierLogs = courierLogRepository.findByCourierIdOrderByTimeAsc(courier.getId());
        double totalDistance = 0.0;
        CourierLog previousLog = null;

        for (CourierLog currentLog : courierLogs) {
            if (previousLog != null) {
                double distance = GeoUtil.calculateDistance(previousLog.getLatitude(), previousLog.getLongitude(), currentLog.getLatitude(), currentLog.getLongitude());
                totalDistance += distance;
            }
            previousLog = currentLog;
        }

        return totalDistance;
    }
    private boolean isWithinReentryThreshold(LocalDateTime previousTime, LocalDateTime currentTime) {
        Duration duration = Duration.between(previousTime, currentTime);
        return duration.compareTo(CourierTrackingConstants.THRESHOLD) <= 0;
    }
}
