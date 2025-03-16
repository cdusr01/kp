package com.example.kp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer creditId;

    @ManyToOne
    @JoinColumn(name = "kind_credit_id")
    private KindCredit kindCredit;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "summa")
    private BigDecimal summa;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    // Геттеры и сеттеры
    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    public KindCredit getKindCredit() {
        return kindCredit;
    }

    public void setKindCredit(KindCredit kindCredit) {
        this.kindCredit = kindCredit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        if (!summa.isEmpty() && summa.matches("-?\\d+")) {
            BigDecimal sum = BigDecimal.valueOf(Long.parseLong(summa));
            if (sum.compareTo(BigDecimal.valueOf(0)) == 1) {
                this.summa = sum;
            } else {
                throw new IllegalArgumentException("Сумма должна быть больше 0!");
            }
        } else {
            throw new IllegalArgumentException("Сумма должна быть числом!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (new Date().after(date)) {
            this.date = date;
        }else {
            throw new IllegalArgumentException("Дата не должна превышать текущую!");
        }
    }

    @Override
    public String toString() {
        return "Credit{" +
                "creditId=" + creditId +
                ", kindCredit=" + kindCredit +
                ", client=" + client +
                ", summa=" + summa +
                ", date=" + date +
                '}';
    }
}
