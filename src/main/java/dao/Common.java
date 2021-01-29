/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.joda.time.*;

/**
 *
 * @author Terance Wijesuriya
 */
public class Common 
{    
    public static String SAVE_SUCCESS = "Save Success";
    public static String SAVE_ROLLBACK = "Error occured. Failed to save";
    public static String SAVE_ROLLBACK_FAILED = "Error occured. Failed to Rollback";
    public static String DELETE_SUCCESS = "Delete Success";
    public static String DELETE_ROLLBACK = "Error Occured. Failed to Delete";
    public static String DELETE_ROLLBACK_FAILED = "Error Occured. Failed to Rollback";
    
    public static java.sql.Timestamp getSQLCurrentTimeStamp()
    {
        java.util.Date date= new java.util.Date();
	Timestamp t= new Timestamp(date.getTime());
        System.out.println(t);
        return t;
    }
    
    public static Double calcBMI(double heightInFt,double weight)
    {
        double heightInInches=heightInFt*12;
        double bmiTotal=(weight/(heightInInches*heightInFt))*703;        
        return bmiTotal;
    }
    
//    public static java.sql.Date convertJavaDateToSQL(String javaDate) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsed = null;
//        java.sql.Date sqlDate = null;
//        try {
//            parsed = format.parse(javaDate);
//            sqlDate = new java.sql.Date(parsed.getTime());
//        } catch (ParseException ex) {
//            //Logger.getLogger(Common.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return sqlDate;
//    }
    
    public static Date convertJavaDateToSQL(String javaDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(javaDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return parsed;
    }
    
    public static int calcAge(java.sql.Date bday)
    {
        
        LocalDate birthdate = new LocalDate (bday);
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthdate, now);
        
        return age.getYears();
    }
    
    //Use this method to get the current date
    public static java.sql.Date getCurrentDateSQL()
    {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
        
    }
    
    public static long compareTwoTimeStamps(java.sql.Timestamp oldTime)
    {
        java.util.Date date= new java.util.Date();    
        java.sql.Timestamp currentTime=  new Timestamp(date.getTime());

        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;   
        return diffSeconds;
    }
    
    public static int getNumberOfDatesBetween(Date startDate, Date endDate)
    {
        int days = Days.daysBetween(new LocalDate(startDate), new LocalDate(endDate)).getDays();
        return days;
    } 
    
    public static Date convertDateToTimestamp(Date convertDate) {
        System.out.println("DATE - " + convertDate);
        long time = convertDate.getTime();
        java.sql.Timestamp timestamp = new Timestamp(time);
        return timestamp;
    }
    
    public static String get_SHA_256_SecurePassword(String passwordToHash, String salt) throws Exception {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest((passwordToHash + salt).getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException("No Such Hash Algorithm");
        }
        return generatedPassword;
    }

    public static String getSalt() throws NoSuchAlgorithmException {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);

        return generatedString;
    }
    
}
