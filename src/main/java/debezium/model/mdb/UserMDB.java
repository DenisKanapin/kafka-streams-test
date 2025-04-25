package debezium.model.mdb;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@Document("user")
@Data
@Builder
public class UserMDB {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MongoId
    private Long id;

    @Field
    private String name;

    @Field
    private String email;
}
