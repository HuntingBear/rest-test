package ru.test.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class ClientModel {

    private String login;
    private String firstName;
    private String secondName;
    private LocalDate birthday;
    private String password;
    private String about;
    private String address;
}
