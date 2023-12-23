package com.event.tracker.eventtracker.service;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.exception.CustomException;
import com.event.tracker.eventtracker.mapper.EventMapper;
import com.event.tracker.eventtracker.model.Attendee;
import com.event.tracker.eventtracker.model.Event;
import com.event.tracker.eventtracker.model.User;
import com.event.tracker.eventtracker.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AttendeeService {

    private final EventService eventService;
    private final UserService userService;
    private final AttendeeRepository attendeeRepository;
    private final EventMapper eventMapper;

    public AttendeeService(EventService eventService, UserService userService, AttendeeRepository attendeeRepository, EventMapper eventMapper) {
        this.eventService = eventService;
        this.userService = userService;
        this.attendeeRepository = attendeeRepository;
        this.eventMapper = eventMapper;
    }

    public EventDTO attendEvent(String email, String eventId) {
        User user = userService.getUserIfExist(email);
        Event event = eventService.getEventIfExist(eventId);

        event.getAttendees()
                .forEach(attendee -> {
                    if (attendee.getUser().getEmail().equals(email)) {
                        throw new CustomException(
                                CustomException.ErrorCode.ALREADY_ATTENDING_ERROR,
                                CustomException.ErrorCode.ALREADY_ATTENDING_ERROR.getValue());
                    }
                });

        Attendee attendee = new Attendee(event, user, new Date());
        attendeeRepository.save(attendee);
        return eventMapper.toEventDTO(event);
    }
}
