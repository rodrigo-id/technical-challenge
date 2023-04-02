package cl.meli.technicalchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TechnicalChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalChallengeApplication.class, args);
	}

}
