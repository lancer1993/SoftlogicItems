/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public interface DAOConnectionInterface {
    
    /**
     * This method is used to open a connection to database.
     * 
     * @return
     *      This returns, opened database connection instance.
     */
    public Session openCurrentSession();
    
    /**
     * This method is used to  begin transaction with exact table in the database.
     * 
     * @param session
     *          This session instance is used get opened connection to the database. 
     * @return
     *      This returns, opened transaction instance of the table
     */
    public Transaction openTransaction(Session session);
    
    
    
}
