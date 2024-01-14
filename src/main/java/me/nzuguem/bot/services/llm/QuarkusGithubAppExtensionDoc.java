package me.nzuguem.bot.services.llm;

import dev.langchain4j.service.MemoryId;
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
    @UserMessage("""
            Gives an answer to the concern raised by this Github issue title "{title}", including this issue description "{description}"
            """)
    String ask(@MemoryId Long memoryId, String title, String description);
}