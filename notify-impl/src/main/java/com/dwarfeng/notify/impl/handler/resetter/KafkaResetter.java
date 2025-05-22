package com.dwarfeng.notify.impl.handler.resetter;

import com.dwarfeng.notify.sdk.handler.resetter.AbstractResetter;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * 使用 CRON 表达式定时重置的重置器。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
@Component
public class KafkaResetter extends AbstractResetter implements ConsumerSeekAware {

    public static final String RESET_IDENTIFIER_ROUTE = "route";
    public static final String RESET_IDENTIFIER_DISPATCH = "dispatch";
    public static final String RESET_IDENTIFIER_SEND = "send";

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaResetter.class);

    private final KafkaListenerEndpointRegistry registry;

    @Value("${resetter.kafka.listener_id}")
    private String listenerId;

    private final Lock lock = new ReentrantLock();

    public KafkaResetter(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void doStart() throws Exception {
        lock.lock();
        try {
            LOGGER.info("Kafka resetter 开启...");
            MessageListenerContainer listenerContainer = registry.getListenerContainer(listenerId);
            if (Objects.isNull(listenerContainer)) {
                throw new HandlerException("找不到 kafka listener container " + listenerId);
            }
            // 判断监听容器是否启动，未启动则将其启动。
            if (!listenerContainer.isRunning()) {
                listenerContainer.start();
            }
            // 判断监听容器是否复位，未复位则将其复位。
            if (listenerContainer.isPauseRequested()) {
                listenerContainer.resume();
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void doStop() throws Exception {
        lock.lock();
        try {
            LOGGER.info("Kafka resetter 停止...");
            MessageListenerContainer listenerContainer = registry.getListenerContainer(listenerId);
            if (Objects.isNull(listenerContainer)) {
                throw new HandlerException("找不到 kafka listener container " + listenerId);
            }
            // 判断监听容器是否暂停，未暂停则将其暂停。
            if (!listenerContainer.isPauseRequested()) {
                listenerContainer.pause();
            }
            // 判断监听容器是否停止，未停止则将其停止。
            if (listenerContainer.isRunning()) {
                listenerContainer.stop();
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    @KafkaListener(
            id = "${resetter.kafka.listener_id}",
            containerFactory = "kafkaResetter.kafkaListenerContainerFactory",
            topics = "${resetter.kafka.topic}"
    )
    public void handleDataInfo(
            List<ConsumerRecord<String, String>> consumerRecords, Acknowledgment ack
    ) {
        lock.lock();
        try {
            Set<String> valueSet = consumerRecords.stream().map(ConsumerRecord::value).collect(Collectors.toSet());
            if (valueSet.contains(RESET_IDENTIFIER_ROUTE)) {
                try {
                    LOGGER.info("接收到路由重置消息, 正在重置路由...");
                    context.resetRoute();
                } catch (Exception e) {
                    LOGGER.warn("重置路由时发生异常, 路由将不会重置, 异常信息如下: ", e);
                }
            }
            if (valueSet.contains(RESET_IDENTIFIER_DISPATCH)) {
                try {
                    LOGGER.info("接收到调度重置消息, 正在重置调度...");
                    context.resetDispatch();
                } catch (Exception e) {
                    LOGGER.warn("重置调度时发生异常, 调度将不会重置, 异常信息如下: ", e);
                }
            }
            if (valueSet.contains(RESET_IDENTIFIER_SEND)) {
                try {
                    LOGGER.info("接收到发送重置消息, 正在重置发送...");
                    context.resetSend();
                } catch (Exception e) {
                    LOGGER.warn("重置发送时发生异常, 发送将不会重置, 异常信息如下: ", e);
                }
            }
            ack.acknowledge();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        LOGGER.info("将 id 为 {} 的监听器的 offset 移动至最后, 避免处理过期的消息...", listenerId);
        callback.seekToEnd(assignments.keySet());
    }

    @Override
    public String toString() {
        return "KafkaResetter{" +
                "registry=" + registry +
                ", listenerId='" + listenerId + '\'' +
                '}';
    }

    @Configuration("kafkaResetter.kafkaConfiguration")
    @EnableKafka
    public static class KafkaConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfiguration.class);

        @Value("${resetter.kafka.bootstrap_servers}")
        private String consumerBootstrapServers;
        @Value("${resetter.kafka.session_timeout_ms}")
        private int sessionTimeoutMs;
        @Value("${resetter.kafka.auto_offset_reset}")
        private String autoOffsetReset;
        @Value("${resetter.kafka.concurrency}")
        private int concurrency;
        @Value("${resetter.kafka.poll_timeout}")
        private int pollTimeout;
        @Value("${resetter.kafka.max_poll_records}")
        private int maxPollRecords;
        @Value("${resetter.kafka.max_poll_interval_ms}")
        private int maxPollIntervalMs;

        @Bean("kafkaResetter.consumerProperties")
        public Map<String, Object> consumerProperties() {
            LOGGER.info("配置Kafka消费者属性...");
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootstrapServers);
            // 本实例使用ack手动提交，因此禁止自动提交的功能。
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMs);
            // 不需要设置 GROUP_ID，因为侦听器 ID 会覆盖 GROUP_ID，因此不需要如下代码：
            // props.put(ConsumerConfig.GROUP_ID_CONFIG, xxxGroup);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
            props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollIntervalMs);
            LOGGER.debug("Kafka消费者属性配置完成...");
            return props;
        }

        @Bean("kafkaResetter.consumerFactory")
        public ConsumerFactory<String, String> consumerFactory() {
            LOGGER.info("配置Kafka消费者工厂...");
            Map<String, Object> properties = consumerProperties();
            DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(properties);
            factory.setKeyDeserializer(new StringDeserializer());
            factory.setValueDeserializer(new StringDeserializer());
            LOGGER.debug("Kafka消费者工厂配置完成");
            return factory;
        }

        @Bean("kafkaResetter.kafkaListenerContainerFactory")
        public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
        kafkaListenerContainerFactory() {
            LOGGER.info("配置Kafka侦听容器工厂...");
            ConsumerFactory<String, String> consumerFactory = consumerFactory();
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            factory.setConcurrency(concurrency);
            factory.getContainerProperties().setPollTimeout(pollTimeout);
            // Kafka 监听容器通过框架对开启和关闭进行托管，因此在启动时不自动开启。
            factory.setAutoStartup(false);
            // 监听器启用批量监听模式。
            factory.setBatchListener(true);
            // 配置 ACK 模式为手动立即提交。
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
            LOGGER.info("配置Kafka侦听容器工厂...");
            return factory;
        }
    }
}
