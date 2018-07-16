package me.aski.EMSIStage;

import me.aski.EMSIStage.entities.*;
import me.aski.EMSIStage.repositories.RoleRepository;
import me.aski.EMSIStage.repositories.UserRepository;
import me.aski.EMSIStage.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class EmsiStageApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final iService service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmsiStageApplication(UserRepository userRepository, RoleRepository roleRepository, iService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmsiStageApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //Thread.sleep(10000);

        Role R1 = new Role("ADMIN");
        roleRepository.save(R1);
        Role R2 = new Role("STUDENT");
        roleRepository.save(R2);
        Role R3 = new Role("SUPERVISOR");
        roleRepository.save(R3);



        User U1 = new Admin("Abdellah@aski.me", "AbdellahASKI", "654321", "Abdellah", "ASKI","0707970909", true);

        User U2 = new Student("es_Student01@aski.me","Student01","654321","Gibb","Jarville","0600000000",true,131778,"IIR",4);
        User U3 = new Supervisor("Supervisor01@aski.me","Supervisor01","654321","Lennie","Farnhill","0600000000",true);

        User U4 = new Student("es_Student02@aski.me","Student02","654321","Markos","Sumpter","0600000000",true,170859,"GI",3);
        User U5 = new Supervisor("es_supervisor02@aski.me","Supervisor02","654321","Bradan","Waddam","0600000000",true);

        User U6 = new Student("es_Student03@aski.me","Student03","654321","Gawain","April","0600000000",true,170233,"IAII",2);
        User U7 = new Student("es_Student04@aski.me","Student04","654321","Darda","Fender","0600000000",true,141320,"IIR",3);
        User U8 = new Student("es_Student05@aski.me","Student05","654321","Edouard","Tebbe","0600000000",true,171008,"GI",2);
        User U9 = new Student("es_Student06@aski.me","Student06","654321","Cornelia","Dellenbach","0600000000",true,166282,"IFA",1);

        service.newUser(U1, "ADMIN");
        service.newUser(U2, "STUDENT");
        service.newUser(U3, "SUPERVISOR");
        service.newUser(U4, "STUDENT");
        service.newUser(U5, "SUPERVISOR");
        service.newUser(U6, "STUDENT");
        service.newUser(U7, "STUDENT");
        service.newUser(U8, "STUDENT");
        service.newUser(U9, "STUDENT");



        LocalDate dateFrom=LocalDate.of(2017,7,16);
        LocalDate dateTo=LocalDate.of(2017,9,16);
        Organization organization= new Organization("HPS","Monetique","Casablanca");
        Internship internship = new Internship(ChronoUnit.DAYS.between(dateFrom, dateTo),dateFrom,dateTo,"Stage d’application",organization, (Student) U2, (Supervisor) U3);

        ((Student) U2).getInternships().add(internship);

        dateFrom = LocalDate.of(2018, 7, 16);
        dateTo = LocalDate.of(2018, 9, 16);

        Internship internship2 = new Internship(ChronoUnit.DAYS.between(dateFrom, dateTo),dateFrom,dateTo,"Stage de fin d’études",organization, (Student) U2,(Supervisor) U5);

        ((Supervisor) U3).getInternships().add(internship);

        ((Supervisor) U5).getInternships().add(internship2);
        service.saveInternship(internship);
        service.saveInternship(internship2);

        service.saveUser(U3);
        service.saveUser(U5);
        service.saveUser(U2);



    }
}
