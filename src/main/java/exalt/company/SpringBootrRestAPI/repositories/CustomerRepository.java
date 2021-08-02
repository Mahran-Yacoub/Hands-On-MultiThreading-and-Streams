package exalt.company.SpringBootrRestAPI.repositories;

import exalt.company.SpringBootrRestAPI.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {

}
