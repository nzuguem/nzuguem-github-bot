package me.nzuguem.bot;

import io.quarkiverse.githubapp.testing.GitHubAppTest;
import io.quarkiverse.githubapp.testing.GitHubAppTesting;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHEvent;
import org.kohsuke.github.ReactionContent;
import org.mockito.Mockito;

import java.io.IOException;

@QuarkusTest
@GitHubAppTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class IssueEventHandlerTest {

    @Test
    void Should_add_open_label_When_issue_opened() throws IOException {

        GitHubAppTesting.when()
                .payloadFromClasspath("/github/issue-opened.json")
                .event(GHEvent.ISSUES)
                .then().github(mocks -> {
                    Mockito.verify(mocks.issue(1941238501))
                            .addLabels("open");
                });
    }

    @Test
    void Should_add_heart_reaction_label_When_issue_opened() throws IOException {

        GitHubAppTesting.when()
                .payloadFromClasspath("/github/issue-opened.json")
                .event(GHEvent.ISSUES)
                .then().github(mocks -> {
                    Mockito.verify(mocks.issue(1941238501))
                            .createReaction(ReactionContent.HEART);
                });
    }

    @Test
    void Should_add_rocket_reaction_When_issue_comment_created() throws IOException {

        GitHubAppTesting.when()
                .payloadFromClasspath("/github/issue-comment-created.json")
                .event(GHEvent.ISSUE_COMMENT)
                .then().github(mocks -> {
                    Mockito.verify(mocks.issueComment(1760901577))
                            .createReaction(ReactionContent.ROCKET);
                });
    }

    @Test
    void Should_add_close_label_When_issue_closed() throws IOException {

        GitHubAppTesting.when()
                .payloadFromClasspath("/github/issue-closed.json")
                .event(GHEvent.ISSUES)
                .then().github(mocks -> {
                    Mockito.verify(mocks.issue(1941238501))
                            .addLabels("close");
                });
    }
}
