package com.example.CompleteEmpManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Basic User Information


    // Username must be unique
    @Column(nullable = false, unique = true)
    private String username;

    // Email must be unique
    @Column(nullable = false, unique = true)
    private String email;

    // Password will be stored as a BCrypt encoded password
    @Column(nullable = false)
    private String password;

    // Indicates whether the user account is active
    @Column(nullable = false)
    private boolean enabled = true;



    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // User * -------- * Role


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",

            // Foreign key referring to users table
            joinColumns = @JoinColumn(name = "user_id"),

            // Foreign key referring to roles table
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();



    // User 1 -------- 1 Employee

    @OneToOne(mappedBy = "user")
    private Employee employee;




    public User() {
    }


    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }




    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}