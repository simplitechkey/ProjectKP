/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHelper;

import BeansPackage.AppointmentItem;
import BeansPackage.ClientItem;
import BeansPackage.TransactionItem;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;

/**
 *
 * @author omkarkamate
 */
public class DBDAO {
        public static int clientId=0;
       public static String phno="";
    public static void insertEmployee(int empId, String empName) {
        String sql = "insert into empTable ( empId, empName) values ('" + empId + "','" + empName + "');";
        try {
            DBUtil.dbexcuteQuery(sql);
        } catch (Exception e) {

        }
    }

    public static ObservableList<String> getAllEmployees() {
        ObservableList<String> data = FXCollections.observableArrayList();
        String sql = "select empName from empTable";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(rs.getString("empName"));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static void insertNewTransaction(String empName, String serviceName, String clientName, String clientMobileNumber, int clientId, String transactionDate, Float amount) {
        String sql = "insert into tableTransactions ( empName, serviceName,clientName,clientMobileNumber,clientId,transactionDate,amount) values ('" + empName + "','" + serviceName + "','" + clientName + "','" + clientMobileNumber + "'," + clientId + ",'" + transactionDate + "'," + amount + ");";

        try {
            DBUtil.dbexcuteQuery(sql);

        } catch (Exception e) {

        }

    }

    public static void totalAmountSum(String date) {
        String sql = "select sum(amount)as sum from tableTransactions where transactionDate= '" + date + "'";

    }

    public static ObservableList<TransactionItem> getAllTransactions() {
        ObservableList<TransactionItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableTransactions";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new TransactionItem(rs.getInt("transactionId"), rs.getString("empName"), rs.getString("serviceName"), rs.getString("clientName"), rs.getString("clientMobileNumber"), rs.getInt("clientId"), rs.getString("transactionDate"), rs.getFloat("amount")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static void deleteTransaction(int transactionId) {
        try {
            String sql = "delete from tableTransactions where transactionId = " + transactionId;

            DBUtil.dbexcuteQuery(sql);
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ObservableList<ClientItem> getAllClients() {
        ObservableList<ClientItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableClients";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new ClientItem(rs.getInt("clientId"), rs.getString("clientName"), rs.getString("clientMobileNumber"), rs.getString("clientEmail"), rs.getString("clientDob"), rs.getString("clientGender"), rs.getString("location"), rs.getDouble("amountBalance")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static void insertNewClient(String clientName, String clientMobileNumber, String clientEmail, String clientDOB, String clientGender, String location, double amountBalance) {
        String sql = "INSERT INTO tableClients(clientName,clientMobileNumber,clientEmail,clientDOB,clientGender,location,amountBalance) VALUES ('" + clientName + "','" + clientMobileNumber + "','" + clientEmail + "','" + clientDOB + "','" + clientGender + "','" + location + "'," + amountBalance + ");";

        try {
            DBUtil.dbexcuteQuery(sql);

        } catch (Exception e) {

        }

    }

    public static ObservableList<MenuItem> searchClientByName(String clientName) {
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableClients where clientName like  '%" + clientName + "%'";

        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new CheckMenuItem(rs.getString("clientName")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static ObservableList<MenuItem> searchClientByphNo(String phoneNumber) {
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableClients where clientMobileNumber like  '%" + phoneNumber + "%'";

        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new CheckMenuItem(rs.getString("clientMobileNumber")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static ResultSet getClientObjectByphNo(String phoneNumber) {
        String sql = "select * from tableClients where clientMobileNumber ='" + phoneNumber + "'";
        ResultSet rs = null;
        try {
            rs = DBUtil.dbExecute(sql);
            if (rs.next()) {
                return rs;
            }
        } catch (Exception e) {

        }

        return null;
    }

  

    public static ObservableList<AppointmentItem> getAllAppointments() {
        ObservableList<AppointmentItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableAppointments ";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new AppointmentItem(rs.getInt("appointmentId"), rs.getString("date"), rs.getString("timeSlot"), rs.getString("clientName"), rs.getString("clientMobileNumber"), rs.getString("empName"), rs.getString("appointmentStatus")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static ObservableList<AppointmentItem> getAllAppointmentsByDate(String date) {
        ObservableList<AppointmentItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableAppointments where date = '" + date + "'";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new AppointmentItem(rs.getInt("appointmentId"), rs.getString("date"), rs.getString("timeSlot"), rs.getString("clientName"), rs.getString("clientMobileNumber"), rs.getString("empName"), rs.getString("appointmentStatus")));
            }
        } catch (Exception e) {

        }
        return data;
    }

    public static void insertNewAppointment(String date, String timeSlot, String clientName, String clientMobileNumber, String empName, String appointmentStatus) {

        String sql = " INSERT INTO tableAppointments(date  , timeSlot, clientName , clientMobileNumber ,  empName, appointmentStatus) VALUES ('" + date + "','" + timeSlot + "','" + clientName + "','" + clientMobileNumber + "','" + empName + "','" + appointmentStatus + "');";

        if (!DBDAO.checkifbooked(empName, timeSlot, date)) {
            try {
                DBUtil.dbexcuteQuery(sql);

            } catch (Exception e) {

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("duplicate entry");
            alert.showAndWait();
        }

    }

    public static boolean checkifbooked(String empName, String timeSlot, String date) {

        String sql = "Select * from tableAppointments where empName= '" + empName + "' and  timeSlot = '" + timeSlot + "' and date= '" + date + "'";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static String getDateforAppointments() {

        String sql = "select * from todaysDate";
        String todaysDate = "";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            while (rs.next()) {
                todaysDate = rs.getString("date");
            }
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return todaysDate;

    }
    
      public static void putDateforAppointments(String todaysDate) {
          String sql1="delete from todaysDate";
        String sql = "insert into todaysDate (date) values ('"+todaysDate+"')";

        try {
            DBUtil.dbexcuteQuery(sql1);
            DBUtil.dbexcuteQuery(sql);
            
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

      

    }

    public static void insertService(String serviceName) {
        String sql = "insert into tableServices (serviceName) values ('"+serviceName+"')";

        try {
          
            DBUtil.dbexcuteQuery(sql);
            
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static ObservableList<String> getAllServices() {
        ObservableList<String> data = FXCollections.observableArrayList();
        String sql = "select * from tableServices ";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(rs.getString("serviceName"));
            }
        } catch (Exception e) {

        }
        return data;
    }
     
      public static ObservableList<MenuItem> getAllServicesbyName(String serviceName) {
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        String sql = "select * from tableServices where serviceName like  '%" + serviceName + "%'";
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new MenuItem(rs.getString("serviceName")));
            }
        } catch (Exception e) {

        }
        return data;
    }
      
      
      public static void deleteServicebyName(String serviceName)
      {
           String sql1="delete from tableServices where serviceName = '"+serviceName+"'";
        try {
            DBUtil.dbexcuteQuery(sql1);
                   
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

      
      }
      
      public static void deleteAppointmentById(int id){
          String sql1="delete from tableAppointments where appointmentId = "+id;
       
        try {
            DBUtil.dbexcuteQuery(sql1);
                   
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

          
      }
     
     public static ObservableList<TransactionItem> getAllTransactionByClientId (int id) {
         
        String sql = "select * from tableTransactions where clientId = "+id;
       ObservableList<TransactionItem> data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBUtil.dbExecute(sql);

            while (rs.next()) {
                data.add(new TransactionItem(rs.getInt("transactionId"), rs.getString("empName"), rs.getString("serviceName"), rs.getString("clientName"), rs.getString("clientMobileNumber"), rs.getInt("clientId"), rs.getString("transactionDate"), rs.getFloat("amount")));
            }
        } catch (Exception e) {

        }
        return data;
}

    public static void updateAmountBalanceofCLient(long clientId,Double amount) {
        String sql="update tableClients set amountbalance = "+amount+" where clientId = "+clientId;
         try {
            DBUtil.dbexcuteQuery(sql);
                   
        } catch (Exception ex) {
            Logger.getLogger(DBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
     
     
}
     
      
