package com.api.v01.NotificationAddressFacadeSystem.data.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByCustomerIdAndAddressTypeIdAndText(Long customerId, Long addressTypeId, String text);

}
