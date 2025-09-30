package org.example.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_user")
    private int id;


    @Column(name = "username")
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "password")
    @Size(min = 3, max = 50)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "Customer_Review",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "ID_role")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50) String username) {
        this.username = username;
    }

    public @Size(min = 3, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 3, max = 50) String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
