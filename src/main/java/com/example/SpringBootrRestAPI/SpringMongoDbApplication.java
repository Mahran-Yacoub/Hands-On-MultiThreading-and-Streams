package com.example.SpringBootrRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class SpringMongoDbApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMongoDbApplication.class, args);

		//int[] a = {10,20,30};
		//System.out.println(IntStream.of(a).sum());
		//System.out.println(IntStream.of(a).filter(number -> number >= 20).sum());
		//System.out.println(IntStream.of(a).reduce(0,(x,y)-> x+y));

		//String [] names = {"mahran" , "Rajai" , "Yacoub"};
		//Arrays.stream(names).map(name -> "Hello "+name).forEach(name -> System.out.println(name));

		//List<String> s = Arrays.stream(names).filter(name -> name.contains("ja")).collect(Collectors.toList());
		// System.out.println(s);
	}


}
