package me.aski.EMSIStage.service;

import me.aski.EMSIStage.entities.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface iService {
    User findUserByid(int id);
    User findUserByEmail(String email);

    User findUserByUsername(String username);

    void saveUser(User user);

    void saveUserPassChanged(User user);

    void newUser(User user);

    void newUser(User user, String role);

    void newUser(User user, Set<String> rolesNames);

    List<Admin> findAllAdmins();

    List<Student> findAllStudents();

    List<Supervisor> findAllSupervisors();

    void saveInternship(Internship internship);

    List<Internship> findAllInternship();

    User findStudentByID(int id);

    User findStudentByMat(int mat);

    int adminCount();
    int supervisorCount();
    int studentCount();

    int internshipCount();

    String userType(User user);

    String getUserPassByID(int id);

    Internship findInternshipByID(int id);

    Supervisor findRandomSupervisor();

    List<Internship> findInternshipByStudent(Student student);

    int countInternshipByStudent(Student student);

    Internship getLastInternshipByStudent(int id);
}
