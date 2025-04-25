package debezium.service;

import debezium.model.mdb.ProductMDB;
import debezium.model.mdb.UserMDB;
import debezium.repository.mdb.ProductMDBRepository;
import debezium.repository.mdb.UserMDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import debezium.avro.AvroProduct;

import java.math.BigDecimal;

@Slf4j
@Service
public class MongoDBService {

    @Autowired
    private ProductMDBRepository productMDBRepository;
    @Autowired
    private UserMDBRepository userMDBRepository;

    @Transactional(transactionManager = "transactionManagerMongoDB")
    public void pushToMongoDB(AvroProduct record) {
        log.warn("Start to transform data for push to MongoDB with thread {}", Thread.currentThread().getName());
        ProductMDB newProduct = ProductMDB.builder()
                .id(record.getProductId())
                .name(record.getName().toString())
                .price(BigDecimal.valueOf(Double.valueOf(record.getPrice().toString())))
                .description(record.getDescription().toString())
                .build();
        productMDBRepository.save(newProduct);

        UserMDB newUser = UserMDB.builder()
                .id(record.getProductId())
                .name(record.getName().toString())
                .email(record.getName().toString() + "@mdbtest.com")
                .build();
        userMDBRepository.save(newUser);

        log.warn("New data pushed to MongoDB with thread {}", Thread.currentThread().getName());
        log.info("=END=====================================================================================");
    }
}
