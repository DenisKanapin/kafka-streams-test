package debezium.repository.pg;

import debezium.model.pq.UserInteractionsWithSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterRepository extends JpaRepository<UserInteractionsWithSite, Long> {
}
