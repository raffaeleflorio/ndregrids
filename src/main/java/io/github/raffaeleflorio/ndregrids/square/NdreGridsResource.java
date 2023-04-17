package io.github.raffaeleflorio.ndregrids.square;

import io.github.raffaeleflorio.ndregrids.qute.QuteMedia;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ndregrids")
public final class NdreGridsResource {

    private final static Integer COUNT = 10;


    private final Template template;

    public NdreGridsResource(@Location("ndregrids.html") final Template template) {
        this.template = template;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance ndregrids() {
        return new NdreGrids(COUNT).show(new QuteMedia(this.template)).output();
    }
}
