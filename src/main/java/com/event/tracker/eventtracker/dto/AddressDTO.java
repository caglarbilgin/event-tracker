package com.event.tracker.eventtracker.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record AddressDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) //TODO:her türlü donsun
        UUID id,
        @NonNull String country,
        @NonNull String city,
        int postCode,
        @Size(max = 256) String addressLabel) {
}
