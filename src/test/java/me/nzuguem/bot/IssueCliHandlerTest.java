package me.nzuguem.bot;

import io.quarkiverse.githubapp.testing.GitHubAppTest;
import io.quarkiverse.githubapp.testing.GitHubAppTesting;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHEvent;
import org.mockito.Mockito;

import java.io.IOException;

@QuarkusTest
@GitHubAppTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class IssueCliHandlerTest {

    @Test
    void Should_add_issue_comment_When_execute_hello_command() throws IOException {

        GitHubAppTesting.when()
                .payloadFromClasspath("/github/issue-comment-command-created.json")
                .event(GHEvent.ISSUE_COMMENT)
                .then().github(mocks -> {
                    Mockito.verify(mocks.issue(1941238501))
                            .comment("Hello 👋🏽 from 🤖");
                });
    }
}
