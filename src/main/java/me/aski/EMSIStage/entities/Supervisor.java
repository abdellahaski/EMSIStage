package me.aski.EMSIStage.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("SUPERVISOR")
public class Supervisor extends User {
    @OneToMany(mappedBy="supervisor",fetch = FetchType.LAZY)
    private List<Internship> internships=new ArrayList<>();

    public Supervisor(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active, Set<Role> roles) {
        super(email, username, password, firstName, lastName, active, roles);
    }

    public Supervisor(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active) {
        super(email, username, password, firstName, lastName, active);
    }

    public Supervisor(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active, Set<Role> roles) {
        super(email, username, password, firstName, lastName, phone, active, roles);
    }

    public Supervisor(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active) {
        super(email, username, password, firstName, lastName, phone, active);
    }

    public Supervisor() {
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void setInternships(List<Internship> internships) {
        this.internships = internships;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                super.toString() +
                '}';
    }
}
