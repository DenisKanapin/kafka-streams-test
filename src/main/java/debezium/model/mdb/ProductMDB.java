package debezium.model.mdb;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@AllArgsConstructor
@Document("products")
@Data
@Builder
public class ProductMDB {

    @MongoId
    private Long id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private BigDecimal price;
}
