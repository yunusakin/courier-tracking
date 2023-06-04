package com.yunusakin.couriertracking.service;

import com.yunusakin.couriertracking.constants.CourierTrackingConstants;
import com.yunusakin.couriertracking.controller.data.request.CourierInfo;
import com.yunusakin.couriertracking.controller.data.request.CourierLogRequest;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.model.CourierLog;
import com.yunusakin.couriertracking.model.Store;
import com.yunusakin.couriertracking.repository.CourierLogRepository;
import com.yunusakin.couriertracking.repository.CourierRepository;
import com.yunusakin.couriertracking.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TrackingServiceTest {
    @Mock
    private CourierRepository courierRepository;
    @Mock
    private CourierLogRepository courierLogRepository;
    @Mock
    private StoreRepository storeRepository;

    private TrackingService trackingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trackingService = new TrackingService(courierRepository, courierLogRepository, storeRepository);
    }

    @Test
    void logCourier_courierNotFound_throwsIllegalArgumentException() {
        CourierLogRequest logRequest = new CourierLogRequest();
        logRequest.setLatitude(12.345);
        logRequest.setLongitude(67.890);
        logRequest.setCourier(new CourierInfo());
        logRequest.getCourier().setCourierToken("token");

        when(courierRepository.findByToken("token")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> trackingService.logCourier(logRequest));
    }

    @Test
    void logCourier_nearestStoreWithinRadius_courierLogSaved() {
        CourierLogRequest logRequest = new CourierLogRequest();
        logRequest.setLatitude(12.345);
        logRequest.setLongitude(67.890);
        logRequest.setTime(LocalDateTime.now());
        logRequest.setCourier(new CourierInfo());
        logRequest.getCourier().setCourierToken("token");

        Courier courier = new Courier();
        courier.setId(1L);
        Store nearestStore = new Store();
        nearestStore.setId(1L);

        when(courierRepository.findByToken("token")).thenReturn(courier);
        when(storeRepository.findAll()).thenReturn(createStores());
        when(storeRepository.findById(1L)).thenReturn(Optional.of(nearestStore));
        when(courierLogRepository.findByCourierIdAndStoreIdAndTimeAfterOrderByTimeDesc(1L, 1L, logRequest.getTime().minus(CourierTrackingConstants.THRESHOLD)))
                .thenReturn(new ArrayList<>());

        trackingService.logCourier(logRequest);

        verify(courierLogRepository, times(1)).save(any(CourierLog.class));
    }

    @Test
    void getTotalTravelDistance_courierNotFound_throwsIllegalArgumentException() {
        when(courierRepository.findByToken("token")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> trackingService.getTotalTravelDistance("token"));
    }

    @Test
    void getTotalTravelDistance_courierLogsExist_calculatesTotalDistance() {
        Courier courier = new Courier();
        courier.setId(1L);

        List<CourierLog> courierLogs = createCourierLogs();

        when(courierRepository.findByToken("token")).thenReturn(courier);
        when(courierLogRepository.findByCourierIdOrderByTimeAsc(1L)).thenReturn(courierLogs);

        double expectedDistance = 1696433.8503457317; // Assuming the distance is 100.0 for testing purposes

        assertEquals(expectedDistance, trackingService.getTotalTravelDistance("token"));
    }

    private List<Store> createStores() {
        List<Store> stores = new ArrayList<>();

        Store store1 = new Store();
        store1.setId(1L);
        store1.setLatitude(12.345);
        store1.setLongitude(67.890);
        stores.add(store1);

        Store store2 = new Store();
        store2.setId(2L);
        store2.setLatitude(23.456);
        store2.setLongitude(78.901);
        stores.add(store2);

        return stores;
    }

    private List<CourierLog> createCourierLogs() {
        List<CourierLog> courierLogs = new ArrayList<>();

        CourierLog log1 = new CourierLog();
        log1.setId(1L);
        log1.setLatitude(12.345);
        log1.setLongitude(67.890);
        courierLogs.add(log1);

        CourierLog log2 = new CourierLog();
        log2.setId(2L);
        log2.setLatitude(23.456);
        log2.setLongitude(78.901);
        courierLogs.add(log2);

        return courierLogs;
    }
}


