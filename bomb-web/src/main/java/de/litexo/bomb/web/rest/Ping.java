package de.litexo.bomb.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Andreas Hauschild
 */
@Path("/ping")
public class Ping {

    @GET
    @Produces("text/plain")
    public String ping(){
        return "successfully pinged";
    }
}
