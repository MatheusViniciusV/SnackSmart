package br.cefetmg.snacksmart.View.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE10Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}