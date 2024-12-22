package com.api.v01.NotificationAddressFacadeSystem.data.Admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminDTO {


    private String username;
    private String password;

    private Admin fromDTO(AdminDTO fromDTO) {
        Admin admin = Admin.builder()
                .username(fromDTO.getUsername())
                .password(fromDTO.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        return admin;
    }

}
