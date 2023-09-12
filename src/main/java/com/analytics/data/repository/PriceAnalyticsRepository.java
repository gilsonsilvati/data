package com.analytics.data.repository;

import com.analytics.data.entity.PriceAnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceAnalyticsRepository extends JpaRepository<PriceAnalyticsEntity, Long> {

    Optional<PriceAnalyticsEntity> findByPrice(Double price);
}
