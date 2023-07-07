package springData;

import org.springframework.data.jpa.repository.JpaRepository;
import springData.view.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}