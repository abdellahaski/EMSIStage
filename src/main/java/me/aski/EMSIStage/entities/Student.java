package me.aski.EMSIStage.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User{
    @DecimalMin(value= "0", inclusive=false, message = "*Please provide a Mat")
    private int mat;
    @NotEmpty(message = "*Please provide a Branch")
    private String branch;
    @NotNull(message = "*Please provide a Year")
    @DecimalMin(value= "0", inclusive=false, message = "*Please provide a year")
    private int year;

    @OneToMany(mappedBy="student",fetch = FetchType.LAZY)
    private List<Internship> internships = new ArrayList<>();

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void setInternships(List<Internship> internships) {
        this.internships = internships;
    }

    public Student() {
    }

    public Student(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active, Set<Role> roles, int mat, @NotEmpty(message = "*Please provide a Branch") String branch, @NotNull(message = "*Please provide a Year") @DecimalMin(value = "0", inclusive = false, message = "*Please provide a year") int year) {
        super(email, username, password, firstName, lastName, phone, active, roles);
        this.mat = mat;
        this.branch = branch;
        this.year = year;
    }

    public Student(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active, Set<Role> roles, int mat, @NotEmpty(message = "*Please provide a Branch") String branch, @NotNull(message = "*Please provide a Year") @DecimalMin(value = "0", inclusive = false, message = "*Please provide a year") int year) {
        super(email, username, password, firstName, lastName, active, roles);
        this.mat = mat;
        this.branch = branch;
        this.year = year;
    }

    public Student(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Username must have at least 4 characters") @NotEmpty(message = "*Please provide a username") String username, @Length(min = 6, message = "*Password must have at least 6 characters") @NotEmpty(message = "*Please provide a password") String password, @NotEmpty(message = "*Please provide a first name") String firstName, @NotEmpty(message = "*Please provide a last name") String lastName, String phone, boolean active, int mat, @NotEmpty(message = "*Please provide a Branch") String branch, @NotNull(message = "*Please provide a Year") @DecimalMin(value = "0", inclusive = false, message = "*Please provide a year") int year) {
        super(email, username, password, firstName, lastName, phone, active);
        this.mat = mat;
        this.branch = branch;
        this.year = year;
    }

    public Student(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 4, message = "*Your username must have at least 4 characters") @NotEmpty(message = "*Please provide your username") String username, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your first name") String firstName, @NotEmpty(message = "*Please provide your last name") String lastName, boolean active, int mat, @NotEmpty(message = "*Please provide a Branch") String branch, @NotNull(message = "*Please provide a Year") @DecimalMin(value = "0", inclusive = false, message = "*Please provide a year") int year) {
        super(email, username, password, firstName, lastName, active);
        this.mat = mat;
        this.branch = branch;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                ", mat='" + mat + '\'' +
                ", branch='" + branch + '\'' +
                ", year=" + year +
                '}';
    }
}
