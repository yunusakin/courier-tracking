package com.yunusakin.couriertracking.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURIER")
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TOKEN")
    private String token;


    @OneToMany(mappedBy = "courier")
    private List<CourierLog> courierLogs;

    public Courier() {
    }

    public Courier(Long id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<CourierLog> getCourierLogs() {
        return courierLogs;
    }

    public void setCourierLogs(List<CourierLog> courierLogs) {
        this.courierLogs = courierLogs;
    }
}
