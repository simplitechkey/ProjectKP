/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHelper;

import BeansPackage.ClientItem;
import BeansPackage.TransactionItem;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author omkarkamate
 */
public class DBDAO {
    
    
    public static void insertEmployee(int empId, String empName)
    {
        String sql = "insert into empTable ( empId, empName) values ('"  + empId + "','" + empName + "');";
        try {
            DBUtil.dbexcuteQuery(sql);
        } catch (Exception e) {

        }
    }
    
    
     public static   ObservableList<String> getAllEmployees()
    {
          ObservableList<String> data=FXCollections.observableArrayList();
        String sql = "select empName from empTable";
        try {
            ResultSet rs= DBUtil.dbExecute(sql);
            
            while(rs.next()){
                data.add(rs.getString("empName"));
            }
        } catch (Exception e) {

        }
        return data;
    }
     
     
       public static  void insertNewTransaction(String empName,String serviceName,String clientName,String transactionDate, Float amount )
    {
          ObservableList<String> data=FXCollections.observableArrayList();
       String sql = "insert into tableTransactions ( empName, serviceName,clientName,transactionDate,amount) values ('" + empName + "','" + serviceName + "','" + clientName + "','" + transactionDate+ "'," + amount + ");";
   
        try {
        DBUtil.dbexcuteQuery(sql);
            
          
        } catch (Exception e) {

        }
    
    }
       public static void totalAmountSum(String date){
           String sql="select sum(amount)as sum from tableTransactions where transactionDate= '"+date+"'";

       }
       
         public static   ObservableList<TransactionItem> getAllTransactions()
    {
          ObservableList<TransactionItem> data=FXCollections.observableArrayList();
        String sql = "select * from tableTransactions";
        try {
            ResultSet rs= DBUtil.dbExecute(sql);
            
            while(rs.next()){
                data.add(new TransactionItem(rs.getInt("transactionId"),rs.getString("empName"),rs.getString("serviceName"),rs.getString("clientName"),rs.getString("transactionDate"),rs.getFloat("amount")));
            }
        } catch (Exception e) {

        }
        return data;
    }
         
         public static void deleteTransaction(int transactionId){
        try {
            String sql = "delete from tableTransactions where transactionId = "+transactionId;
            
            DBUtil.dbexcuteQuery(sql);
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
             
         }
    
         
         public static   ObservableList<ClientItem> getAllClients()
    {
          ObservableList<ClientItem> data=FXCollections.observableArrayList();
        String sql = "select * from tableClients";
        try {
            ResultSet rs= DBUtil.dbExecute(sql);
            
            while(rs.next()){
                data.add(new ClientItem(rs.getInt("clientId"),rs.getString("clientName"),rs.getString("clientMobileNumber"),rs.getString("clientEmail"),rs.getString("clientDob"),rs.getString("clientGender"),rs.getString("location"),rs.getDouble("amountBalance")));
            }
        } catch (Exception e) {

        }
        return data;
    }
         
         
          public static  void insertNewClient(String clientName, String clientMobileNumber, String clientEmail, String clientDOB, String clientGender, String location, double amountBalance)
    {
          ObservableList<String> data=FXCollections.observableArrayList();
       String sql = "INSERT INTO tableClients(clientName,clientMobileNumber,clientEmail,clientDOB,clientGender,location,amountBalance) VALUES ('" + clientName + "','" + clientMobileNumber + "','" + clientEmail + "','" + clientDOB+ "','" + clientGender +"','" + location+"'," + amountBalance+");";
   
        try {
        DBUtil.dbexcuteQuery(sql);
            
          
        } catch (Exception e) {

        }
    
    }
    
}
