package com.api.v01.NotificationAddressFacadeSystem.service.AddressType;

import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressType;
import org.springframework.stereotype.Service;

@Service
public interface AddressTypeService {

    AddressType createAddressType(AddressType addressType);

    AddressType findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    boolean existsByTypeName(String typeName);
}
