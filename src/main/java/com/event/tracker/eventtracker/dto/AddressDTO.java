package com.event.tracker.eventtracker.dto;


import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        @NonNull String country,
        @NonNull String city,
        int postCode,
        @Size(max = 256) String addressLabel) {
}
