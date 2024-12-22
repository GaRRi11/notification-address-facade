package com.api.v01.NotificationAddressFacadeSystem.service.AddressService;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean checkIfAddressExists(Long customerId, Long addressTypeId, String text) {
        return addressRepository.existsByCustomerIdAndTypeIdAndText(customerId, addressTypeId, text);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }

    @Override
    @Transactional
    public Address updateAddressText(Long id, String text) {
        Address address = addressRepository.findById(id).get();
        address.setText(text);
        address.setUpdatedAt(LocalDateTime.now());
        return addressRepository.save(address);
    }
}
