package com.greennest.backend.controller;

import com.greennest.backend.entity.Address;
import com.greennest.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address, @RequestParam Long userId) {
        Address saved = addressService.addAddress(address, userId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable Long userId) {
        List<Address> addresses = addressService.getAddressesByUserId(userId);
        return ResponseEntity.ok(addresses);
    }
}
