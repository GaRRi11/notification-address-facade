package com.api.v01.NotificationAddressFacadeSystem.data.Address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "customer_id")
    private Long customerId;

    @Column(nullable = false,name = "address_type_id")
    private Long addressTypeId;

    @Column(nullable = false,updatable = true,length = 30)
    private String text;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;


    @Column(nullable = false,name = "updated_at")
    private LocalDateTime updatedAt;


}
