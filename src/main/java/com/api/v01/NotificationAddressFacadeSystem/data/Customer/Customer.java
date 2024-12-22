package com.api.v01.NotificationAddressFacadeSystem.data.Customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50,name = "username")
    private String username;

    @JoinColumn(name = "customer_id")
    @ElementCollection
    @Column(name = "relation_id")
    private List<Long> relationIds;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;


    @Column(nullable = false,name = "updated_at")
    private LocalDateTime updatedAt;

}
