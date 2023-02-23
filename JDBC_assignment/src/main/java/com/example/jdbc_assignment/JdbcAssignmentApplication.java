package com.example.jdbc_assignment;
import com.example.jdbc_assignment.Models.Customer;
import com.example.jdbc_assignment.Models.CustomerCountry;
import com.example.jdbc_assignment.Models.CustomerGenre;
import com.example.jdbc_assignment.Models.CustomerSpender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JdbcAssignmentApplication implements ApplicationRunner{

    @Autowired
    CustomerRepository custRepo;

    public static void main(String[] args) {
        SpringApplication.run(JdbcAssignmentApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        custRepo.test();

        // find all customers
        List<Customer> customerFindAll = custRepo.findAll();
        for (Customer customer : customerFindAll) {
            System.out.println(customer);
        }

    System.out.println("--------------------------------------------------------------------------------------------");

        // find a customer by ID
        Customer customerRepoById = custRepo.findById(5);
        System.out.println(customerRepoById);

    System.out.println("--------------------------------------------------------------------------------------------");

            // find a customer by name
            Customer customerFindByName = custRepo.findByName("Emma");
        System.out.println(customerFindByName);

    System.out.println("--------------------------------------------------------------------------------------------");

        // return a page with offset and limit
        List<Customer> customerPage = custRepo.findPage(3,0);
        for (Customer customer : customerPage) {
            System.out.println(customer);
        }

    System.out.println("--------------------------------------------------------------------------------------------");

//        Customer customerInsert = new Customer(0, "Floda", "Reltih", "Poland", "999","99999999","Reltih@ss.de");
//        custRepo.insert(customerInsert);

    System.out.println("--------------------------------------------------------------------------------------------");

        // update the customer
        Customer customerUpdate = new Customer(0, "Rimidaldalv Hcovorimdilv", "Nitup", "France", "666","66666666","Nitup@KGB.ru");
        custRepo.update(customerUpdate);

    System.out.println("--------------------------------------------------------------------------------------------");

        // find the country with most customers
        CustomerCountry customerCountry = custRepo.findCountryWithMostCustomers();
        System.out.println(customerCountry);

    System.out.println("--------------------------------------------------------------------------------------------");

        // find the highest spender
        CustomerSpender customerSpender = custRepo.findHighestSpender();
        System.out.println(customerSpender);

    System.out.println("--------------------------------------------------------------------------------------------");

        // find the most popular genre for a given customer if tie it shows both
        CustomerGenre genreResult = custRepo.findMostFrequentGenreForAGivenCustomerById(10);
        System.out.println(genreResult);

        CustomerGenre genreResultTie = custRepo.findMostFrequentGenreForAGivenCustomerById(12);
        System.out.println(genreResultTie);
    }
}