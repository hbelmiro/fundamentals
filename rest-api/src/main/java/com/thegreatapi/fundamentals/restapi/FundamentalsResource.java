package com.thegreatapi.fundamentals.restapi;

import com.thegreatapi.fundamentals.core.FundamentalsService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class FundamentalsResource {

    @Inject
    FundamentalsService s;

    @GET
    @Path("{country}/{stock}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStock(@PathParam("country") String country, @PathParam("stock") String stock) {
        return "Hello from " + country + " for " + stock + ": " + s;
    }
}
