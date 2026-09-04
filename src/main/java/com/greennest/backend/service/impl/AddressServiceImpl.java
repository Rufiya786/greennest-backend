package com.greennest.backend.service.impl;

import com.greennest.backend.entity.Address;
import com.greennest.backend.entity.User;
import com.greennest.backend.exception.ResourceNotFoundException;
import com.greennest.backend.repository.AddressRepository;
import com.greennest.backend.repository.UserRepository;
import com.greennest.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address addAddress(Address address, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        address.setUser(user);

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findByUser_UserId(userId);
    }
}