package com.event.tracker.eventtracker.mapper;

import com.event.tracker.eventtracker.dto.EventDTO;
import com.event.tracker.eventtracker.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEvent(EventDTO eventDTO);

    @Mapping(target = "attendees", ignore = true)
    EventDTO toEventDTO(Event event);

}
