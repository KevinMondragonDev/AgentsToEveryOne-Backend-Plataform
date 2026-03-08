package com.agentstoeveryone.agentstoeveryoneplataform.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@RequiredArgsConstructor
public class User implements Serializable {
    private BigInteger userId;
    private UUID externalId;
    private String username;
    private String name;
    private String middleName;
    private String lastname;
    private String secondLastname;
    private String password;
    private LocalTime createdAt;
    private String createdBy;
    private String updatedBy;
    private LocalTime updatedAt;
}
