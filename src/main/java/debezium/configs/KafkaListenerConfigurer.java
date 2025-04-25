package debezium.configs;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerPostProcessor;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.adapter.DefaultBatchToRecordAdapter;
import debezium.avro.AvroProduct;

import java.util.concurrent.LinkedBlockingQueue;

@Configuration("kafkaListenerConfigurer")
@DependsOn("kafkaListenerContainerFactory")
public class KafkaListenerConfigurer {

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    @Autowired
    @Lazy
    private LinkedBlockingQueue<ConsumerRecord<String, AvroProduct>> failedConsumerRecordsBlockingQueue;

    @PostConstruct
    public void init() {
        kafkaListenerContainerFactory.setBatchToRecordAdapter(new DefaultBatchToRecordAdapter<>((record, ex) -> {
            failedConsumerRecordsBlockingQueue.add((ConsumerRecord<String, AvroProduct>)record);
        }));
    }

    @Bean
    public LinkedBlockingQueue<ConsumerRecord<String, AvroProduct>> failedConsumerRecordsBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public ContainerPostProcessor<String, String, ConcurrentMessageListenerContainer<String, String>> customizeMessageListenerContainer() {
        return new ContainerPostProcessor<String, String, ConcurrentMessageListenerContainer<String, String>>() {

            @Override
            public void postProcess(ConcurrentMessageListenerContainer container) {
                container.getContainerProperties().setIdleBetweenPolls(50000);
                container.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            }
        };
    }

    @Bean
    public ContainerPostProcessor<Long, String, ConcurrentMessageListenerContainer<Long, String>> failedMessageListenerContainer() {
        return new ContainerPostProcessor<Long, String, ConcurrentMessageListenerContainer<Long, String>>() {

            @Override
            public void postProcess(ConcurrentMessageListenerContainer container) {
                container.getContainerProperties().setIdleBetweenPolls(20000);
                container.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
            }
        };
    }
}
