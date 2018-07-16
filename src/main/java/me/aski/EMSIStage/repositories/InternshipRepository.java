package me.aski.EMSIStage.repositories;

import me.aski.EMSIStage.entities.Internship;
import me.aski.EMSIStage.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Integer> {


    @Query("select i from Internship i where i.id= :id")
    Internship findInternshipByID(@Param("id")int id);

    @Query("select i from Internship i where i.student= :student")
    List<Internship> findInternshipByStudent(@Param("student") Student student);

    @Query("select COUNT(i) from Internship i where i.student= :student")
    int countInternshipByStudent(@Param("student") Student student);

    //@Query("select i from Internship i where i.student= :student order by i.dateFrom desc")
    @Query(value="SELECT * FROM internship where student = :id ORDER BY date_from desc LIMIT 1", nativeQuery = true)
    Internship getLastInternshipByStudent(@Param("id") int id);

}
