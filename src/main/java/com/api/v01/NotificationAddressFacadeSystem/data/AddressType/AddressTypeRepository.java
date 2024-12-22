package com.api.v01.NotificationAddressFacadeSystem.data.AddressType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface AddressTypeRepository extends JpaRepository<AddressType, Long> {

    Optional<AddressType> findByTypeName(String typeName);


}
