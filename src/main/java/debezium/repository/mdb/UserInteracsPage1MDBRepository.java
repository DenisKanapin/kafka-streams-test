package debezium.repository.mdb;

import debezium.model.mdb.UserInteracsMDBPage1MDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInteracsPage1MDBRepository extends MongoRepository<UserInteracsMDBPage1MDB, Long> {
}
