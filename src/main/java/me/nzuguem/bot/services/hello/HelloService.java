package me.nzuguem.bot.services.hello;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String reply() {
        return "Hello 👋🏽";
    }
}
