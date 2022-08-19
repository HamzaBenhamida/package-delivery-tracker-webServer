package cmpt213.assignment4.packagedeliveries.webappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class WebappserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappserverApplication.class, args);
	}

}
