/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import DTO.UserFavQuoteDTO;
import DTO.UserFavQuotesDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Role;
import entities.User;
import entities.UserFavQuotes;
import facades.UserFavQuotesFacade;
import java.util.List;
import javax.persistence.EntityManager;
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
        UserFavQuotesDTO userFavQuotesList = uf.getUsersQuotes();
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
    
    
    //THIS IS FYFY TODO: DELETE ME 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/populate")
    public String populateDB() {      
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    
    User user = new User("user", "kode123");
    User admin = new User("admin", "kodeord");
    User both = new User("user_admin", "kodetest");
    UserFavQuotes favQuote = new UserFavQuotes("My fav quote"); 
    

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(favQuote);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("kode"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
      
        return GSON.toJson("DB is populated");       
    }

}
