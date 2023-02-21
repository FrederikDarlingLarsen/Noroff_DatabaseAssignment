package com.example.jdbc_assignment;

public record Customer(int id,
                       String firstName,
                       String lastName,
                       String postalCode,
                       String phoneNumber,
                       String email) {
}
