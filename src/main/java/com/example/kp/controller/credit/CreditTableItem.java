package com.example.kp.controller.credit;

import com.example.kp.model.Client;
import com.example.kp.model.Credit;
import com.example.kp.model.KindCredit;
import javafx.beans.property.SimpleStringProperty;

public class CreditTableItem {
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty address;
    private SimpleStringProperty kindCredit;
    private SimpleStringProperty name;
    private SimpleStringProperty summa;
    private SimpleStringProperty date;

    private Credit credit;


    public CreditTableItem(Credit credit) {
        this.kindCredit = new SimpleStringProperty(credit.getKindCredit().getName());
        this.name = new SimpleStringProperty(credit.getClient().getName());
        this.summa = new SimpleStringProperty(credit.getSumma().toString());
        this.date = new SimpleStringProperty(credit.getDate().toString());
        this.email = new SimpleStringProperty(credit.getClient().getContact());
        this.address = new SimpleStringProperty(credit.getClient().getAddress());
        this.phone = new SimpleStringProperty(credit.getClient().getPhone());
        this.credit = credit;
    }

    public String getKindCredit() {
        return kindCredit.get();
    }

    public SimpleStringProperty kindCreditProperty() {
        return kindCredit;
    }

    public void setKindCredit(String kindCredit) {
        this.kindCredit.set(kindCredit);
    }



    public String getSumma() {
        return summa.get();
    }

    public SimpleStringProperty summaProperty() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa.set(summa);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}

