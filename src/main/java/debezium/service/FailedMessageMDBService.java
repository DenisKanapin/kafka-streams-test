package debezium.service;

import debezium.model.mdb.DeadProductMDB;
import debezium.repository.mdb.DeadProductMDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import debezium.avro.AvroProduct;

import java.math.BigDecimal;

@Service
public class FailedMessageMDBService {

    @Autowired
    private DeadProductMDBRepository deadProductMDBRepository;

    @Transactional("transactionManagerMongoDB")
    public void deadLetterMDBMessageSender(Long key, AvroProduct message) {
        DeadProductMDB dp = DeadProductMDB.builder()
                .id(message.getProductId())
                .offset(String.valueOf(key))
                .price(BigDecimal.valueOf(Double.valueOf(message.getPrice().toString())))
                .name(message.getName().toString())
                .description(message.getDescription().toString())
                .build();

        deadProductMDBRepository.save(dp);

        if (Double.valueOf(message.getPrice().toString()) == 90) {
            throw new IllegalStateException("Bad price");
        }
    }

}
