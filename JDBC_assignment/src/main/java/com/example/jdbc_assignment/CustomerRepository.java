package com.example.jdbc_assignment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements CustomerInterface {

    // Default values that can be overridden
    private final String url;
    private final String username;
    private final String password;

    public CustomerRepository(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void test() {
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";

        List<Customer> customers = new ArrayList<Customer>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }

            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer findById(Integer id){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        ResultSet result;

        List<Customer> customers = new ArrayList<Customer>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // Execute statement
            result = statement.executeQuery();


            if(result.next()){
                Customer customer = new Customer(
                    result.getInt("customer_id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("postal_code"),
                    result.getString("phone"),
                    result.getString("email")
            );
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return customers.get(0);
    }

    public Customer findByName(String firstName){
        String sql = "SELECT * FROM customer WHERE first_name LIKE  '%" + firstName + "%'";

        ResultSet result;

        List<Customer> customers = new ArrayList<Customer>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute statement
            result = statement.executeQuery();


            if(result.next()){
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers.get(0);
    }



    public List<Customer> findPage(int limit, int offset){
        String sql = "SELECT * FROM customer LIMIT " + limit + " OFFSET " + offset;

        List<Customer> customers = new ArrayList<Customer>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")
                );
                customers.add(customer);
            }

            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public int insert(Customer object){
        return 0;
    }

    public int update(Customer object){
        return 0;

    }

    public int delete(Customer object){
        return 0;

    }

    public int deleteById(Integer id){
        return 0;
    }






}