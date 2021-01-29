/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.item;

import beans.Item;
import dao.DAOConnectionInterface;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Terance Wijesuriya
 */
public interface ItemDAOInterface extends DAOConnectionInterface {

    List<Object[]> getAllItemStocks(Session session);

    List<Object[]> getItemsByCategory(int idCategory, Session session);

    List<Object[]> getWarrantyItems(int warrantyStatus, Session session);

    List<Object[]> getItemsByCategoryAndPrice(int idCatgeory, double startPrice, double endPrice, Session session);
    
    List<Object[]> getDeliverableItems(int deliveryStatus, Session session);
    
    List<Object[]> getItemsForCustomizations(int idCategory, int warrantyStatus, int deliveryStatus, Session session);

}
