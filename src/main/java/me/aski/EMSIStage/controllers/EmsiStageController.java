package me.aski.EMSIStage.controllers;

import me.aski.EMSIStage.entities.*;
import me.aski.EMSIStage.mail.MailService;
import me.aski.EMSIStage.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.temporal.ChronoUnit;

@Controller
public class EmsiStageController {


    @Autowired
    private iService service;
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/")
    public String index(Model model, @ModelAttribute("currentUser") User user ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth);
        if ((auth instanceof AnonymousAuthenticationToken))
            return "redirect:/login";
        else {

            for (GrantedAuthority gAuth : auth.getAuthorities()) {
                if (gAuth.getAuthority().equals("ROLE_ADMIN")) {
                    model.addAttribute("StudentCount", service.studentCount());
                    model.addAttribute("SupervisorCount", service.supervisorCount());
                    model.addAttribute("AdminCount", service.adminCount());
                    model.addAttribute("InternshipCount", service.internshipCount());

                    return "admin/index";
                } else if (gAuth.getAuthority().equals("ROLE_SUPERVISOR"))
                {
                    model.addAttribute("StudentCount", service.studentCount());
                    model.addAttribute("SupervisorCount", service.supervisorCount());
                    model.addAttribute("AdminCount", service.adminCount());
                    model.addAttribute("InternshipCount", service.internshipCount());
                    return "supervisor/index";
                }
                else if (gAuth.getAuthority().equals("ROLE_STUDENT")) {
                    model.addAttribute("InternshipCount", service.countInternshipByStudent((Student) user));
                    System.out.println(service.getLastInternshipByStudent(user.getId()));
                    model.addAttribute("LastInternship",service.getLastInternshipByStudent(user.getId()));
                    return "student/index";
                } else
                    return "403";


            }
        }
        return "admin/none";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
            model.addAttribute("currentUser", service.findUserByUsername(auth.getName()));
    }

    /**
     * Add users controllers
     */
    @GetMapping("/admin/addstudent")
    public String addStudent(Model model) {
        model.addAttribute("Student", new Student());
        /*char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
        String randomStr = RandomStringUtils.random( 10, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        System.out.println( randomStr );
        model.addAttribute("")*/


        return "admin/addstudent";
    }

    @PostMapping(value = "/admin/addstudent")
    public ModelAndView addStudent(@Valid @ModelAttribute("Student") Student student,
                                   BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();


        User userExists = service.findUserByEmail(student.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.Student",
                            "There is already a user registered with the email provided");
        }
        userExists = service.findUserByUsername(student.getUsername());
        if (userExists != null) {
            System.out.println("Username");
            bindingResult
                    .rejectValue("username", "error.Student",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/addstudent");
        } else {
            String pass = student.getPassword();
            service.newUser(student, "Student");
            modelAndView.setViewName("admin/addstudent");
            modelAndView.addObject("successMessage", "The Student " + student.getFirstName()
                    + " "
                    + student.getLastName()
                    + " Has been added successfully");
            modelAndView.addObject("successStudentPass", pass);
            model.addAttribute("loggedInAdminEmail", service.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getEmail());
        }
        return modelAndView;
    }

    @GetMapping("/admin/addsupervisor")
    public String addSupervisor(Model model) {
        model.addAttribute("Supervisor", new Supervisor());
        return "admin/addsupervisor";
    }

    @PostMapping(value = "/admin/addsupervisor")
    public ModelAndView addSupervisor(@Valid @ModelAttribute("Supervisor") Supervisor supervisor,
                                      BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = service.findUserByEmail(supervisor.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.Student",
                            "There is already a user registered with the email provided");
        }
        userExists = service.findUserByUsername(supervisor.getUsername());
        if (userExists != null) {
            System.out.println("Username");
            bindingResult
                    .rejectValue("username", "error.Student",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/addsupervisor");
        } else {
            String pass = supervisor.getPassword();
            service.newUser(supervisor, "SUPERVISOR");
            modelAndView.setViewName("admin/addsupervisor");
            modelAndView.addObject("successMessage", "The Student " + supervisor.getFirstName()
                    + " "
                    + supervisor.getLastName()
                    + " Has been added successfully");
            modelAndView.addObject("successSupervisorPass", pass);
            model.addAttribute("loggedInAdminEmail", service.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getEmail());
        }
        return modelAndView;
    }

    @GetMapping("/admin/addadmin")
    public String addAdmin(Model model) {
        model.addAttribute("Admin", new Admin());

        return "admin/addadmin";
    }

    @PostMapping(value = "/admin/addadmin")
    public ModelAndView addAdmin(@Valid @ModelAttribute("Admin") Admin admin,
                                 BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = service.findUserByEmail(admin.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.Student",
                            "There is already a user registered with the email provided");
        }
        userExists = service.findUserByUsername(admin.getUsername());
        if (userExists != null) {
            System.out.println("Username");
            bindingResult
                    .rejectValue("username", "error.Student",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/addadmin");
        } else {
            String pass = admin.getPassword();
            service.newUser(admin, "ADMIN");
            modelAndView.setViewName("admin/addadmin");
            modelAndView.addObject("successMessage", "The Student " + admin.getFirstName()
                    + " "
                    + admin.getLastName()
                    + " Has been added successfully");
            modelAndView.addObject("successAdminPass", pass);
            model.addAttribute("loggedInAdminEmail", service.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getEmail());
        }
        return modelAndView;
    }


    /**
     * Listing Users
     */

    @GetMapping("/admin/liststudents")
    public String listStudents(Model model) {
        model.addAttribute("listStudents", service.findAllStudents());
        return "admin/liststudents";
    }

    @GetMapping("/supervisor/liststudents")
    public String listStudentsForSup(Model model) {
        model.addAttribute("listStudents", service.findAllStudents());
        return "supervisor/liststudents";
    }

    @GetMapping("/admin/listsupervisors")
    public String listSupervisors(Model model) {
        model.addAttribute("listSupervisors", service.findAllSupervisors());
        return "admin/listsupervisors";
    }

    @GetMapping("/admin/listadmins")
    public String listAdmins(Model model) {
        model.addAttribute("listAdmins", service.findAllAdmins());
        return "admin/listadmins";
    }


    /**
     * Listing stages
     */
    @GetMapping("/admin/listinternships")
    public String listInterships(Model model) {
        model.addAttribute("listInternship", service.findAllInternship());
        return "admin/listinternships";
    }

    @GetMapping("/supervisor/listinternships")
    public String listIntershipsForSup(Model model) {
        model.addAttribute("listInternship", service.findAllInternship());
        return "supervisor/listinternships";
    }

    @GetMapping("/student/listinternships")
    public String listIntershipsForStd(Model model, @ModelAttribute("currentUser") User user) {
        model.addAttribute("listInternship", ((Student) user).getInternships());
        return "student/listinternships";
    }

    /**
     * Student Page
     */
    @GetMapping(value = "/admin/student", params = "id")
    public String getStudentInfo(@RequestParam("id") int id, @ModelAttribute("currentUser") User user, Model model) {
        System.out.println(user.toString());

        Student S;
        if ((S = (Student) service.findStudentByMat(id)) != null)
            model.addAttribute("Student", S);
        else if ((S = (Student) service.findStudentByID(id)) != null)
            model.addAttribute("Student", S);
        else
            model.addAttribute("Error", "Not Found");
        return "admin/student";
    }

    @GetMapping(value = "/supervisor/student", params = "id")
    public String getStudentInfoForSup(@RequestParam("id") int id, Model model) {

        Student S;
        if ((S = (Student) service.findStudentByMat(id)) != null)
            model.addAttribute("Student", S);
        else if ((S = (Student) service.findStudentByID(id)) != null)
            model.addAttribute("Student", S);
        else
            model.addAttribute("Error", "Not Found");
        return "supervisor/student";
    }

    @GetMapping(value = "/student/supervisor", params = "id")
    public String getSupervisorInfoForstd(@RequestParam("id") int id, Model model) {

        Supervisor S;
        if ((S = (Supervisor) service.findUserByid(id)) != null)
            model.addAttribute("Supervisor", S);
        else
            model.addAttribute("Error", "Not Found");
        return "student/supervisor";
    }
    /**
     * Internship page
     *
     */

    @GetMapping(value = "/admin/internship", params = "id")
    public String getInternshipInfo(@RequestParam("id") int id, Model model) {
        Internship i ;
        if ((i=service.findInternshipByID(id)) != null)
            model.addAttribute("Internship", i);
        else
            model.addAttribute("Error", "Not Found");
        return "admin/internship";
    }

    @GetMapping(value = "/supervisor/internship", params = "id")
    public String getInternshipInfoForSup(@RequestParam("id") int id, Model model) {
        Internship i ;
        if ((i=service.findInternshipByID(id)) != null)
            model.addAttribute("Internship", i);
        else
            model.addAttribute("Error", "Not Found");
        return "supervisor/internship";
    }
    @GetMapping(value = "/student/internship", params = "id")
    public String getInternshipInfoForStd(@RequestParam("id") int id, Model model) {
        Internship i ;
        if ((i=service.findInternshipByID(id)) != null)
            model.addAttribute("Internship", i);
        else
            model.addAttribute("Error", "Not Found");
        return "student/internship";
    }

    /**
     * Profile Page
     */
    @GetMapping(value = "/profile")
    public String profile(@ModelAttribute("currentUser") User user, Model model) {
        String userType=service.userType(user);
        model.addAttribute("userType", userType);
        if(userType.equals("Student"))
            return "student/profile";
        if(userType.equals("Supervisor"))
            return "supervisor/profile";
        if (userType.equals("Admin"))
            return "admin/profile";

        return "403";
    }


    /**
     * Profile Edit
     */

    @PostMapping(value = "/profile")
    public ModelAndView profileEdit(@ModelAttribute("currentUser") User user, boolean newPass,
                                    BindingResult bindingResult, Model model) {
        boolean success=false;
        ModelAndView modelAndView = new ModelAndView();
        String userType=service.userType(user);
        modelAndView.addObject("userType", userType);
        if (!newPass || user.getPassword().isEmpty()) {
            user.setPassword(service.getUserPassByID(user.getId()));
            service.saveUser(user);
            success=true;
        } else if (user.getPassword().length() >= 6) {
            service.saveUserPassChanged(user);
            success=true;
        } else if (user.getPassword().length() < 6) {
            bindingResult
                    .rejectValue("password", "error.currentUser",
                            "Password must have at least 6 characters");
        } else {
            modelAndView.addObject("errorMessage", "Error !");
        }
        if (success)
        {
            modelAndView.addObject("successMessage","Your changes has been saved");
        }
        if(userType.equals("Student"))
            modelAndView.setViewName("student/profile");
        else
            if(userType.equals("Supervisor"))
            modelAndView.setViewName("supervisor/profile");
        else
            if (userType.equals("Admin"))
                modelAndView.setViewName("admin/profile");
        else
            modelAndView.setViewName("403");
        return modelAndView;
    }


    /**
     * New internship by Student
     *
     */

    @GetMapping("/student/newinternship")
    public String newInternship(Model model) {
        model.addAttribute("Internship", new Internship());
        return "/student/newinternship";
    }

    @PostMapping(value = "/student/newinternship")
    public ModelAndView newInternship(@Valid @ModelAttribute("Internship") Internship internship,
                                      @ModelAttribute("currentUser") Student student,
                                      BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();



        internship.setDuration(ChronoUnit.DAYS.between(internship.getDateFrom(), internship.getDateTo()));
        System.out.println(internship.toString());
        internship.setStudent(student);
        Supervisor supervisor = service.findRandomSupervisor();
        internship.setSupervisor(supervisor);
        supervisor.getInternships().add(internship);

        service.saveInternship(internship);
        service.saveUser(student);
        service.saveUser(supervisor);






        modelAndView.setViewName("redirect:/student/internship?id="+internship.getId()+"&success");

        return modelAndView;
    }

}
