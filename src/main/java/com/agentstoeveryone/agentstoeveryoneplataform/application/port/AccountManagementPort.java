package com.agentstoeveryone.agentstoeveryoneplataform.application.port;

import com.agentstoeveryone.agentstoeveryoneplataform.application.useCases.AccountManagementUseCase;
import com.agentstoeveryone.agentstoeveryoneplataform.domain.models.User;
import com.agentstoeveryone.agentstoeveryoneplataform.domain.port.output.RegisterAccountPort;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManagementPort implements AccountManagementUseCase {

    private final RegisterAccountPort registerAccountPort;

    public String register(User user) throws Exception {
        var userPassword = user.getPassword();

        int logRounds = 12;  // Increasing this value makes it more secure, but slower
        // Generate the salt
        String salt = BCrypt.gensalt(logRounds);
        // Hash the password with the salt
        var userPasswordhashed = BCrypt.hashpw(userPassword, salt);
        user.setPassword(userPasswordhashed);

        return registerAccountPort.register(user);
    }

}
