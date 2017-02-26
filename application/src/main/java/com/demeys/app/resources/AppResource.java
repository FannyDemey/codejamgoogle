package com.demeys.app.resources;

import com.demeys.app.services.AppService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by TODO : set user name on TODO : set date.
 */
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class AppResource {

    private final AppService appService;

    @Inject
    public AppResource(AppService appService) {
        this.appService = appService;
    }

    @GET
    public Response getAppName( @Context UriInfo uriInfo)  {
        return Response.ok(appService.getAppName(),MediaType.APPLICATION_JSON).build();
    }

}
