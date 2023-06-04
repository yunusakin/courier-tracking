package com.yunusakin.couriertracking.repository;

import com.yunusakin.couriertracking.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Courier findByToken(String token);
}
