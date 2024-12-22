package com.api.v01.NotificationAddressFacadeSystem.data.Relation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "customer_id")
    private Long customerId;

    @Column(nullable = false, name = "address_id")
    private Long addressTypeId;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;
}
