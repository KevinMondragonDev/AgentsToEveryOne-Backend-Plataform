package com.agentstoeveryone.agentstoeveryoneplataform.infrastructure.input.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto{

    private String username;

    private String name;

    private String middleName;

    private String lastname;

    private String secondLastname;

    private String password;

}
