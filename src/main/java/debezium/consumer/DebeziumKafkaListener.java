package debezium.consumer;

import debezium.avro.AvroUserInters;
import debezium.service.MongoDBService;
import debezium.service.UserInteracsProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import debezium.avro.AvroProduct;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@DependsOn("kafkaListenerConfigurer")
public class DebeziumKafkaListener {

    private Semaphore semaphore = new Semaphore(1, true);

    private CyclicBarrier barrier = new CyclicBarrier(3);
    private CountDownLatch cdl = new CountDownLatch(3);

    @Autowired
    private MongoDBService mongoDBService;

    @Autowired
    private UserInteracsProcessorService userInteracsProcessorService;

    @KafkaListener(topics = "debezium.public.products",
            concurrency = "1",
            groupId = "${spring.application.name}-products",
            batch = "true",
            containerPostProcessor = "customizeMessageListenerContainer",
            properties = {
                    ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=10",
                    ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG + "=100000",
                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG + "=false"})
    public void consumerDebeziumRecords(AvroProduct record,
                                        //Acknowledgment ack,
                                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition) throws InterruptedException {
        log.info("=START=====================================================================================");
        log.info("Consuming record from kafka. Key {}. Record {}. Partition: {}. Thread: {}",
                key,
                record,
                partition,
                Thread.currentThread().getName());
        log.info("Method with transaction started Thread {}", Thread.currentThread().getName());

        if (Double.valueOf(record.getPrice().toString()) == 90) {
            throw new IllegalStateException("record price is less than zero");
        }

        mongoDBService.pushToMongoDB(record);
        log.info("Method with transaction ended Thread {}", Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(5);
    }


    @KafkaListener(topics = "test.public.deadproducts",
            concurrency = "1",
            groupId = "${spring.application.name}-deadProducts",
            containerPostProcessor = "failedMessageListenerContainer",
            properties = {
                    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG + "=org.apache.kafka.common.serialization.LongDeserializer",
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG + "=org.apache.kafka.common.serialization.StringDeserializer",
                    ConsumerConfig.ISOLATION_LEVEL_CONFIG + "=read_committed"
            })
    public void deadMessageListener(String record,
                                    //Acknowledgment ack,
                                    @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition) throws InterruptedException {
        log.info("=START==deadproducts===================================================================================");

        log.info("Method with transaction started Thread {}", Thread.currentThread().getName());
        /*bin/kafka-console-consumer.sh --topic test.public.deadproducts
                                        --from-beginning
                                        --bootstrap-server localhost:9092
                                        --property "print.key=true"
                                        --property "key.separator=-"
                                        --key-deserializer org.apache.kafka.common.serialization.LongDeserializer*/

    }


    @KafkaListener(topics = "debezium.public.user_interactions_with_site",
            concurrency = "3",
            groupId = "${spring.application.name}-userInteracs",
            containerFactory = "kafkaListenerContainerFactoryUserInters",
            containerPostProcessor = "userInteracsMessageListenerContainer",
            batch = "true",
            properties = {
                    ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=1000",
                    ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG + "=100000",
                    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG + "=false",
                    ConsumerConfig.ISOLATION_LEVEL_CONFIG + "=read_committed"})
    public void userInteractionsWithSiteListener(List<AvroUserInters> userIntersList/*,
                                                 Acknowledgment ack*/) throws BrokenBarrierException {
        System.out.println("New records arrived with Thread: " + Thread.currentThread().getName() + " NOR: " + userIntersList.size());
        try {
            cdl.countDown();
            cdl.await();// to test fairness of threads arrived and release of lock
            semaphore.acquire();
            System.out.println("Permit was aquired by thread - " + Thread.currentThread().getName());
            userInteracsProcessorService.processUserInteracs(userIntersList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finally block");
            semaphore.release();
            System.out.println("Permit was released by thread - " + Thread.currentThread().getName());
        }
        System.out.println("Semaphore Released. Records completed with Thread: " + Thread.currentThread().getName());

    }
}
