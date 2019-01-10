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
public class AppointmentItem {

    int appointmentId;
    String date;
    String timeSlot;
    String clientName;
    String clientMobileNumber;
    String empName;
    String appointmentStatus;

    public AppointmentItem(int appointmentId, String date, String timeSlot, String clientName, String clientMobileNumber, String empName, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.clientName = clientName;
        this.clientMobileNumber = clientMobileNumber;
        this.empName = empName;
        this.appointmentStatus = appointmentStatus;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

}
