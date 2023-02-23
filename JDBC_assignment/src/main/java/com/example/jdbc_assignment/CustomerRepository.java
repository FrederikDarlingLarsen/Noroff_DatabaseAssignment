package com.example.jdbc_assignment;

import com.example.jdbc_assignment.Models.Customer;
import com.example.jdbc_assignment.Models.CustomerCountry;
import com.example.jdbc_assignment.Models.CustomerGenre;
import com.example.jdbc_assignment.Models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
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


    /**
     * Retrieves a list of all customers from the database.
     *
     * @return a list of all customers in the database
     * @throws SQLException if an error occurs while executing the SQL query
     *                      <p>
     *                      (=^-ω-^=)
     */
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";

        List<Customer> customers = new ArrayList<Customer>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
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

    /**
     * Retrieves a customer by ID
     *
     * @param id takes the id of a customer as an input
     * @return returns a customer with the given ID
     */
    public Customer findById(Integer id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        ResultSet result;

        List<Customer> customers = new ArrayList<Customer>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // Execute statement
            result = statement.executeQuery();


            if (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
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

    /**
     * Retrieves a customer by name
     *
     * @param firstName takes the firstname of a customer as an input
     * @return returns a customer with the given name or partial matches
     */
    public Customer findByName(String firstName) {
        String sql = "SELECT * FROM customer WHERE first_name LIKE  '%" + firstName + "%'";

        ResultSet result;

        List<Customer> customers = new ArrayList<Customer>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute statement
            result = statement.executeQuery();


            if (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
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


    /**
     * Retrieves a page with selected offset and limit
     *
     * @param limit  limit the amount of customers for the page
     * @param offset sets the offset for the page
     * @return returns the page with the selected offset and limit (=^-ω-^=)
     */
    public List<Customer> findPage(int limit, int offset) {
        String sql = "SELECT * FROM customer LIMIT " + limit + " OFFSET " + offset;

        List<Customer> customers = new ArrayList<Customer>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
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

    /**
     * inserts a customer to the database
     * @param customer takes in a customer object
     * @return either the row count for SQL Data Manipulation Language statements
     * or 0 for SQL statements that return nothing (=^-ω-^=)
     */
    public int insert(Customer customer){
        String sql = "INSERT INTO customer VALUES (?,?,?,NULL,NULL,NULL,NULL,?,?,?,NULL,?)";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customer.id());
            statement.setString(2,customer.firstName());
            statement.setString(3, customer.lastName());
            statement.setString(4, customer.country());
            statement.setString(5, customer.postalCode());
            statement.setString(6, customer.phoneNumber());
            statement.setString(7, customer.email());
            // Execute statement
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Updates a customer
     * @param customer sets the
     * @return
     */
    public int update(Customer customer){
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? WHERE customer_id = ?";
        int result = 0;
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,customer.firstName());
            statement.setString(2, customer.lastName());
            statement.setString(3, customer.country());
            statement.setString(4, customer.postalCode());
            statement.setString(5, customer.phoneNumber());
            statement.setString(6, customer.email());
            statement.setInt(7, customer.id());
            // Execute statement
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Retrieves the country with most customers
     * @return the country (=^-ω-^=)
     */
    public CustomerCountry findCountryWithMostCustomers(){
        String sql = "SELECT Country, COUNT (*) AS Number FROM customer GROUP BY Country ORDER BY Number DESC";
        ResultSet result;

        List<CustomerCountry> customerCountryList = new ArrayList<CustomerCountry>();
        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            result = statement.executeQuery();

            if(result.next()){
                CustomerCountry customer = new CustomerCountry(
                        result.getString("country")
                );
                customerCountryList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCountryList.get(0);
    }


//    public CustomerSpender findHighestSpender(){
//        String sql = "SELECT customer_id, SUM(total) AS total_score FROM invoice GROUP BY customer_id ORDER BY SUM(total) DESC";
//
//        ResultSet result;
//
//        List<CustomerSpender> customerSpenderList = new ArrayList<CustomerSpender>();
//        try(Connection conn = DriverManager.getConnection(url, username,password)) {
//            // Write statement
//            PreparedStatement statement = conn.prepareStatement(sql);
//
//            // Execute statement
//            result = statement.executeQuery();
//
//            if(result.next()){
//                CustomerSpender customer = new CustomerSpender(
//                        result.getInt("customer_id"),
//                        result.getString("first_name"),
//                        result.getString("last_name"),
//                        result.getString("total")
//                );
//                customerSpenderList.add(customer);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//
//        Customer customer = findById(cust_id);
//
//        return "Name: " + customer.firstName() + ", Total: " + total;
//    }


    /**
     * Retrieves the most frequent genre for a given customer
     * @param id
     * @return
     */
    public CustomerGenre findMostFrequentGenreForAGivenCustomerById(int id){
        String sql = "SELECT g.name AS genre_name, COUNT(*) AS genre_count FROM invoice i JOIN invoice_line il ON i.invoice_id = il.invoice_id JOIN track t ON il.track_id = t.track_id JOIN genre g ON t.genre_id = g.genre_id WHERE i.customer_id = ? GROUP BY g.name ORDER BY genre_count DESC LIMIT 1";

        ResultSet result;

        List<CustomerGenre> customerGenreList = new ArrayList<CustomerGenre>();

        try(Connection conn = DriverManager.getConnection(url, username,password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            // Execute statement
            result = statement.executeQuery();

            if(result.next()) {
                CustomerGenre customerGenre = new CustomerGenre(id,findById(id).firstName(),result.getString("genre_name"),);
                customerGenreList.add(customerGenre);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return customerGenreList.get(0);
    }


    public int delete(Customer object){
        return 0;

    }
    public int deleteById(Integer id){
        return 0;
    }
}