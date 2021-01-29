/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import beans.User;
import dao.DAOConnectionInterface;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Terance Wijesuriya
 */
public interface UserDAOInterface extends DAOConnectionInterface {
    
    List<User> getAllUsers(Session session);
    
    User getUserByID(int idUser,Session session);
    
    int saveUser(User user,Session session);
    
    List<Object[]> getUserByEmail(String email, Session session);
    
    void updateUserPassword(User user, Session session);
    
    void resetPassword(User user, Session session);
    
}
