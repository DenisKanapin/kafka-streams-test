package debezium.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfigurer {

    @Bean
    public ProducerFactory<Long, String> producerFactory() {
        DefaultKafkaProducerFactory<Long, String> pf = new DefaultKafkaProducerFactory<>(producerConfigs());
        pf.setTransactionIdPrefix("deadLetter-tx-");
        TransactionIdSuffixStrategy ss = new DefaultTransactionIdSuffixStrategy(5);
        pf.setTransactionIdSuffixStrategy(ss);
        return pf;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1000);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "deadLetter-tx-id");
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public LoggingProducerListener<Long, String> kafkaProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean
    public KafkaTemplate<Long, String> kafkaTemplate(ProducerFactory<Long, String> producerFactory,
                                                     ProducerListener<Long, String> kafkaProducerListener,
                                                     ObjectProvider<RecordMessageConverter> messageConverter) {
        KafkaTemplate<Long, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setTransactionIdPrefix("deadLetter-tx-");
        return kafkaTemplate;
    }

    @Bean
    public KafkaTransactionManager<Long, String> kafkaTransactionManager(ProducerFactory<Long, String> producerFactory) {
        KafkaTransactionManager kafkaTransactionManager = new KafkaTransactionManager(producerFactory);
        kafkaTransactionManager.setTransactionIdPrefix("deadLetter-tx-");
        return kafkaTransactionManager;
    }

    @Bean
    NewTopic deadProductsTopic() {
        return TopicBuilder.name("test.public.deadproducts")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
