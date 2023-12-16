package com.event.tracker.eventtracker.mapper;

import com.event.tracker.eventtracker.dto.AddressDTO;
import com.event.tracker.eventtracker.model.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDTO addressDTO);
    AddressDTO toAddressDTO(Address address);
}
