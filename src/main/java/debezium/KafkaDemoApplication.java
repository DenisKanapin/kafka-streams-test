package debezium;

import debezium.repository.pg.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= ProductRepository.class)
public class KafkaDemoApplication {

    public static void main(String[] args) {
        log.info("Application started");
        SpringApplication.run(KafkaDemoApplication.class, args);
        log.info("Application stopped");
    }

}
