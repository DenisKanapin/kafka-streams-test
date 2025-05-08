package debezium.model.mdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@Document("userInteracs_page2")
@Data
@Builder
public class UserInteracsMDBPage2MDB implements UserInteracsMDB {

    @MongoId
    private Long id;

    @Field
    private boolean page2;
    @Field
    private boolean page2_button;
}
