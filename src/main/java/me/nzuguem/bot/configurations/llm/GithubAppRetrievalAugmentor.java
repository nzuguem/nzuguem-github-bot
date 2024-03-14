package me.nzuguem.bot.configurations.llm;

import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.function.Supplier;

@ApplicationScoped
public class GithubAppRetrievalAugmentor implements Supplier<RetrievalAugmentor> {

    private final GithubAppContentRetriever retriever;

    public GithubAppRetrievalAugmentor(GithubAppContentRetriever retriever) {
        this.retriever = retriever;
    }

    @Override
    public RetrievalAugmentor get() {
        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(this.retriever)
                .build();
    }
}
