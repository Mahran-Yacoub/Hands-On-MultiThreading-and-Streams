package com.example.SpringBootrRestAPI.services;

import com.devskiller.friendly_id.FriendlyId;
import com.example.SpringBootrRestAPI.models.Active;
import com.example.SpringBootrRestAPI.models.Server;
import com.example.SpringBootrRestAPI.repo.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class Services {

    private final int CAPACITY = 100 ;

    @Autowired
    private ServerRepository serverRepositoryDB;

    private FriendlyId friendlyId;

    public Services(ServerRepository serverRepositoryDB, FriendlyId friendlyId) {
        this.serverRepositoryDB = serverRepositoryDB;
        this.friendlyId = friendlyId;
    }

    public Services(){

    }
    public synchronized void allocate(int space, String id) {

        Optional<Server> selectServer = serverRepositoryDB.findAll().stream()
                .filter(server -> server.getServerCapacity() >= space
                        && server.getIsActive() == Active.ON).findFirst();


        if(selectServer.isPresent()){

            updateServer(selectServer,space,id);

        }else{

            spanAndAllocateServer(space, id) ;

        }
    }

    private void spanAndAllocateServer(int space, String id) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Server newServer = spanServer();
        int newCapacity = newServer.getServerCapacity() - space ;
        newServer.setServerCapacity(newCapacity);
        newServer.getCustomers().put(id,space);

        serverRepositoryDB.save(newServer) ;

    }

    private Server spanServer() {

        String newID = friendlyId.createFriendlyId();
        Server newServer = new Server(newID,CAPACITY,Active.ON,new HashMap<String,Integer>());

        return newServer ;
    }

    private void updateServer(Optional<Server> selectServer, int space, String id) {

        Server server = selectServer.get();
        int newCapacity = server.getServerCapacity() - space ;
        server.setServerCapacity(newCapacity);

        if(server.getCustomers().containsKey(id)){

            int existSpace = server.getCustomers().get(id);
            int newSpace = existSpace + space ;
            server.getCustomers().put(id,newSpace);

        }else{

            server.getCustomers().put(id,space);
        }

        serverRepositoryDB.save(server);
    }

}


