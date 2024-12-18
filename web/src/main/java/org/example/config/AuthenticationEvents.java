package org.example.config;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    public static String successName = null;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        successName = success.getAuthentication().getName();
        System.out.println("--------success-------"+ success.getAuthentication().getName());
        // ...
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        System.out.println("--------failures-------"+ failures.getAuthentication().getName());
        // ...
    }
}
