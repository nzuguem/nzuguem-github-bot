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

    @GET
    @Path("{fileName}")
    String getContent(@PathParam("fileName") String fileName);

    @GET
    @Path("includes/{fileName}")
    String getContentOnIncludes(@PathParam("fileName") String fileName);

    default Document getContentAsDocument(String fileName, boolean includes, String baseUri) {

        var content = includes ? this.getContentOnIncludes(fileName) : this.getContent(fileName);

        var metadata =  UrlSource.from("%s/%s".formatted(baseUri, fileName))
                .metadata()
                .add("source", "quarkus-github-app-extension");

        return new Document(content, metadata);
    }

    default List<Document>  getContentAsDocument() {

        var documents = new ArrayList<Document>();

        var baseUri = ConfigProvider.getConfig().getValue("quarkus.rest-client.quarkus-github-app-extension-doc-client.url", String.class);

        documents.add(this.getContentAsDocument("commands.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("create-github-app.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("developer-reference.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("index.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("push-to-production.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("register-github-app.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("replay-ui.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("testing.adoc", false, baseUri));
        documents.add(this.getContentAsDocument("quarkus-github-app.adoc", true, baseUri));

        return documents;
    }
}
