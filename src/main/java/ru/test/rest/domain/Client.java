package ru.test.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, length = 1024)
    @JsonIgnore
    private String password;

    @Column(nullable = false, length = 1024)
    @JsonIgnore
    private String salt;

    @Column(length = 5000)
    private String about;

    @Column(length = 1000)
    private String address;
}
