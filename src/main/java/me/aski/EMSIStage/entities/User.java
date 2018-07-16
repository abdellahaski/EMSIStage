package me.aski.EMSIStage.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length = 10)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "username")
    @Length(min = 4, message = "*Username must have at least 4 characters")
    @NotEmpty(message = "*Please provide a username")
    private String username;

    @Column(name = "password")
    @Length(min = 6, message = "*Password must have at least 6 characters")
    @NotEmpty(message = "*Please provide a password")
    @org.springframework.data.annotation.Transient
    private String password;

    @Column(name = "firstName")
    @NotEmpty(message = "*Please provide a first name")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "*Please provide a last name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active, Set<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active, Set<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.active = active;
        this.roles = roles;
    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.active = active;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                        ", email='" + email + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", phone='" + phone + '\'' +
                        ", active=" + active +
                        ", roles=" + roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
