package me.nzuguem.bot.services.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;


@RegisterAiService(
        retrieverSupplier = RegisterAiService.BeanRetrieverSupplier.class
)
public interface QuarkusGithubAppExtensionDoc {

    @SystemMessage("""
            You're an expert at formulating answers for a Github issue.
            """)
    String ask(@UserMessage String prompt);

}
