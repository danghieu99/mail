package com.example.kafkalistener.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.Task;
import org.springframework.web.client.RestClient;

import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
public class KafkaListenerConfig {

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("BOOTSTRAP_SERVERS_CONFIG"));
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "45000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "mailtest-subscribergroup");

        return new KafkaConsumer<>(props);
    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        TaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//
//    }

    @Bean
    public RestClient getRestClient() {
        return RestClient.create();
    }

}