package me.nzuguem.bot;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.annotations.Command;
import io.quarkiverse.githubapp.command.airline.CliOptions;
import io.quarkiverse.githubapp.command.airline.CommandOptions;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.kohsuke.github.GHEventPayload;

import java.io.IOException;
import java.util.List;

@Cli(name = IssueCliHandler.CLI_NAME, commands = { IssueCliHandler.Hello.class })
@CliOptions(
        aliases = { IssueCliHandler.CLI_NAME_ALIAS_1 },
        defaultCommandOptions =
            @CommandOptions(
                    scope = CommandOptions.CommandScope.ISSUES,
                    reactionStrategy = CommandOptions.ReactionStrategy.NONE,
                    executionErrorStrategy = CommandOptions.ExecutionErrorStrategy.COMMENT_MESSAGE,
                    executionErrorMessage = """
                            💔 Error during command execution "%s"
                            """
            )
)
class IssueCliHandler {

    static final String CLI_NAME = "@nbot";
    static final String CLI_NAME_ALIAS_1 = "@bot";

    static final List<String> CLI_NAMES = List.of(CLI_NAME, CLI_NAME_ALIAS_1);

    static final Logger LOGGER = Logger.getLogger(IssueCliHandler.class);

    interface Commands {
        void run(GHEventPayload.IssueComment issueCommentPayload) throws IOException;
    }

    @Command(name = "hello")
    static class Hello implements Commands {

        @Inject
        HelloService helloService;

        @Override
        public void run(GHEventPayload.IssueComment issueCommentPayload) throws IOException {

            LOGGER.info("Run cli -> <@bot | @nbot> hello");

            issueCommentPayload.getIssue()
                    .comment(helloService.reply() + " from 🤖");
        }
    }
}
