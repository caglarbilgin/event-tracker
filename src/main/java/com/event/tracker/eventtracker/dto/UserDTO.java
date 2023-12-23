package com.event.tracker.eventtracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String surname,
        @Min(1) @Max(100) int age,
        String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Size(min = 8, max = 30) String password,
        Long phoneNumber) {
}
