package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.AddressDto;
import com.example.Ecommerce.exceptions.NoAddressExitsException;
import com.example.Ecommerce.exceptions.UserNotExitException;
import com.example.Ecommerce.model.Address;
import com.example.Ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserService userService;

    public List<AddressDto> getAllAddressByUserId(Long userId) {
        List<Address> addressList = addressRepository.findByUserId(userId);
        return addressList.stream().map(this::mapAddressToAddress).collect(Collectors.toList());
    }

    public AddressDto getAddressById(Long id) throws NoAddressExitsException {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            return mapAddressToAddress(address.get());
        }
        throw new NoAddressExitsException("NO address exits with id: "+id);
    }

    public ResponseEntity<Object> createAddress(AddressDto addressRequest) throws UserNotExitException {
        Long userId = addressRequest.getUserId();
        if(!userService.exitsUserByUserId(userId)){
            throw new UserNotExitException("user not exits with id: "+userId);
        }
        Address address = Address.builder()
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .firstName(addressRequest.getFirstName())
                .lastName(addressRequest.getLastName())
                .userId(addressRequest.getUserId())
                .build();
        addressRepository.save(address);
        ResponseEntity<Object> response = ResponseEntity.ok().build();
        return response;
    }

    public ResponseEntity<Object> updateAddress(Long id, AddressDto addressRequest) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if(!existingAddress.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Address address = existingAddress.get();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setFirstName(addressRequest.getFirstName());
        address.setLastName(addressRequest.getLastName());
        addressRepository.save(address);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> deleteAddress(Long id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if(!existingAddress.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Address address = existingAddress.get();
        addressRepository.delete(address);
        return ResponseEntity.ok().build();
    }

    private AddressDto mapAddressToAddress(Address address) {
        AddressDto addressDto = AddressDto.builder()
                .city(address.getCity())
                .userId(address.getUserId())
                .street(address.getStreet())
                .firstName(address.getFirstName())
                .lastName(address.getLastName())
                .build();
        return addressDto;
    }
}
