package me.nzuguem.bot;

import io.quarkiverse.githubapp.event.Issue;
import io.quarkiverse.githubapp.event.IssueComment;
import org.jboss.logging.Logger;
import org.kohsuke.github.*;
import java.io.IOException;

class IssueEventHandler {

    static final Logger LOGGER = Logger.getLogger(IssueEventHandler.class);

    void onIssueOpen(@Issue.Opened
                     GHEventPayload.Issue issuePayload) throws IOException {

        var issue = issuePayload.getIssue();

        LOGGER.info("Issue opened -> " + issue.getTitle());

        issue.addLabels("open");
        issue.createReaction(ReactionContent.HEART);
    }

    void onIssueClosed(@Issue.Closed
                       GHEventPayload.Issue issuePayload) throws IOException {

        var issue = issuePayload.getIssue();

        LOGGER.info("Issue closed -> " + issue.getTitle());

        issue.addLabels("close");
    }

    void onIssueComment(@IssueComment.Created
                        GHEventPayload.IssueComment issueCommentPayload) throws IOException {

        var comment = issueCommentPayload.getComment();

        LOGGER.info("Issue comment created -> " + comment.getBody());

        if (this.canCreateReactionOnIssueComment(comment)) {
            comment.createReaction(ReactionContent.ROCKET);
        }

    }

    boolean canCreateReactionOnIssueComment(GHIssueComment comment) {

        var body = comment.getBody();
        var isCommand = IssueCliHandler.CLI_NAMES.stream()
                .anyMatch(cliName -> body.contains(cliName + " "));

        var isBotComment = body.contains("🤖") ;

        return !isCommand && !isBotComment;
    }
}
