package com.analytics.data.service.impl;

import com.analytics.data.dto.CarDTO;
import com.analytics.data.entity.BrandAnalyticsEntity;
import com.analytics.data.entity.CarModelAnalyticsEntity;
import com.analytics.data.entity.CarModelPriceEntity;
import com.analytics.data.repository.BrandAnalyticsRepository;
import com.analytics.data.repository.CarModelAnalyticsRepository;
import com.analytics.data.repository.CarPriceAnalyticsRepository;
import com.analytics.data.service.PostAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostAnalyticsServiceImpl implements PostAnalyticsService {

    @Autowired
    protected BrandAnalyticsRepository brandAnalyticsRepository;

    @Autowired
    protected CarModelAnalyticsRepository carModelAnalyticsRepository;

    @Autowired
    protected CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    @Override
    public void saveDataAnalytics(CarDTO carDTO) {
        saveBrandAnalytics(carDTO.getBrand());
        saveModelAnalytics(carDTO.getModel());
        saveModelPriceAnalytics(carDTO.getModel(), carDTO.getPrice());
    }

    private void saveBrandAnalytics(String brand) {

        brandAnalyticsRepository.findByBrand(brand).ifPresentOrElse(entity -> {
            entity.setPosts(entity.getPosts() + 1);
            brandAnalyticsRepository.save(entity);
        }, () -> {
            BrandAnalyticsEntity brandAnalyticsEntity = new BrandAnalyticsEntity();
            brandAnalyticsEntity.setBrand(brand);
            brandAnalyticsEntity.setPosts(1L);
            brandAnalyticsRepository.save(brandAnalyticsEntity);
        });
    }

    private void saveModelAnalytics(String model) {

        carModelAnalyticsRepository.findByModel(model).ifPresentOrElse(entity -> {
            entity.setPosts(entity.getPosts() + 1);
            carModelAnalyticsRepository.save(entity);
        }, () -> {
            CarModelAnalyticsEntity carModelAnalyticsEntity = new CarModelAnalyticsEntity();
            carModelAnalyticsEntity.setModel(model);
            carModelAnalyticsEntity.setPosts(1L);
            carModelAnalyticsRepository.save(carModelAnalyticsEntity);
        });
    }

    private void saveModelPriceAnalytics(String model, Double price) {
        CarModelPriceEntity carModelPriceEntity = new CarModelPriceEntity();

        carModelPriceEntity.setModel(model);
        carModelPriceEntity.setPrice(price);
        carPriceAnalyticsRepository.save(carModelPriceEntity);
    }
}
