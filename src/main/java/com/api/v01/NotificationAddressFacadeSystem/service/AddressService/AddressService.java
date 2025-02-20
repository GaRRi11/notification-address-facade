package com.api.v01.NotificationAddressFacadeSystem.service.AddressService;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AddressService {

    Address createAddress(Address address);

    boolean checkIfAddressExists(Long customerId, Long addressTypeId, String text);

    void delete(Long id);

    boolean existsById(Long id);

    Address updateAddressText(Long id, String text);

    List<Address> getAllAddresses();

    List<Address> findByCustomerId(Long customerId);

    Optional<Address> findById(Long id);

    void deleteByAddressTypeId(@Min(value = 1, message = "ID must be a positive number") Long id);
}
