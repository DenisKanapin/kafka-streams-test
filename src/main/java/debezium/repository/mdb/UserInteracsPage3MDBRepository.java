package debezium.repository.mdb;

import debezium.model.mdb.UserInteracsMDBPage3MDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInteracsPage3MDBRepository extends MongoRepository<UserInteracsMDBPage3MDB, Long> {
}
