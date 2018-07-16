package me.aski.EMSIStage.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active, Set<Role> roles) {
        super(email, username, password, firstName, lastName, active, roles);
    }

    public Admin(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active) {
        super(email, username, password, firstName, lastName, active);
    }

    public Admin(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active) {
        super(email, username, password, firstName, lastName, phone, active);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                '}';
    }
/*
    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }*/


}
