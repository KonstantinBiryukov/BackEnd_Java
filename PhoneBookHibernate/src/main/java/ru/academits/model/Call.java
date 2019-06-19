package ru.academits.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "call_id")
    private int id;

    @Column(name = "call_date")
    private String callDate;

    @Column(name = "call_phone")
    private String callPhone;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}