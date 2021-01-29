/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.item;

import beans.Item;
import dao.SessionFactoryBuilder;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public class ItemDAOImpl implements ItemDAOInterface {

    private final SessionFactoryBuilder sessionFactoryBuilder = SessionFactoryBuilder.getInstance();

    @Override
    public List<Object[]> getAllItemStocks(Session session) {
        Query query = session.createSQLQuery("SELECT [itemId]\n"
                + ",[productCode]\n"
                + ",[itemName]\n"
                + ",[itemDescription]\n"
                + ",[itemPrice]\n"
                + ",[actualPrice]\n"
                + ",[discount]\n"
                + ",[dateCreated]\n"
                + ",[availability]\n"
                + ",[image_item]\n"
                + ",[delivery]\n"
                + ",[delivery_time_period]\n"
                + ",[warranty]\n"
                + ",[warranty_period]\n"
                + ",[lastUpdated]\n"
                + ",[categoryId]\n"
                + "FROM [dbo].[item]");
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getItemsByCategory(int idCategory, Session session) {
        Query query = session.createSQLQuery("SELECT [itemId]\n"
                + ",[productCode]\n"
                + ",[itemName]\n"
                + ",[itemDescription]\n"
                + ",[itemPrice]\n"
                + ",[actualPrice]\n"
                + ",[discount]\n"
                + ",[dateCreated]\n"
                + ",[availability]\n"
                + ",[image_item]\n"
                + ",[delivery]\n"
                + ",[delivery_time_period]\n"
                + ",[warranty]\n"
                + ",[warranty_period]\n"
                + ",[lastUpdated]\n"
                + ",[categoryId]\n"
                + "FROM [dbo].[item] WHERE [categoryId]=:categoryId");
        query.setParameter("categoryId", idCategory);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getWarrantyItems(int warrantyStatus, Session session) {
        if (warrantyStatus == 1) {
            Query query = session.createSQLQuery("SELECT [itemId]\n"
                    + ",[productCode]\n"
                    + ",[itemName]\n"
                    + ",[itemDescription]\n"
                    + ",[itemPrice]\n"
                    + ",[actualPrice]\n"
                    + ",[discount]\n"
                    + ",[dateCreated]\n"
                    + ",[availability]\n"
                    + ",[image_item]\n"
                    + ",[delivery]\n"
                    + ",[delivery_time_period]\n"
                    + ",[warranty]\n"
                    + ",[warranty_period]\n"
                    + ",[lastUpdated]\n"
                    + ",[categoryId]\n"
                    + "FROM [dbo].[item] WHERE [warranty]=:status");
            query.setParameter("status", true);
            List<Object[]> list = query.list();
            return list;
        } else {
            List<Object[]> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public List<Object[]> getItemsByCategoryAndPrice(int idCatgeory, double startPrice, double endPrice, Session session) {
        if (startPrice > 0 && endPrice > 0) {
            BigDecimal startb = new BigDecimal(startPrice, MathContext.DECIMAL64);
            BigDecimal endb = new BigDecimal(endPrice, MathContext.DECIMAL64);
            Query query = session.createSQLQuery("SELECT [itemId]\n"
                    + ",[productCode]\n"
                    + ",[itemName]\n"
                    + ",[itemDescription]\n"
                    + ",[itemPrice]\n"
                    + ",[actualPrice]\n"
                    + ",[discount]\n"
                    + ",[dateCreated]\n"
                    + ",[availability]\n"
                    + ",[image_item]\n"
                    + ",[delivery]\n"
                    + ",[delivery_time_period]\n"
                    + ",[warranty]\n"
                    + ",[warranty_period]\n"
                    + ",[lastUpdated]\n"
                    + ",[categoryId]\n"
                    + "FROM [dbo].[item] WHERE [categoryId]=:categoryId AND [actualPrice] BETWEEN :startPrice AND :endPrice");
            query.setParameter("categoryId", idCatgeory);
            query.setParameter("startPrice", startb);
            query.setParameter("endPrice", endb);
            List<Object[]> list = query.list();
            return list;
        } else {
            List<Object[]> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public List<Object[]> getDeliverableItems(int deliveryStatus, Session session) {
        if (deliveryStatus == 1) {
            Query query = session.createSQLQuery("SELECT [itemId]\n"
                    + ",[productCode]\n"
                    + ",[itemName]\n"
                    + ",[itemDescription]\n"
                    + ",[itemPrice]\n"
                    + ",[actualPrice]\n"
                    + ",[discount]\n"
                    + ",[dateCreated]\n"
                    + ",[availability]\n"
                    + ",[image_item]\n"
                    + ",[delivery]\n"
                    + ",[delivery_time_period]\n"
                    + ",[warranty]\n"
                    + ",[warranty_period]\n"
                    + ",[lastUpdated]\n"
                    + ",[categoryId]\n"
                    + "FROM [dbo].[item] WHERE [delivery]=:status");
            query.setParameter("status", true);
            List<Object[]> list = query.list();
            return list;
        } else {
            List<Object[]> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public List<Object[]> getItemsForCustomizations(int idCategory, int warrantyStatus, int deliveryStatus, Session session) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT [itemId]\n"
                + ",[productCode]\n"
                + ",[itemName]\n"
                + ",[itemDescription]\n"
                + ",[itemPrice]\n"
                + ",[actualPrice]\n"
                + ",[discount]\n"
                + ",[dateCreated]\n"
                + ",[availability]\n"
                + ",[image_item]\n"
                + ",[delivery]\n"
                + ",[delivery_time_period]\n"
                + ",[warranty]\n"
                + ",[warranty_period]\n"
                + ",[lastUpdated]\n"
                + ",[categoryId]\n"
                + " FROM [dbo].[item] WHERE [categoryId] =:categoryId\n");
        if (warrantyStatus == 1) {
            builder.append(" AND [warranty] = 1\n");
        }

        if (deliveryStatus == 1) {
            builder.append(" AND [delivery] = 1\n");
        }

        String sqlQuery = builder.toString();
        Query query = session.createSQLQuery(sqlQuery);
        query.setParameter("categoryId", idCategory);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public Session openCurrentSession() {
        return sessionFactoryBuilder.getSessionFactory().openSession();
    }

    @Override
    public Transaction openTransaction(Session session) {
        Transaction transaction = session.beginTransaction();
        return transaction;
    }

}
