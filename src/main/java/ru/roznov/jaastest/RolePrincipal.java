package ru.roznov.jaastest;

import java.security.Principal;

public class RolePrincipal implements Principal {
    private String name;

    public RolePrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
