package br.cefetmg.snacksmart.View.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
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