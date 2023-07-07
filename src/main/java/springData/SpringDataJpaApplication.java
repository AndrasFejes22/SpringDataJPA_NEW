package springData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springData.view.Customer;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(CustomerRepository  repository) {
		return (args) -> {
			//insertAnUser(repository);
			//System.out.println(repository.findAll());
			//System.out.println("************USERNAME************");
			//System.out.println(repository.findUserByusernameContaining("p"));
			//System.out.println("************DELETE************");
			//deleteAnUser(repository, 22L);
			//System.out.println("************UPDATE************");
			//updateAnUser(repository, 20L);
			//System.out.println("************DELETEALLBYNAME************");
			//deleteAnUserByName(repository, "GIZI");

			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));

// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(2L).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");

		};
	}

	public void insertAnUser(UserRepository repository) {
		User user = new User();
		user.setCreatedAt(ZonedDateTime.now());
		user.setUsername("JANI");
		user.setStatus(UserStatus.ACTIVE);
		repository.save(user);
	}

	public void deleteAnUser(UserRepository repository, Long Id) {
		Optional<User> user = repository.findById(Id);
		if(user.isPresent()){
			repository.delete(user.get());
			System.out.println("ID " + Id + " user deleted.");
		} else {
			System.out.println("nincs ilyen user!");
		}

	}

	public void deleteAnUserByName(UserRepository repository, String username) {
		List<User> userList = repository.findUserByusernameContaining(username);
		repository.deleteAll(userList);

	}

	public void updateAnUser(UserRepository repository, Long Id) {
		Optional<User> user = repository.findById(Id);
		if(user.isPresent()){
			User updatedUser = user.get();
			updatedUser.setUsername("AXL ROSE");
			repository.save(updatedUser);
			System.out.println("ID " + Id + " user updated.");
		} else {
			System.out.println("nincs ilyen user!");
		}

	}

}

