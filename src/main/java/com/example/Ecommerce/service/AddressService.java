package com.example.Ecommerce.service;


import com.example.Ecommerce.dto.AddressDto;
import com.example.Ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<AddressDto> getAllAddressByUserId(Long userId) {
        //TODO
    }

    public AddressDto getAddressById(Long id) {
        //TODO
    }

    public ResponseEntity<Object> createAddress(AddressDto addressRequest) {
        //TODO
    }

    public ResponseEntity<Object> updateAddress(Long id, AddressDto addressRequest) {
        //TODO
    }

    public ResponseEntity<Object> deleteAddress(Long id) {
        //TODO
    }
}
