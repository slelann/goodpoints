package fr.sll.goodpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(final String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(final KidRepository repository) {
		return (args) -> {
			repository.deleteAll();
			// save a couple of Kids
			repository.save(new Kid("Titouan", 50));
			repository.save(new Kid("Maïwenn", 50));

			// fetch all kids
			log.info("Kids found with findAll():");
			log.info("-------------------------------");
			for (Kid kid : repository.findAll()) {
				log.info(kid.toString());
			}
			log.info("");


		};
	}

}
