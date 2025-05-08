package debezium.model.mdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@Document("userInteracs_page3")
@Data
@Builder
public class UserInteracsMDBPage3MDB implements UserInteracsMDB {

    @MongoId
    private Long id;

    @Field
    private boolean page3;
    @Field
    private boolean page3_button;
}
