package exalt.company.SpringBootrRestAPI.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * This Class will be a Document that store in DataBase Automatically
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Server implements Comparable<Server> {

    @Id @Setter @Getter
    private String serverID ;

    @Setter @Getter
    private int serverCapacity ;

    @Setter @Getter
    private Active isActive ;

    @Setter @Getter
    private HashMap<String,Integer> customers  = new HashMap<>();

    @Override
    public String toString() {
        return "Server{" +
                "serverID='" + serverID + '\'' +
                ", serverCapacity=" + serverCapacity +
                ", isActive=" + isActive +
                ", customers=" + customers +
                '}';
    }

    @Override
    public int compareTo(Server o) {

        if(serverCapacity > o.serverCapacity){

            return 1 ;
        }else if (serverCapacity < o.serverCapacity){

            return -1 ;

        }else {

            return  0 ;
        }
    }
}
