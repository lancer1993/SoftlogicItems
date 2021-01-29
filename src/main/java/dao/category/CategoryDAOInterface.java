/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.category;

import beans.Category;
import dao.DAOConnectionInterface;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Terance Wijesuriya
 */
public interface CategoryDAOInterface extends DAOConnectionInterface {
    
    List<Category> getCategories(Session session);
}
