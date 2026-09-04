package com.greennest.backend.service;

import com.greennest.backend.entity.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(Address address, Long userId);

    List<Address> getAddressesByUserId(Long userId);
}
