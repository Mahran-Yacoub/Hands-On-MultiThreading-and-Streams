package com.example.SpringBootrRestAPI.repo;

import com.example.SpringBootrRestAPI.models.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server,String> {

}
