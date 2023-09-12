package com.analytics.data.repository;

import com.analytics.data.entity.ModelAnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelAnalyticsRepository extends JpaRepository<ModelAnalyticsEntity, Long> {

    Optional<ModelAnalyticsEntity> findByModel(String model);
}
