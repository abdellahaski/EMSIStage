package me.aski.EMSIStage.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "organization")
public class Organization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name")
    private String name;

    @Column(name = "activity")
    @NotEmpty(message = "*Please provide an activity")
    private String activity;

    @Column(name = "city")
    @NotEmpty(message = "*Please provide a city")
    private String city;

    public Organization(@NotEmpty(message = "*Please provide a name") String name, @NotEmpty(message = "*Please provide an activity") String activity, @NotEmpty(message = "*Please provide a city") String city) {
        this.name = name;
        this.activity = activity;
        this.city = city;
    }

    public Organization(@NotEmpty(message = "*Please provide a name") String name, @NotEmpty(message = "*Please provide a city") String city) {
        this.name = name;
        this.city = city;
    }

    public Organization() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
