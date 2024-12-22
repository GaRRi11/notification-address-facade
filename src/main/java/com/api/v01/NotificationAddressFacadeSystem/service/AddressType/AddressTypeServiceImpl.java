package com.api.v01.NotificationAddressFacadeSystem.service.AddressType;

import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressType;
import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressTypeServiceImpl implements AddressTypeService {

    private final AddressTypeRepository addressTypeRepository;

    @Override
    @Transactional
    public AddressType createAddressType(AddressType addressType) {
        return addressTypeRepository.save(addressType);
    }

    @Override
    public boolean existsById(Long id) {
        return !addressTypeRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        addressTypeRepository.deleteById(id);
    }

    @Override
    public boolean existsByTypeName(String typeName) {
        return addressTypeRepository.findByTypeName(typeName).isPresent();
    }
}
