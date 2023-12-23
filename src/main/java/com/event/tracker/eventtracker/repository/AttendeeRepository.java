package com.event.tracker.eventtracker.repository;

import com.event.tracker.eventtracker.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
}
