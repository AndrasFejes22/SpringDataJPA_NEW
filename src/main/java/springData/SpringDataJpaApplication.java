package springData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository repository) {
		return (args) -> {
			//insertAnUser(repository);
			//System.out.println(repository.findAll());
			//System.out.println("************USERNAME************");
			//System.out.println(repository.findUserByusernameContaining("p"));
			//System.out.println("************DELETE************");
			//deleteAnUser(repository, 22L);
			//System.out.println("************UPDATE************");
			//updateAnUser(repository, 20L);
			System.out.println("************DELETEALLBYNAME************");
			deleteAnUserByName(repository, "GIZI");

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

