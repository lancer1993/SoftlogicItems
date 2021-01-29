/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Category;
import dao.category.CategoryDAOImpl;
import dao.category.CategoryDAOInterface;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public class CategoryService {
    
    private static CategoryDAOInterface categoryDAOInterface;
    
    public CategoryService(){
        categoryDAOInterface = new CategoryDAOImpl();
    }
    
    public Session getSession() {
        return categoryDAOInterface.openCurrentSession();
    }

    public Transaction getTransaction(Session session) {
        return categoryDAOInterface.openTransaction(session);
    }
    
    public List<Category> getCategories(){
        Session session = getSession();
        Transaction transaction = null;
        List<Category> categorys = new ArrayList<>();
        List<Category> list = new ArrayList<>();
        
        try{
            transaction = getTransaction(session);
            categorys = categoryDAOInterface.getCategories(session);
            transaction.commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            session.close();
            
            for(Category category : categorys){
                Category cat = new Category();
                cat.setIdCategory(category.getIdCategory());
                cat.setCategoryName(category.getCategoryName());
                list.add(cat);
            }
        }
        return list;
    }
    
}
