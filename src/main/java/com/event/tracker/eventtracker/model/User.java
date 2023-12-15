package com.event.tracker.eventtracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String surname;

    private int age;

    private String email;

    private String password;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "phone_number")
    private Long phoneNumber;

    public User() {
    }

    public User(UUID id, String email, String password, LocalDateTime createdDate, Long phoneNumber, String name, String surname, int age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(String email, String password, LocalDateTime createdDate, Long phoneNumber, String name, String surname, int age) {
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
