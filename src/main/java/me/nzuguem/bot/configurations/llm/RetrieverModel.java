package me.nzuguem.bot.configurations.llm;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import io.quarkiverse.langchain4j.pgvector.PgVectorEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import me.nzuguem.bot.exceptions.NotFoundRelevantException;

import java.util.List;

@ApplicationScoped
public class RetrieverModel implements Retriever<TextSegment> {

    private final EmbeddingStoreRetriever retriever;

    public RetrieverModel(PgVectorEmbeddingStore embeddingStore, EmbeddingModel embeddingModel) {
        retriever = EmbeddingStoreRetriever.from(embeddingStore, embeddingModel, 20, 0.9);
    }

    @Override
    public List<TextSegment> findRelevant(String text) {

        var chunksResult = this.retriever.findRelevant(text);

        if (chunksResult.isEmpty()) {
            throw new NotFoundRelevantException("No information relevant to the configured score was found");
        }

        return chunksResult;
    }
}
