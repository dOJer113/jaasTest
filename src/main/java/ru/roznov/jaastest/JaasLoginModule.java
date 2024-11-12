package ru.roznov.jaastest;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class JaasLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    private String username;
    private char[] password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Имя пользователя");
        callbacks[1] = new PasswordCallback("Пароль", false);
        try {
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = ((PasswordCallback) callbacks[1]).getPassword();
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getLocalizedMessage());
        }
        if ("admin".equals(username) && "12345".equals(new String(password))) {
            succeeded = true;
            return true;
        } else {
            succeeded = false;
            username = null;
            password = null;
            throw new LoginException("Неверное имя пользователя или пароль");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (!succeeded) {
            return false;
        } else {
            subject.getPrincipals().add(new UserPrincipal(username));
            commitSucceeded = true;
            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}