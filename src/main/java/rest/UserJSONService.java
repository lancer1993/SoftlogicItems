/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.User;
import common.Secret;
import custom_beans.UserWithPasswordSecret;
import dao.Common;
import service.UserService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.filter.Secured;

/**
 *
 * @author Terance Wijesuriya
 */
@Path("/user")
public class UserJSONService {

    @POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        UserService service = new UserService();
        String result = service.saveUser(user);
        if (result.equals(Common.SAVE_SUCCESS)) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(result).build();
        }
    }
    
    @GET
    @Secured
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<User> getAllUsers() {
        UserService userService = new UserService();
        List<User> list = userService.getAllUsers();
        return list;
    }

    @GET
    @Secured
    @Path("/getUserById/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public User getUserById(@PathParam("id") int id) {
        UserService userService = new UserService();
        User user = userService.getUserById(id);
        return user;
    }
    
    @GET
    @Secured
    @Path("/getUserByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public User getUserByEmail(@PathParam("email")String email) {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(email);
        return user;
    }
    
    @POST
    @Secured
    @Path("/updateUserPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserPassword(User user) {
        UserService userService = new UserService();
        String result = userService.updateUserPassword(user);
        if (result.equals(Common.SAVE_SUCCESS)) {
            return Response.status(Response.Status.OK).entity(result).build();
        } else {
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(result).build();
        }
    }
    
    @POST
    @Path("/resetPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(UserWithPasswordSecret userWithPasswordSecret) {

        if (userWithPasswordSecret.getPasswordSecret().equals(Secret.PASSWORD_RESET_SECRET)) {
            UserService userService = new UserService();
            String result = userService.resetPassword(userWithPasswordSecret);
            if (result.equals(Common.SAVE_SUCCESS)) {
                return Response.status(Response.Status.OK).entity(result).build();
            } else {
                return Response.status(Response.Status.EXPECTATION_FAILED).entity(result).build();
            }
        } else {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("FAILED").build();
        }

    }    
}
