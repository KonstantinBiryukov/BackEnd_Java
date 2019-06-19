package ru.academits.dto;

public class CallDto {
    private int id;
    private String callDate;
    private String callPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallNumber() {
        return callPhone;
    }

    public void setCallNumber(String callNumber) {
        this.callPhone = callNumber;
    }
}