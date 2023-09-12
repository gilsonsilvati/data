package com.analytics.data.message;

import com.analytics.data.dto.CarDTO;
import com.analytics.data.service.PostAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-topic", groupId = "analytics-posts-group")
    public void listening(@Payload CarDTO carPost) {
        LOG.info("ANALYTICS DATA - Received Car Post information: {}", carPost);

        postAnalyticsService.saveDataAnalytics(carPost);
    }
}