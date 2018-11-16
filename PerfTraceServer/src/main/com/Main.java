package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import persistence.WebappRepository;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Main {



  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

//  @Bean
//  public CommandLineRunner demo(WebappRepository repository) {
//    return (args) -> {
////       save a couple of customers
//      repository.save(new Webapp("Jack"));
//      repository.save(new Webapp("Jack.e.e."));
//
//      // fetch all customers
//      System.out.println("Customers found with findAll():");
//      System.out.println("-------------------------------");
//      for (Webapp customer : repository.findAll()) {
//        System.out.println(customer.toString());
//      }
//      System.out.println("");
//
//
//    };
//  }
}