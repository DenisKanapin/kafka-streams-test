package debezium.runner;

import debezium.model.pq.Product;
import debezium.model.pq.UserInteractionsWithSite;
import debezium.repository.pg.ProductRepository;
import debezium.repository.pg.UserInterRepository;
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
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    UserInterRepository userInterRepository;

    private Random random = new Random();
    private Random userRandom = new Random();

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

        final Thread messageUserIntersToPGSQL = new Thread() {

            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    Thread.sleep(5000);
                    List<UserInteractionsWithSite> userInters = generateUserInterations();
                    userInterRepository.saveAll(userInters);
                }
            }
        };
        messageUserIntersToPGSQL.start();

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

    private List<UserInteractionsWithSite> generateUserInterations() {
        final List<UserInteractionsWithSite> users = new ArrayList<>();
        for(int i = 0; i < 10_000; i++) {
            UserInteractionsWithSite userInteractionsWithSite = new UserInteractionsWithSite();
            int r = userRandom.nextInt( 10);
            if(r <= 3) {
                userInteractionsWithSite.setPage1(true);
                userInteractionsWithSite.setPage1_button(true);
            } else if(r > 3 && r <= 6) {
                userInteractionsWithSite.setPage2(true);
                userInteractionsWithSite.setPage2_button(true);
            } else {
                userInteractionsWithSite.setPage3(true);
                userInteractionsWithSite.setPage3_button(true);
            }
            users.add(userInteractionsWithSite);
        }
        return users;
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
