package com.example.SpringBootrRestAPI.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This Class will be a Document that store in DataBase Automatically
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id @Setter @Getter
    private Integer id ;

    @Setter @Getter
    private String firstName ;

    @Setter @Getter
    private String lastName ;

}
