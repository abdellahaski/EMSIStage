package me.aski.EMSIStage.service;


import me.aski.EMSIStage.entities.*;
import me.aski.EMSIStage.repositories.OrganizationRepository;
import me.aski.EMSIStage.repositories.RoleRepository;
import me.aski.EMSIStage.repositories.InternshipRepository;
import me.aski.EMSIStage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.toIntExact;

@Service("userService")
public class ServiceImpl implements iService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InternshipRepository internshipRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, InternshipRepository internshipRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.internshipRepository = internshipRepository;
        this.organizationRepository = organizationRepository;
    }


    @Override
    public User findUserByid(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }
    @Override
    public void saveUserPassChanged(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void newUser(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("STUDENT");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }
    @Override
    public void newUser(User user, String role) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void newUser(User user, Set<String> rolesNames) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        for (String R : rolesNames) {
            Role userRole = roleRepository.findByRole(R);
            roles.add(userRole);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return userRepository.findAllAdmins();
    }

    @Override
    public List<Student> findAllStudents() {
        return userRepository.findAllStudents();
    }

    @Override
    public List<Supervisor> findAllSupervisors() {
        return userRepository.findAllSupervisors();
    }

    @Override
    public void saveInternship(Internship internship) {
        internshipRepository.save(internship);
    }

    @Override
    public List<Internship> findAllInternship() {
        return internshipRepository.findAll();
    }

    @Override
    public User findStudentByID(int id) {
        return userRepository.findStudentByID(id);
    }

    @Override
    public User findStudentByMat(int mat) {
        return userRepository.findStudentByMat(mat);
    }

    @Override
    public int adminCount() {
        return userRepository.adminCount();
    }

    @Override
    public int supervisorCount() {
        return userRepository.supervisorCount();
    }

    @Override
    public int studentCount() {
        return userRepository.studentCount();
    }

    @Override
    public int internshipCount() {
        return toIntExact(internshipRepository.count());
    }

    @Override
    public String userType(User user) {
        if(user instanceof Student)
            return "Student";
        if (user instanceof Supervisor)
            return "Supervisor";
        if (user instanceof Admin)
            return "Admin";

        return null;
    }

    @Override
    public String getUserPassByID(int id) {
        return userRepository.getUserPassByID(id);
    }

    @Override
    public Internship findInternshipByID(int id) {
        return internshipRepository.findInternshipByID(id);
    }

    @Override
    public Supervisor findRandomSupervisor() {
        return userRepository.findRandomSupervisor();
    }

    @Override
    public List<Internship> findInternshipByStudent(Student student) {
        return internshipRepository.findInternshipByStudent(student);
    }

    @Override
    public int countInternshipByStudent(Student student) {
        return internshipRepository.countInternshipByStudent(student);
    }

    @Override
    public Internship getLastInternshipByStudent(int id) {
        return internshipRepository.getLastInternshipByStudent(id);
    }


}
