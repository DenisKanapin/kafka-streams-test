package debezium.repository.mdb;

import debezium.model.mdb.DeadProductMDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadProductMDBRepository extends MongoRepository<DeadProductMDB, Long> {
}
