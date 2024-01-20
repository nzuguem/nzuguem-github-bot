package me.nzuguem.bot.services.llm;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

@RegisterAiService(
        retrieverSupplier = RegisterAiService.BeanRetrieverSupplier.class
)
public interface QuarkusGithubAppExtensionDoc {

    String SYSTEM_MESSAGE = "You're an expert at formulating answers for a Github issue.";

    @SystemMessage(SYSTEM_MESSAGE)
    @UserMessage("""
            Give an answer to the question raised by the Github problem title "{title}", and consider this description "{description}".
            """)
    @Fallback(value = AskFallback.class)
    String ask(@MemoryId Long memoryId, String title, String description);

    @SystemMessage(SYSTEM_MESSAGE)
    @UserMessage("""
            Give an answer to the question raised by the Github problem title "{title}".
            """)
    @Fallback(value = AskFallback.class)
    String ask(@MemoryId Long memoryId, String title);

    class AskFallback implements FallbackHandler<String> {

        public String handle(ExecutionContext context) {

            Log.warn(context.getFailure().getMessage(), context.getFailure());

            return "Thank you for your issue, we'll get back to you shortly.";
        }
    }

}