package com.api.v01.NotificationAddressFacadeSystem.data.AddressType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressTypeDTO {


    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 30 characters")
    private String name;

    public AddressType fromDTO(AddressTypeDTO dto) {
        return AddressType.builder()
                .typeName(dto.getName())
                .build();
    }
}
