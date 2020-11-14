/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import DTO.UserFavQuoteDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.User;
import entities.UserFavQuotes;
import facades.UserFavQuotesFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("quotes")
public class UserFavQuoteRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final UserFavQuotesFacade uf = UserFavQuotesFacade.getUserFavQuotesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/all")
    public String getUserFavQuote() {      
        List<UserFavQuoteDTO> userFavQuotesList = uf.getUsersQuotes();
        return GSON.toJson(userFavQuotesList);       
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("users/")
    public String addUserFavQuote(String quote) {
        UserFavQuotes ufq = GSON.fromJson(quote, UserFavQuotes.class);
        UserFavQuoteDTO ufqDTO = new UserFavQuoteDTO(ufq); 
        return GSON.toJson(uf.addUserQuote(ufqDTO.getUserQuote()));
              
    }

}
