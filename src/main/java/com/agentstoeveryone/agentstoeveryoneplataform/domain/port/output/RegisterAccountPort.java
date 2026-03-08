package com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output;

import com.agentstoeveryone.agentstoeveryoneplataform.domain.models.User;

public interface RegisterAccountPort {
    String register(User user) throws Exception;
}
