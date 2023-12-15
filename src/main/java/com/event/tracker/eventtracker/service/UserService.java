package com.event.tracker.eventtracker.service;

import com.event.tracker.eventtracker.dto.UserDTO;
import com.event.tracker.eventtracker.exception.CustomException;
import com.event.tracker.eventtracker.mapper.UserMapper;
import com.event.tracker.eventtracker.model.User;
import com.event.tracker.eventtracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        checkUserExist(userDTO.email());
        User user = userMapper.toUser(userDTO);
        user.setCreatedDate(LocalDateTime.now());
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

    public void checkUserExist(String email) {
        userRepository.findUserByEmail(email)
                .ifPresent(user -> {
                    throw new CustomException(
                            CustomException.ErrorCode.USER_ALREADY_EXIST,
                            CustomException.ErrorCode.USER_ALREADY_EXIST.getValue());
                });
    }
}