package com.event.tracker.eventtracker.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address eventAddress;

    @Column(name = "event_date")
    private Date eventDate;

    private String title;

    private String description;

    private String image;

    private Double price;

    @OneToMany(mappedBy = "event")
    private Set<Attendee> attendees = new HashSet<>();

    @Column(name = "attendee_limit")
    private Integer attendeeLimit;

    public Event() {
    }

    public Event(User user,
                 Date createdDate,
                 Date updatedDate,
                 Address eventAddress,
                 Date eventDate,
                 String title,
                 String description,
                 String image,
                 Double price,
                 Integer attendeeLimit) {
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.attendeeLimit = attendeeLimit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Address getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(Address eventAddress) {
        this.eventAddress = eventAddress;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Integer getAttendeeLimit() {
        return attendeeLimit;
    }

    public void setAttendeeLimit(Integer attendeeLimit) {
        this.attendeeLimit = attendeeLimit;
    }

    public static class Builder {
        private User user;
        private Date createdDate;
        private Date updatedDate;
        private Address eventAddress;
        private Date eventDate;
        private String title;
        private String description;
        private String image;
        private Double price;
        private Integer attendeeLimit;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder setEventAddress(Address eventAddress) {
            this.eventAddress = eventAddress;
            return this;
        }

        public Builder setEventDate(Date eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setAttendeeLimit(Integer attendeeLimit) {
            this.attendeeLimit = attendeeLimit;
            return this;
        }

        public Event build() {
            return new Event(user, createdDate, updatedDate, eventAddress, eventDate, title, description, image, price, attendeeLimit);
        }
    }
}
