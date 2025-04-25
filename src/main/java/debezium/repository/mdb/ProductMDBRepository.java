package debezium.repository.mdb;

import debezium.model.mdb.ProductMDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMDBRepository extends MongoRepository<ProductMDB, Long> {
}
