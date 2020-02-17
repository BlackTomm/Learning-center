package spring.bucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.bucks.model.Coffee;


public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
}
