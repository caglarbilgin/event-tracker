package com.event.tracker.eventtracker.service;

import com.event.tracker.eventtracker.dto.AddressDTO;
import com.event.tracker.eventtracker.dto.UserDTO;
import com.event.tracker.eventtracker.exception.CustomException;
import com.event.tracker.eventtracker.mapper.AddressMapper;
import com.event.tracker.eventtracker.mapper.UserMapper;
import com.event.tracker.eventtracker.model.Address;
import com.event.tracker.eventtracker.model.User;
import com.event.tracker.eventtracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Value("${address.max-size}")
    private int maxSize;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AddressMapper addressMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public UserService(UserRepository userRepository, UserMapper userMapper, AddressMapper addressMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void kafkaTest() {
        log.info("Start Test Kafka");
        kafkaTemplate.send("user-information", "testMessage1");
    }

    public UserDTO createUser(UserDTO userDTO) {
        //TODO: phone number is unique ?
        checkUserExist(userDTO.email());
        User user = userMapper.toUser(userDTO);
        user.setCreatedDate(new Date());
        userRepository.save(user);
        log.info("User '{}' created successfully !", userDTO.email());
        return userDTO;
    }

    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    public UserDTO deleteUser(String email) {
        User user = getUserIfExist(email);
        userRepository.delete(user);
        return userMapper.toUserDTO(user);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = getUserIfExist(userDTO.email());
        userMapper.updateModelFromDTO(userDTO, user);
        userRepository.save(user);
        return userDTO;
    }

    public AddressDTO createAddress(String email, AddressDTO addressDTO) {
        User user = getUserIfExist(email);

        if (Objects.nonNull(user.getAddresses()) && user.getAddresses().size() >= maxSize)
            throw new CustomException(
                    CustomException.ErrorCode.MAX_ADDRESS_SIZE,
                    CustomException.ErrorCode.MAX_ADDRESS_SIZE.getValue());


        Address address = addressMapper.toAddress(addressDTO);
        user.getAddresses().add(address);
        address.setUser(user);

        userRepository.save(user);
        return addressDTO;
    }

    private User getUserIfExist(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException(
                        CustomException.ErrorCode.USER_NOT_FOUND,
                        CustomException.ErrorCode.USER_NOT_FOUND.getValue()));
    }

    public AddressDTO deleteAddress(String email, String addressId) {
        User user = getUserIfExist(email);
        UUID addressUUID = UUID.fromString(addressId);

        Address deletedAddress = findAddressById(user, addressUUID);

        user.getAddresses().removeIf(address -> address.getId().equals(addressUUID));
        userRepository.save(user);

        return addressMapper.toAddressDTO(deletedAddress);
    }

    public List<AddressDTO> getAddressByUser(String email) {
        User user = getUserIfExist(email);
        return user.getAddresses().stream().map(addressMapper::toAddressDTO).toList();
    }

    public AddressDTO updateAddress(String email, String addressId, AddressDTO addressDTO) {
        User user = getUserIfExist(email);

        UUID addressUUID = UUID.fromString(addressId);

        Address addressToUpdate = user.getAddresses().stream()
                .filter(address -> address.getId().equals(addressUUID))
                .findFirst()
                .orElseThrow(() -> new CustomException(
                        CustomException.ErrorCode.ADDRESS_NOT_FOUND,
                        CustomException.ErrorCode.ADDRESS_NOT_FOUND.getValue()));

        updateAddressFields(addressToUpdate, addressDTO);

        userRepository.save(user);

        return addressMapper.toAddressDTO(addressToUpdate);
    }

    private void updateAddressFields(Address address, AddressDTO addressDTO) {
        address.setAddressLabel(addressDTO.addressLabel());
        address.setCity(addressDTO.city());
        address.setPostCode(addressDTO.postCode());
        address.setCountry(addressDTO.country());
    }

    private static Address findAddressById(User user, UUID addressUUID) {
        return user.getAddresses().stream()
                .filter(address -> address.getId().equals(addressUUID))
                .findFirst()
                .orElseThrow(() -> new CustomException(
                        CustomException.ErrorCode.ADDRESS_NOT_FOUND,
                        CustomException.ErrorCode.ADDRESS_NOT_FOUND.getValue()));
    }

    public void checkUserExist(String email) {
        userRepository.findUserByEmail(email)
                .ifPresent(user -> {
                    throw new CustomException(
                            CustomException.ErrorCode.USER_ALREADY_EXIST,
                            CustomException.ErrorCode.USER_ALREADY_EXIST.getValue());
                });
    }

}