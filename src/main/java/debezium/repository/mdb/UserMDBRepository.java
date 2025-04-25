package debezium.repository.mdb;

import debezium.model.mdb.UserMDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMDBRepository extends MongoRepository<UserMDB, Long> {
}
