package com.api.v01.NotificationAddressFacadeSystem.data.Notification;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "customer_id")
    private Long customerId;

    @Column(nullable = false,name = "address_id")
    private Long addressId;

    @Column(nullable = false,length = 200)
    private String text;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;

}
