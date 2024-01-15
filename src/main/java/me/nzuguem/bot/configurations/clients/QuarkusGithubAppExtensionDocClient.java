package me.nzuguem.bot.configurations.clients;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.source.UrlSource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.ArrayList;
import java.util.List;

@Path("")
@RegisterRestClient(configKey = "quarkus-github-app-extension-doc-client")
@Produces(MediaType.TEXT_PLAIN)
public interface QuarkusGithubAppExtensionDocClient {

    List<String> FILE_NAMES = List.of(
            "commands.adoc", "create-github-app.adoc", "developer-reference.adoc",
            "index.adoc", "push-to-production.adoc", "register-github-app.adoc",
            "replay-ui.adoc", "testing.adoc", "includes/quarkus-github-app.adoc"
    );

    @GET
    @Path("{fileName}")
    String getContent(@PathParam("fileName") String fileName);


    default Document getContentAsDocument(String fileName, String baseUri) {

        var content = this.getContent(fileName);

        var metadata =  UrlSource.from("%s/%s".formatted(baseUri, fileName))
                .metadata()
                .add("source", "quarkus-github-app-extension");

        return new Document(content, metadata);
    }

    default List<Document>  getContentAsDocument() {

        var documents = new ArrayList<Document>();

        var baseUri = ConfigProvider.getConfig().getValue("quarkus.rest-client.quarkus-github-app-extension-doc-client.url", String.class);

        FILE_NAMES.forEach(fileName -> documents.add(getContentAsDocument(fileName, baseUri)));

        return documents;
    }
}
