package dd.projects.ddshop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class DdshopApplication {
	public static void main(final String[] args) {
		SpringApplication.run(DdshopApplication.class, args);
	}
}
