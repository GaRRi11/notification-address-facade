package com.api.v01.NotificationAddressFacadeSystem.data.Customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {


    @NotBlank(message = "username cannot be blank")
    @Size(max = 100, message = "username must not exceed 50 characters")
    private String username;


    public Customer fromDTO(CustomerDTO customerDTO) {
        return Customer.builder()
                .username(customerDTO.username)
                .relationIds(List.of())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
