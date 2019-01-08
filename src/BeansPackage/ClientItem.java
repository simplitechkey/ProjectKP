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
public class ClientItem {
   int clientId;
    String clientName;
    String clientMobileNumber;
    String clientEmail;
    String clientDOB;
    String clientGender;
    String location;
    double amountBalance;

    public ClientItem(int clientId, String clientName, String clientMobileNumber, String clientEmail, String clientDOB, String clientGender, String location, double amountBalance) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientMobileNumber = clientMobileNumber;
        this.clientEmail = clientEmail;
        this.clientDOB = clientDOB;
        this.clientGender = clientGender;
        this.location = location;
        this.amountBalance = amountBalance;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientDOB() {
        return clientDOB;
    }

    public void setClientDOB(String clientDOB) {
        this.clientDOB = clientDOB;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAmountBalance() {
        return amountBalance;
    }

    public void setAmountBalance(double amountBalance) {
        this.amountBalance = amountBalance;
    }
    
}
