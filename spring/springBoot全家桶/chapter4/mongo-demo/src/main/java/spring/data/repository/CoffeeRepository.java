package spring.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.data.model.Coffee;

import java.util.List;

public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
