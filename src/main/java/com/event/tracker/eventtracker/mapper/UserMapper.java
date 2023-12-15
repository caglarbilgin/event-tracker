package com.event.tracker.eventtracker.mapper;

import com.event.tracker.eventtracker.dto.UserDTO;
import com.event.tracker.eventtracker.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
