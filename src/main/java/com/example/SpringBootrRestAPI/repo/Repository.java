package com.example.SpringBootrRestAPI.repo;

import com.example.SpringBootrRestAPI.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Customer,Integer> {

}
