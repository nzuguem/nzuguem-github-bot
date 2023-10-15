package me.nzuguem.bot;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String reply() {
        return "Hello 👋🏽";
    }
}
