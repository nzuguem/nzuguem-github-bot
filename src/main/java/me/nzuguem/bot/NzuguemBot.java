package me.nzuguem.bot;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import me.nzuguem.bot.configurations.llm.Ingestor;
import org.jboss.logging.Logger;

public class NzuguemBot {

    private static final Logger LOGGER = Logger.getLogger(NzuguemBot.class);

    void onStartup(@Observes StartupEvent event, Ingestor ingestor) {
        LOGGER.info("""
                   _   _   _   _   _   _   _   _   _   _   _ \s
                  / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\\s
                 ( N | Z | U | G | U | E | M | - | B | O | T )
                  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/\s
                """.strip());

        if (LaunchMode.current() != LaunchMode.TEST) {
            LOGGER.info("Ingesting Documents for RAG");
            ingestor.ingest();
        }

    }
}
