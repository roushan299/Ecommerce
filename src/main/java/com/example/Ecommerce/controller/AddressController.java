 package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.AddressDto;
import com.example.Ecommerce.exceptions.NoAddressExitsException;
import com.example.Ecommerce.exceptions.UserNotExitException;
import com.example.Ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/get")
    public List<AddressDto> getAllAddressByUserId(@RequestParam("userId") Long userId) {
        List<AddressDto> addressList = addressService.getAllAddressByUserId(userId);
        return addressList;
    }

    @GetMapping("/get/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        AddressDto address = null;
        try {
            address = addressService.getAddressById(id);
        } catch (NoAddressExitsException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody AddressDto addressRequest) {
        ResponseEntity<Object> response = null;
        try {
            response = addressService.createAddress(addressRequest);
        } catch (UserNotExitException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDto addressRequest) {
        ResponseEntity<Object> response = addressService.updateAddress(id, addressRequest);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") Long id) {
        ResponseEntity<Object> response = addressService.deleteAddress(id);
        return response;
    }

}