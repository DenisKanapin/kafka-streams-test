package debezium.runner;

import debezium.model.pq.Product;
import debezium.repository.pg.ProductRepository;
import debezium.service.FailedMessageKafkaService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import debezium.avro.AvroProduct;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ProductApplicationRunner implements ApplicationRunner {

    @Autowired
    private LinkedBlockingQueue<ConsumerRecord<String, AvroProduct>> failedConsumerRecordsBlockingQueue;

    @Autowired
    private FailedMessageKafkaService failedMessagesService;

    @Autowired
    ProductRepository productRepository;

    private Random random = new Random();

    @Override
    public void run(ApplicationArguments args) {
        final Thread messagePGDBProducer = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    Product product = generateRandomProduct();
                    Product savedProduct = productRepository.save(product);
                    log.info("Successfully saved product: {}", savedProduct);

                    TimeUnit.SECONDS.sleep(5);
                }
            }
        };
        messagePGDBProducer.start();


        final Thread failedConsumersProcessor = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    ConsumerRecord<String, AvroProduct> failedRecord = failedConsumerRecordsBlockingQueue.take();
                    failedMessagesService.sendDeadMessage(failedRecord);
                }
            }
        };

        failedConsumersProcessor.start();

    }


    private Product generateRandomProduct() {
        Product product = new Product();
        double randomDouble = random.nextDouble();
        product.setName("Product " + randomDouble);
        product.setDescription("Product Description " + randomDouble);
//        product.setPrice(BigDecimal.valueOf(90));
        product.setPrice(BigDecimal.valueOf(randomDouble));

        return product;
    }
}
