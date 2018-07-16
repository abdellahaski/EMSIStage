package me.aski.EMSIStage.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "internship")
public class Internship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student")
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="supervisor")
    private Supervisor supervisor;

    public Internship(Long duration, LocalDate dateFrom, LocalDate dateTo, String type, Organization organization) {
        this.duration = duration;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
        this.organization = organization;
    }

    public Internship(Long duration, LocalDate dateFrom, LocalDate dateTo, String type, Organization organization, Student student) {
        this.duration = duration;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
        this.organization = organization;
        this.student = student;
    }

    public Internship(Long duration, LocalDate dateFrom, LocalDate dateTo, String type, Organization organization, Student student, Supervisor supervisor) {
        this.duration = duration;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.type = type;
        this.organization = organization;
        this.student = student;
        this.supervisor = supervisor;
    }

    public Internship() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Internship{" +
                "id=" + id +
                ", duration=" + duration +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", type='" + type + '\'' +
                ", organization=" + organization +
                ", student=" + student +
                ", supervisor=" + supervisor +
                '}';
    }
}
