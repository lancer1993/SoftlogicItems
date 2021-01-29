/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.User;
import custom_beans.UserWithPasswordSecret;
import dao.Common;
import dao.user.UserDAOImpl;
import dao.user.UserDAOInterface;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public class UserService {

    private static UserDAOInterface userDAOInterface;

    public UserService() {
        userDAOInterface = new UserDAOImpl();
    }

    public Session getSession() {
        return userDAOInterface.openCurrentSession();
    }

    public Transaction getTransaction(Session session) {
        return userDAOInterface.openTransaction(session);
    }

    public List<User> getAllUsers() {
        Session session = getSession();
        Transaction transaction = null;
        List<User> list = new ArrayList<>();
        List<User> users = new ArrayList<>();
        try {
            transaction = getTransaction(session);
            users = userDAOInterface.getAllUsers(session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (User u : users) {

                User user = new User();
                user.setIdUser(u.getIdUser());
                user.setFirstName(u.getFirstName());
                user.setLastName(u.getLastName());
                user.setEmail(u.getEmail());
                user.setPassword(u.getPassword());
                user.setSalt(u.getSalt());
                user.setDateCreated(u.getDateCreated());
                user.setLastUpdated(u.getLastUpdated());

                list.add(user);

            }
        }
        return list;
    }

    public User getUserById(int idUser) {
        Session session = getSession();
        Transaction transaction = null;
        User u = new User();
        User user = new User();
        try {
            transaction = getTransaction(session);
            u = userDAOInterface.getUserByID(idUser, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            user.setIdUser(u.getIdUser());
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setEmail(u.getEmail());
            user.setPassword(u.getPassword());
            user.setSalt(u.getSalt());
            user.setDateCreated(u.getDateCreated());
            user.setLastUpdated(u.getLastUpdated());
        }

        return user;
    }

    public String saveUser(User user) {
        Session session = getSession();
        Transaction transaction = null;
        String result = "";
        try {
            String salt = Common.getSalt();
            user.setSalt(salt);

            //Hash the password
            String hashedPassword = Common.get_SHA_256_SecurePassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);

            transaction = getTransaction(session);
            userDAOInterface.saveUser(user, session);
            transaction.commit();
            result = Common.SAVE_SUCCESS;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                result = Common.SAVE_ROLLBACK;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public User getUserByEmail(String email) {
        Session session = getSession();
        Transaction transaction = null;
        User user = new User();
        List<Object[]> list = new ArrayList<>();
        try {
            transaction = getTransaction(session);
            list = userDAOInterface.getUserByEmail(email, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            
            for(Object[] obj: list){
                user.setIdUser(Integer.parseInt(obj[0].toString()));
                user.setFirstName(obj[2].toString());
                user.setLastName(obj[3].toString());
                user.setEmail(obj[5].toString());
                user.setPassword(obj[1].toString());
                user.setSalt(obj[4].toString());
                user.setDateCreated(obj[6].toString());
                user.setLastUpdated(obj[7].toString());
            }
        }
        return user;
    }

    public String updateUserPassword(User user) {
        Session session = getSession();
        Transaction transaction = null;
        String result = "";
        try {
            String salt = Common.getSalt();
            user.setSalt(salt);

            //Hash the password
            String hashedPassword = Common.get_SHA_256_SecurePassword(user.getPassword(), salt);
            user.setPassword(hashedPassword);

            transaction = getTransaction(session);
            userDAOInterface.updateUserPassword(user, session);
            transaction.commit();
            result = Common.SAVE_SUCCESS;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                result = Common.SAVE_ROLLBACK;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public String resetPassword(UserWithPasswordSecret userWithPasswordSecret) {
        Session session = getSession();
        Transaction transaction = null;
        String result = "";
        try {
            String salt = Common.getSalt();
            userWithPasswordSecret.getUser().setSalt(salt);

            //Hash the password
            String hashedPassword = Common.get_SHA_256_SecurePassword(userWithPasswordSecret.getUser().getPassword(), salt);
            userWithPasswordSecret.getUser().setPassword(hashedPassword);

            transaction = getTransaction(session);
            userDAOInterface.resetPassword(userWithPasswordSecret.getUser(), session);
            transaction.commit();
            result = Common.SAVE_SUCCESS;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
                result = Common.SAVE_ROLLBACK;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
