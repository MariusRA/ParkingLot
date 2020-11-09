/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mariu
 */
@Stateless
public class UserBean {

     private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<UserDetails> getAllUsers() {
        LOG.info("getAllUsers");
        try {
            /*TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u", User.class);
            List<User> usersNoCast = typedQuery.getResultList();*/
            List<User> users = (List<User>) em.createQuery("SELECT u FROM User u").getResultList();
            return copyUsersToDetails(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    private List<UserDetails> copyUsersToDetails(List<User> users){
        List<UserDetails> detailsList=new ArrayList<>();
        for(User user:users){
            UserDetails userDetails=new UserDetails(user.getId(),user.getUsername(),user.getEmail(),user.getPosition());
            detailsList.add(userDetails);
        }
         return detailsList;   
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
