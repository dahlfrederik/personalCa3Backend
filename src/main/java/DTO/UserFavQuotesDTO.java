/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import entities.UserFavQuotes;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class UserFavQuotesDTO {
  

    private List<UserFavQuoteDTO> all = new ArrayList();

    public UserFavQuotesDTO(List<UserFavQuotes> userEnitites) {
        userEnitites.forEach((u) -> {
            all.add(new UserFavQuoteDTO(u));
        });
    }

    public List<UserFavQuoteDTO> getAll() {
        return all;
    }



}
