package me.nzuguem.bot.services.llm;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;


@RegisterAiService(
        retrieverSupplier = RegisterAiService.BeanRetrieverSupplier.class
)
public interface QuarkusGithubAppExtensionDoc {

    String ask(@UserMessage String prompt);

}
