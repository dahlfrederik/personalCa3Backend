
package facades;

import DTO.UserFavQuoteDTO;
import DTO.UserFavQuotesDTO;
import entities.User;
import entities.UserFavQuotes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import utils.EMF_Creator;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class UserFavQuotesFacade {
    private static EntityManagerFactory emf;
    private static UserFavQuotesFacade instance;
    
    
    public static UserFavQuotesFacade getUserFavQuotesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFavQuotesFacade();
        }
        return instance;
    }
    
    public UserFavQuotesDTO getUsersQuotes(){
        EntityManager em = emf.createEntityManager(); 
        try {
            Query query = em.createQuery("SELECT u FROM UserFavQuotes u");                 
            List<UserFavQuotes> userFavQuotesList = query.getResultList();
            
            return new UserFavQuotesDTO(userFavQuotesList); 
        } finally {
            em.close();
        }
    }
  
    public UserFavQuoteDTO addUserQuote(String quote){
        EntityManager em = emf.createEntityManager(); 
        
        UserFavQuotes ufq = new UserFavQuotes(quote); 
        
        try{
            em.getTransaction().begin();
            
            em.persist(ufq); 
            
            em.getTransaction().commit();
            return new UserFavQuoteDTO(ufq); 
        }finally{
            em.close(); 
        }
        
    }
    
}
