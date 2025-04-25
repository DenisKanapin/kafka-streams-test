package debezium.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import debezium.avro.AvroProduct;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class FailedMessageKafkaService {

    public static final String DEADLETTER_DEBEZIUM_PUBLIC_PRODUCTS = "test.public.deadproducts";

    @Lazy
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    private FailedMessageMDBService failedMessageMDBService;

    @SneakyThrows
    public void deadLetterKafkaMessageSender(Long key, AvroProduct message) {

        CompletableFuture<SendResult<Long, String>> cfResult = kafkaTemplate.send(DEADLETTER_DEBEZIUM_PUBLIC_PRODUCTS, 0, key, message.toString());

        SendResult<Long, String> result = cfResult.get(2, TimeUnit.SECONDS);

        if (Double.valueOf(message.getPrice().toString()) < 0) {
            throw new IllegalStateException("Bad price");
        }
    }

    @Transactional(value = "kafkaTransactionManager", propagation = Propagation.REQUIRED)
    public void sendDeadMessage(ConsumerRecord<String, AvroProduct> failedRecord) {
        deadLetterKafkaMessageSender(failedRecord.offset(), failedRecord.value());
        if (Double.valueOf(failedRecord.value().getPrice().toString()) == 60) {
            throw new IllegalStateException("Bad price");
        }

        failedMessageMDBService.deadLetterMDBMessageSender(failedRecord.offset(), failedRecord.value());
        if (Double.valueOf(failedRecord.value().getPrice().toString()) == 80) {
            throw new IllegalStateException("Bad price");
        }
    }

}
