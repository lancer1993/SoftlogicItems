/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Terance Wijesuriya
 */
public class SessionFactoryBuilder
{
    private static SessionFactoryBuilder instance;
    private static SessionFactory sessionFactory;
    
    private SessionFactoryBuilder()
    {
        buildConfig();
        System.out.println("Welcome");
    }
    
    private static void buildConfig()
    {
        Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    
    public static SessionFactoryBuilder getInstance()
    {
         if(instance == null) 
         {
            instance = new SessionFactoryBuilder();
            
         }
      return instance;
    }
    
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }  

}