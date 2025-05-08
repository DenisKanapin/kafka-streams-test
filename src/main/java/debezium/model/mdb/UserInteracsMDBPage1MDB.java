package debezium.model.mdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Document("userInteracs_page1")
@Data
@Builder
public class UserInteracsMDBPage1MDB implements UserInteracsMDB {

    @MongoId
    private Long id;

    @Field
    private boolean page1;
    @Field
    private boolean page1_button;
}
