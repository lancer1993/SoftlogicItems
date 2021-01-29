/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Category;
import beans.Item;
import dao.item.ItemDAOImpl;
import dao.item.ItemDAOInterface;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Terance Wijesuriya
 */
public class ItemService {

    private static ItemDAOInterface itemDAOInterface;

    public ItemService() {
        itemDAOInterface = new ItemDAOImpl();
    }

    public Session getSession() {
        return itemDAOInterface.openCurrentSession();
    }

    public Transaction getTransaction(Session session) {
        return itemDAOInterface.openTransaction(session);
    }

    public List<Item> getAllItemStocks() {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getAllItemStocks(session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }

    public List<Item> getItemsByCategory(int idCategory) {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getItemsByCategory(idCategory, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }

    public List<Item> getWarrantyItems(int warrantyStatus) {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getWarrantyItems(warrantyStatus, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }

    public List<Item> getItemsByCategoryAndPrice(int idCatgeory, double startPrice, double endPrice) {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getItemsByCategoryAndPrice(idCatgeory, startPrice, endPrice, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }

    public List<Item> getDeliverableItems(int deliveryStatus) {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getDeliverableItems(deliveryStatus, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }

    public List<Item> getItemsForCustomizations(int idCategory, int warrantyStatus, int deliveryStatus) {
        Session session = getSession();
        Transaction transaction = null;
        List<Object[]> items = new ArrayList<>();
        List<Item> list = new ArrayList<>();

        try {
            transaction = getTransaction(session);
            items = itemDAOInterface.getItemsForCustomizations(idCategory, warrantyStatus, deliveryStatus, session);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();

            for (Object[] obj : items) {
                Item item = new Item();
                item.setItemId(Integer.parseInt(obj[0].toString()));
                item.setProductCode(obj[1].toString());
                item.setItemName(obj[2].toString());
                item.setItemDescription(obj[3].toString());
                item.setItemPrice(new BigDecimal(obj[4].toString()));
                item.setActualPrice(new BigDecimal(obj[5].toString()));
                item.setDiscount(new BigDecimal(obj[6].toString()));
                item.setDateCreated(obj[7].toString());
                
                if (String.valueOf(obj[8]).trim() != null && !(String.valueOf(obj[8]).equals(""))) {
                    if(String.valueOf(obj[8]).trim().equals("1")){
                        item.setAvailability(true);
                    }else{
                        item.setAvailability(false);
                    }
                }
                
                item.setImageItem(obj[9].toString());
                
                if (String.valueOf(obj[10]).trim() != null && !(String.valueOf(obj[10]).equals(""))) {
                    if(String.valueOf(obj[10]).trim().equals("1")){
                        item.setDelivery(true);
                    }else{
                        item.setDelivery(false);
                    }
                }
                
                item.setDeliveryTimePeriod(obj[11].toString());
                
                if (String.valueOf(obj[12]).trim() != null && !(String.valueOf(obj[12]).equals(""))) {
                    if(String.valueOf(obj[12]).trim().equals("1")){
                        item.setWarranty(true);
                    }else{
                        item.setWarranty(false);
                    }
                }
                
                item.setWarrantyPeriod(obj[13].toString());
                item.setLastUpdated(obj[14].toString());
                
                int categoryId = Integer.parseInt(obj[15].toString());
                Category category = new Category();
                category.setIdCategory(categoryId);
                item.setCategory(category);
                
                list.add(item);
            }
        }
        return list;
    }
}
