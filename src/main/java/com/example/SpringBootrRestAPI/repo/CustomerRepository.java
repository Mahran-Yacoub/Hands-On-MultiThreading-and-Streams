package com.example.SpringBootrRestAPI.repo;

import com.example.SpringBootrRestAPI.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {

}
