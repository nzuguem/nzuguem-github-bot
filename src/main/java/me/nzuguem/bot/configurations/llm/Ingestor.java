package me.nzuguem.bot.configurations.llm;

import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkiverse.langchain4j.pgvector.PgVectorEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import me.nzuguem.bot.configurations.clients.QuarkusGithubAppExtensionDocClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class Ingestor {

    private final QuarkusGithubAppExtensionDocClient quarkusGithubAppExtensionDocClient;

    // The embedding store (the database)
    private final PgVectorEmbeddingStore embeddingStore;


    // The embedding model (how is computed the vector of a document)
    private final EmbeddingModel embeddingModel;

    public Ingestor(@RestClient QuarkusGithubAppExtensionDocClient quarkusGithubAppExtensionDocClient,
                    PgVectorEmbeddingStore embeddingStore,
                    EmbeddingModel embeddingModel) {
        this.quarkusGithubAppExtensionDocClient = quarkusGithubAppExtensionDocClient;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public void ingest() {

        var documents = this.quarkusGithubAppExtensionDocClient.getContentAsDocument();

        var ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .documentSplitter(DocumentSplitters.recursive(100, 0))
                .build();

        ingestor.ingest(documents);
    }

}
