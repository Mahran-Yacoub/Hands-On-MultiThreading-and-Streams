package exalt.company.SpringBootrRestAPI.repositories;

import exalt.company.SpringBootrRestAPI.models.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server,String> {

}
