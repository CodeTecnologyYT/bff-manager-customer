package pe.buk.seek.bff.manager.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableCaching
@PropertySource(value = "classpath:bff-manager-customer-1.0.properties")
@PropertySource(value = "classpath:redis.properties")
public class BffManagerCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffManagerCustomerApplication.class, args);
	}

}
