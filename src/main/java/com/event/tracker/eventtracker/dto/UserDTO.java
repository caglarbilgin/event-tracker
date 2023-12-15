package com.event.tracker.eventtracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UserDTO(String name,
                      String surname,
                      @Min(1) @Max(100) int age,
                      String email,
                      @JsonIgnore @Size(min = 8, max = 30) String password,
                      Long phoneNumber) {
}
