package com.event.tracker.eventtracker.dto;

import com.event.tracker.eventtracker.model.Attendee;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record EventDTO(
        UUID id,
        Date createdDate,
        Date updatedDate,
        AddressDTO eventAddress,
        Date eventDate,
        String title,
        String description,
        String image,
        Double price,
        Set<Attendee> attendees,
        @Size(min = 0) Integer attendeeLimit) {
}
