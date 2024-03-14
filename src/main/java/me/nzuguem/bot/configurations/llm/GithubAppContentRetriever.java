package me.nzuguem.bot.configurations.llm;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import io.quarkiverse.langchain4j.pgvector.PgVectorEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import me.nzuguem.bot.exceptions.NotFoundRelevantException;

import java.util.List;

@ApplicationScoped
public class GithubAppContentRetriever implements ContentRetriever {

    private final EmbeddingStoreContentRetriever retriever;

    public GithubAppContentRetriever(PgVectorEmbeddingStore embeddingStore, EmbeddingModel embeddingModel) {
        this.retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(20)
                .minScore(0.9)
                .build();
    }

    @Override
    public List<Content> retrieve(Query query) {

        var result = this.retriever.retrieve(query);

        if (result.isEmpty()) {
            throw new NotFoundRelevantException("No information relevant to the configured score was found");
        }

        return result;
    }
}
