package com.example.jdbc_assignment;
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

        System.out.println(res);
    }
}
