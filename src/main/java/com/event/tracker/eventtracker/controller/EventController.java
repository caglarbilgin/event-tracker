package com.event.tracker.eventtracker.controller;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.service.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventDTO createEventByUser(@RequestParam("email") String email,
                                      @RequestBody EventDTO eventDTO) {
        return eventService.createEventByUser(email, eventDTO);
    }
}
