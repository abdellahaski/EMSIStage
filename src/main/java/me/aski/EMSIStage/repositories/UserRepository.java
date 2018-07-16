package me.aski.EMSIStage.repositories;

import me.aski.EMSIStage.entities.Admin;
import me.aski.EMSIStage.entities.Student;
import me.aski.EMSIStage.entities.Supervisor;
import me.aski.EMSIStage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByUsername(String username);

    @Query("select u from User u where TYPE(u) = 'Admin'")
    List<Admin> findAllAdmins();
    @Query("select u from User u where TYPE(u) = 'Student'")
    List<Student> findAllStudents();
    @Query("select u from User u where TYPE(u) = 'Supervisor'")
    List<Supervisor> findAllSupervisors();

    @Query("select u from User u where TYPE(u) = 'Student' AND u.id= :id")
    User findStudentByID(@Param("id")int id);
    @Query("select u from User u where TYPE(u) = 'Student' AND u.mat= :mat")
    User findStudentByMat(@Param("mat")int mat);


    @Query("select COUNT(u) from User u where TYPE(u) = 'Admin'")
    int adminCount();
    @Query("select COUNT(u) from User u where TYPE(u) = 'Supervisor'")
    int supervisorCount();
    @Query("select COUNT(u) from User u where TYPE(u) = 'Student'")
    int studentCount();

    @Query("select u from User u where u.id= :id")
    User findUserByID(@Param("id")int id);

    @Query("select u.password from User u where u.id= :id")
    String getUserPassByID(@Param("id")int id);

    @Query(value="SELECT * FROM User where type = 'SUPERVISOR' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Supervisor findRandomSupervisor();
}
