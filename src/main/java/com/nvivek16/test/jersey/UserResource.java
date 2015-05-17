package com.nvivek16.test.jersey;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;
@Path("/users")
public class UserResource {
  UserDAO dao = new UserDAO();
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<User> findAll() {
    return dao.findAll();
  }
 
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public User create(User user){
    dao.save(user);
    return user;
  }
  
  @PUT
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public User update(User user){
    dao.save(user);
    return user;
  }

  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void delete(@PathParam("id") int id){
    dao.remove(id);
  }
}
