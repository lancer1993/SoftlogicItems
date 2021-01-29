/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.Category;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rest.filter.Secured;
import service.CategoryService;

/**
 *
 * @author Terance Wijesuriya
 */
@Path("/category")
public class CategoryJSONService {

    @GET
    @Secured
    @Path("/getCategories")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Category> getCategories() {
        CategoryService categoryService = new CategoryService();
        List<Category> list = categoryService.getCategories();
        return list;
    }
}
