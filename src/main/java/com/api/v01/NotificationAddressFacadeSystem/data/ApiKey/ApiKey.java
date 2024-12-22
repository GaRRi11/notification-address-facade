package com.api.v01.NotificationAddressFacadeSystem.data.ApiKey;

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
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, length = 100)
    private String key;

    @Column(nullable = false, unique = true, updatable = false, length = 30)
    private String owner;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false,name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean active;

}
