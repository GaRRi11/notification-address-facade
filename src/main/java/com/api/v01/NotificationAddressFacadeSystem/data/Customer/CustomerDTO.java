package com.api.v01.NotificationAddressFacadeSystem.data.Customer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private String username;


    public Customer fromDTO(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .username(customerDTO.username)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return customer;
    }
}
