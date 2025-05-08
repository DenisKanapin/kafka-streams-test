package debezium.configs;

import debezium.avro.AvroUserInters;
import debezium.model.mdb.UserInteracsMDBPage1MDB;
import debezium.model.mdb.UserInteracsMDBPage2MDB;
import debezium.model.mdb.UserInteracsMDBPage3MDB;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerCustomizer;
import org.springframework.kafka.config.ContainerPostProcessor;
import org.springframework.kafka.core.ConsumerFactory;
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
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryUserInters;

    @Autowired
    @Lazy
    private LinkedBlockingQueue<ConsumerRecord<String, AvroProduct>> failedConsumerRecordsBlockingQueue;
    @Autowired
    @Lazy
    private LinkedBlockingQueue<ConsumerRecord<String, AvroUserInters>> failedUserIntersBlockingQueue; //Not used

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
    public LinkedBlockingQueue<ConsumerRecord<String, AvroUserInters>> failedUserIntersBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage1MDB>> userInteracsPage1MDBs() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage2MDB>> userInteracsPage2MDBs() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage3MDB>> userInteracsPage3MDBs() {
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
    public ContainerPostProcessor<String, String, ConcurrentMessageListenerContainer<String, String>> userInteracsMessageListenerContainer() {
        return new ContainerPostProcessor<String, String, ConcurrentMessageListenerContainer<String, String>>() {

            @Override
            public void postProcess(ConcurrentMessageListenerContainer container) {
                container.getContainerProperties().setIdleBetweenPolls(30000);
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

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactoryUserInters(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ObjectProvider<ContainerCustomizer<Object, Object, ConcurrentMessageListenerContainer<Object, Object>>> kafkaContainerCustomizer) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, (ConsumerFactory<Object, Object>) kafkaListenerContainerFactory.getConsumerFactory());
        kafkaContainerCustomizer.ifAvailable(factory::setContainerCustomizer);
        return factory;
    }
}
