/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import entities.UserFavQuotes;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class UserFavQuoteDTO {
    private String userQuote; 

    public UserFavQuoteDTO(UserFavQuotes UserFavQuotes) {
        this.userQuote = UserFavQuotes.getQuote();
    }

    public String getUserQuote() {
        return userQuote;
    }

    public void setUserQuote(String userQuote) {
        this.userQuote = userQuote;
    }
    
    
}
