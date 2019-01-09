/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansPackage;

/**
 *
 * @author omkarkamate
 */
public class TransactionItem {
  
    int transactionId;
    String empName;
    String serviceName;
    String clientName;
    String clientMobileNumber;
    int clientId;
    Float amount;
    String transactionDate;

    public TransactionItem(int transactionId, String empName, String serviceName, String clientName, String clientMobileNumber, int clientId, String transactionDate, Float amount) {
        this.transactionId = transactionId;
        this.empName = empName;
        this.serviceName = serviceName;
        this.clientName = clientName;
        this.clientMobileNumber = clientMobileNumber;
        this.clientId = clientId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMobileNumber() {
        return clientMobileNumber;
    }

    public void setClientMobileNumber(String clientMobileNumber) {
        this.clientMobileNumber = clientMobileNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
   
}
