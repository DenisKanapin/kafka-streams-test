package debezium.model.mdb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@AllArgsConstructor
@Document("DeadProducts")
@Data
@Builder
public class DeadProductMDB {

    @MongoId
    private Long id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private BigDecimal price;

    @Field
    private String offset;
}
