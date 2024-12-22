package com.api.v01.NotificationAddressFacadeSystem.service.AddressService;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface AddressService {

    Address save(Address address);

    boolean checkIfAddressExists(Long customerId, Long addressTypeId, String text);

    void delete(Long id);

    boolean existsById(Long id);

    Address updateAddressText(Long id, String text);
}
