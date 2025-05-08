package test.debezium.service;


import debezium.avro.AvroProduct;
import debezium.model.pq.Product;
import debezium.service.UserInteracsProcessorService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class RecordsProcessorServiceTest {

    @Test
    public void test_processPSQLProducts() {

        UserInteracsProcessorService processor = new UserInteracsProcessorService();

        List<AvroProduct> products = new ArrayList<AvroProduct>();
        for(int i = 0; i < 10_000; i++) {
            AvroProduct product = new AvroProduct();
            product.setProductId(i);
            product.setDescription("Test description " + i);
            product.setName("Test Name " + i);
            product.setPrice(String.valueOf(123 + i));
            products.add(product);
        }

        List<Product> psqlProducts = processor.processPSQLProducts(products);

        assertEquals(psqlProducts.size(), 10_000);
    }
}
