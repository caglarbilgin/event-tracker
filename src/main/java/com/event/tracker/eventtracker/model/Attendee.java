package com.event.tracker.eventtracker.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "attendees")
public class Attendee {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date attendeeDate;

    public Attendee() {
    }

    public Attendee(Event event, User user, Date attendeeDate) {
        this.event = event;
        this.user = user;
        this.attendeeDate = attendeeDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAttendeeDate() {
        return attendeeDate;
    }

    public void setAttendeeDate(Date attendeeDate) {
        this.attendeeDate = attendeeDate;
    }
}
