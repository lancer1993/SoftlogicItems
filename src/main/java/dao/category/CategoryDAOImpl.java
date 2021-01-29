/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.category;

import beans.Category;
import dao.SessionFactoryBuilder;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public class CategoryDAOImpl implements CategoryDAOInterface{
    
    private final SessionFactoryBuilder sessionFactoryBuilder = SessionFactoryBuilder.getInstance();

    @Override
    public List<Category> getCategories(Session session) {
        Query query = session.createQuery("FROM Category c");
        List<Category> list = (List<Category>) query.list();
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
