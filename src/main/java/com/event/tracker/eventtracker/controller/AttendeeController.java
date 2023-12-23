package com.event.tracker.eventtracker.controller;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.service.AttendeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @PostMapping
    public EventDTO attendEvent(@RequestParam(value = "email") String email,
                                @RequestParam(value = "event-id") String eventId) {

        return attendeeService.attendEvent(email, eventId);
    }
}
