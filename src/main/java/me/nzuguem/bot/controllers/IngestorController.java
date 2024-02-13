package me.nzuguem.bot.controllers;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import me.nzuguem.bot.configurations.llm.Ingestor;

@Path("/ingest")
public class IngestorController {

    private final Ingestor ingestor;

    public IngestorController(Ingestor ingestor) {

        this.ingestor = ingestor;
    }

    @POST
    public Response ingest() {

        this.ingestor.ingest();

        return Response.noContent().build();
    }
}
