package com.example.jdbc_assignment.Models;

public record Customer(int id,
                       String firstName,
                       String lastName,
                       String country,
                       String postalCode,
                       String phoneNumber,
                       String email) {
}
