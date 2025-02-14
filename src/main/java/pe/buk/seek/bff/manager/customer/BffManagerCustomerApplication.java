package pe.buk.seek.bff.manager.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:bff-manager-customer-1.0.properties")
public class BffManagerCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffManagerCustomerApplication.class, args);
	}

}
