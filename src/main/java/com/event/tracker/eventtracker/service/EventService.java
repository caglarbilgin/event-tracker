package com.event.tracker.eventtracker.service;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.exception.CustomException;
import com.event.tracker.eventtracker.mapper.AddressMapper;
import com.event.tracker.eventtracker.mapper.EventMapper;
import com.event.tracker.eventtracker.model.Event;
import com.event.tracker.eventtracker.model.User;
import com.event.tracker.eventtracker.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    public List<EventDTO> getAllEvent() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toEventDTO)
                .toList();
    }

    public EventDTO getEventDetails(String eventId) {
        Event event = eventRepository.findById(UUID.fromString(eventId))
                .orElseThrow(() ->
                        new CustomException(
                                CustomException.ErrorCode.EVENT_NOT_FOUND,
                                CustomException.ErrorCode.EVENT_NOT_FOUND.getValue()));

        return eventMapper.toEventDTO(event);
    }
}
