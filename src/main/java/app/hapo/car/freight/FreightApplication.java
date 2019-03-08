package app.hapo.car.freight;

import app.hapo.car.freight.common.file.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class FreightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreightApplication.class, args);
	}

}