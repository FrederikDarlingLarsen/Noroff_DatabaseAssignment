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
// List<Customer> results = custRepo.findAll();

        //Customer res = custRepo.findById(5);

        List<Customer> res = custRepo.findPage(1,1);

        Customer customerInsert = new Customer(0, "Floda", "Reltih", "Austria", "999","99999999","Reltih@ss.de");
        custRepo.insert(customerInsert);

        Customer customerUpdate = new Customer(0, "Rimidaldalv Hcovorimdilv", "Nitup", "Russia", "666","66666666","Nitup@KGB.ru");
        custRepo.update(customerUpdate);

        CustomerGenre genreResult = custRepo.findMostFrequentGenreForAGivenCustomerById(10);
        System.out.println(genreResult);

        CustomerGenre genreResultTie = custRepo.findMostFrequentGenreForAGivenCustomerById(12);
        System.out.println(genreResultTie);

        System.out.println("--------------------------------------------------------------------------------------------");

        CustomerSpender customerSpender = custRepo.findHighestSpender();
        System.out.println(customerSpender);

        System.out.println("--------------------------------------------------------------------------------------------");

        CustomerCountry customerCountry = custRepo.findCountryWithMostCustomers();
        System.out.println(customerCountry);

        System.out.println("--------------------------------------------------------------------------------------------");

        List<Customer> customerPage = custRepo.findPage(3,0);
        System.out.println(customerPage);

    }
}
