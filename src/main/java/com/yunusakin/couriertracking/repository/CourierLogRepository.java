package com.yunusakin.couriertracking.repository;

import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.model.CourierLog;
import com.yunusakin.couriertracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierLogRepository extends JpaRepository<CourierLog, Long> {
    List<CourierLog> findByCourierIdOrderByTimeAsc(Long courierId);
    List<CourierLog> findByCourierIdAndTimeAfter(Long courierId, LocalDateTime time);
    List<CourierLog> findByCourierIdAndStoreIdAndTimeAfterOrderByTimeDesc(Long courierId, Long storeId, LocalDateTime time);

    Courier findLatestByCourierAndStore(Courier courier, Store store);
}
