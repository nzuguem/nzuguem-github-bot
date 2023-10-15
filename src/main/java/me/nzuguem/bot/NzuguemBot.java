package me.nzuguem.bot;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

public class NzuguemBot {

    private static final Logger LOGGER = Logger.getLogger(NzuguemBot.class);

    void onStartup(@Observes StartupEvent event) {
        LOGGER.info("""
                   _   _   _   _   _   _   _   _   _   _   _ \s
                  / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\\s
                 ( N | Z | U | G | U | E | M | - | B | O | T )
                  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/\s
                """.strip());
    }
}
