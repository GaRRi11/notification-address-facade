package com.api.v01.NotificationAddressFacadeSystem.service.AddressService;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean checkIfAddressExists(Long customerId, Long addressTypeId, String text) {
        return addressRepository.existsByCustomerIdAndAddressTypeIdAndText(customerId, addressTypeId, text);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return !addressRepository.existsById(id);
    }

    @Override
    @Transactional
    public Address updateAddressText(Long id, String text) {
        Address address = addressRepository.findById(id).get();
        address.setText(text);
        address.setUpdatedAt(LocalDateTime.now());
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void deleteByAddressTypeId(Long id) {
        addressRepository.deleteAllByAddressTypeId(id);
    }

}
