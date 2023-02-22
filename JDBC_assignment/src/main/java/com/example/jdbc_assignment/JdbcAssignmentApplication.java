package com.example.jdbc_assignment;
import com.example.jdbc_assignment.Models.Customer;
import com.example.jdbc_assignment.Models.CustomerCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        //List<Customer> res = custRepo.findPage(1,1);

       // Customer customer = new Customer(60, "Bo", "Olsen", "Denmark", "1233","88888888","katte@ersøde.dk");

        CustomerCountry customer = custRepo.findCountryWithMostCustomers();

        System.out.println(customer);

       // custRepo.update(customer);

      /* for(int i = 0; i < 60; i++) {
           String res = custRepo.findMostFrequentGenreForAGivenCustomerById(i);
           System.out.println(res);
       }*/
    }
}
