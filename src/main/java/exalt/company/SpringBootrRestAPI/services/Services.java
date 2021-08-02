package exalt.company.SpringBootrRestAPI.services;

import com.devskiller.friendly_id.FriendlyId;
import exalt.company.SpringBootrRestAPI.models.Active;
import exalt.company.SpringBootrRestAPI.models.Server;
import exalt.company.SpringBootrRestAPI.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Optional;

/**
 * This is A class that contains methods that we need to rent a space in
 * a server.
 */
@Service
public class Services {

    /**
     * Maximum Capacity of a server
     */
    private final int CAPACITY = 100;

    @Autowired
    private ServerRepository serverRepositoryDB;

    /**
     * This method will read servers that exist in servers pool and
     * rent a given space in a server if there exist space in one of them ,
     * otherwise it will span new server in servers pool and rent a given space in it.
     *
     * @param space Space we want to rent in range from 1 to 100 Inclusive.
     * @param id    ID of Customer who wants to rent The space.
     */

    public synchronized void allocate(int space, String id) {

        Optional<Server> selectServer = serverRepositoryDB.findAll().stream()
                .filter(server -> server.getServerCapacity() >= space).findFirst();

        if (selectServer.isPresent()) {

            updateServer(selectServer, space, id);

        } else {

            spanNewServer(space, id);
        }
    }

    /**
     * This Method will Create New Server in Servers Pool and
     * rent A given Space in it and save a customer ID that wants to rent a space
     * in it.
     *
     * @param space      space that want to rent in new Server.
     * @param customerId Customer ID who wants to rent the space.
     */
    private void spanNewServer(int space, String customerId) {

        Server newServer = spanServer();
        serverRepositoryDB.save(newServer);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        newServer.setIsActive(Active.ON);
        int newCapacity = newServer.getServerCapacity() - space;
        newServer.setServerCapacity(newCapacity);
        newServer.getCustomers().put(customerId, space);
        serverRepositoryDB.save(newServer);
    }

    /**
     * This is A Helping Method For A SpanNewServer
     * to create new server with maximum capacity and OFF Status
     * and Hash Table to save customers that will rent in a server.
     *
     * @return New Server With maximum capacity and InActive.
     */
    private Server spanServer() {

        String newID = FriendlyId.createFriendlyId();
        Server newServer = new Server(newID, CAPACITY, Active.OFF, new HashMap<String, Integer>());
        return newServer;
    }

    /**
     * This method will used to update and rent space in exist server in servers pool
     *
     * @param selectServer The server we will rent a given space in it.
     * @param space        The space that we want to rent.
     * @param customerID           Customer ID who wants to rent The space.
     */
    private void updateServer(Optional<Server> selectServer, int space, String customerID) {

        Server server = selectServer.get();

        while(serverRepositoryDB.findById(server.getServerID()).get().getIsActive() != Active.ON){

            //wait a server to be ON
        }

        int newCapacity = server.getServerCapacity() - space;
        server.setServerCapacity(newCapacity);

        if (server.getCustomers().containsKey(customerID)) {

            int existSpace = server.getCustomers().get(customerID);
            int newSpace = existSpace + space;
            server.getCustomers().put(customerID, newSpace);

        } else {

            server.getCustomers().put(customerID, space);
        }

        serverRepositoryDB.save(server);
    }
}


