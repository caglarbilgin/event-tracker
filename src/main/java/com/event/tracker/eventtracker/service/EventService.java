package com.event.tracker.eventtracker.service;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.mapper.AddressMapper;
import com.event.tracker.eventtracker.mapper.EventMapper;
import com.event.tracker.eventtracker.model.Event;
import com.event.tracker.eventtracker.model.User;
import com.event.tracker.eventtracker.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final UserService userService;

    private final EventMapper eventMapper;

    private final AddressMapper addressMapper;

    public EventService(EventRepository eventRepository, UserService userService, EventMapper eventMapper, AddressMapper addressMapper) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.eventMapper = eventMapper;
        this.addressMapper = addressMapper;
    }

    public EventDTO createEventByUser(String email, EventDTO eventDTO) {
        User user = userService.getUserIfExist(email);

        Event event = new Event.Builder()
                .setUser(user)
                .setCreatedDate(new Date())
                .setUpdatedDate(new Date())
                .setEventAddress(addressMapper.toAddress(eventDTO.eventAddress()))
                .setEventDate(eventDTO.eventDate())
                .setTitle(eventDTO.title())
                .setDescription(eventDTO.description())
                .setImage(eventDTO.image())
                .setPrice(eventDTO.price())
                .setAttendeeLimit(eventDTO.attendeeLimit())
                .build();

        eventRepository.save(event);

        return eventMapper.toEventDTO(event);
    }
}
