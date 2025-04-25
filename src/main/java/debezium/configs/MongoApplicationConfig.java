package debezium.configs;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import debezium.repository.mdb.ProductMDBRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableMongoRepositories(basePackageClasses = {ProductMDBRepository.class})
@EnableTransactionManagement
public class MongoApplicationConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "quickstartdb";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Arrays.asList("debezium.repository.mdb");
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        MongoCredential credential = MongoCredential
                .createCredential("root", "admin", "root".toCharArray());
        builder.credential(credential);
    }

    @Bean
    MongoTransactionManager transactionManagerMongoDB(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    @Primary
    JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean dbFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(dbFactory.getObject());
        return transactionManager;
    }
}
