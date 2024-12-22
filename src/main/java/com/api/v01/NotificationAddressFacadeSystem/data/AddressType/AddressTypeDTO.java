package com.api.v01.NotificationAddressFacadeSystem.data.AddressType;

import lombok.Data;

@Data
public class AddressTypeDTO {

    private String name;

    public AddressType fromDTO(AddressTypeDTO dto) {
        AddressType addressType = AddressType.builder()
                .typeName(dto.getName())
                .build();
        return addressType;
    }
}
