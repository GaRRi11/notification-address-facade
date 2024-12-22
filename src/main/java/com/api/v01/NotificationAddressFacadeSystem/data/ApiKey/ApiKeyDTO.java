package com.api.v01.NotificationAddressFacadeSystem.data.ApiKey;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiKeyDTO {

    private String key;

    private String owner;

    private LocalDateTime expiresAt;

    private boolean active;

    public ApiKey fromDTO (ApiKeyDTO apiKeyDTO) {
        return ApiKey.builder()
                .key(apiKeyDTO.getKey())
                .owner(apiKeyDTO.getOwner())
                .createdAt(LocalDateTime.now())
                .expiresAt(apiKeyDTO.getExpiresAt())
                .active(apiKeyDTO.isActive())
                .build();
    }
}
