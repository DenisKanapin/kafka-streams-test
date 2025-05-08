package debezium.repository.mdb;

import debezium.model.mdb.UserInteracsMDBPage2MDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInteracsPage2MDBRepository extends MongoRepository<UserInteracsMDBPage2MDB, Long> {
}
