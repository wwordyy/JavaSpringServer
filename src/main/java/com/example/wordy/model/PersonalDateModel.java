package com.example.wordy.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "PersonalDates")
@Entity
public class PersonalDateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonalDate;

    @NotNull(message = "Логин не может быть пустым!!!")
    @Size(min = 3, max = 20, message = "Логин должен содержать от 3 до 20 символов!!!")
    private String login;

    @NotNull(message = "Пароль не должен быть пустым!!!")
    @Size(min = 3, message = "Пароль должен содержать не менее 3 символов!!!")
    private String password;

    @OneToOne(mappedBy = "personalDate", cascade = CascadeType.ALL)
    private CustomerModel customer;

    private String role;


    public PersonalDateModel() {}

    public int getId() {
        return idPersonalDate;
    }

    public void setId(int id) {
        this.idPersonalDate = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
