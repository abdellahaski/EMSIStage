package me.aski.EMSIStage.controllers;

import me.aski.EMSIStage.service.iService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private iService userService;

    /*@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth.isAuthenticated());
        modelAndView.setViewName("signin");
        return modelAndView;
    }*/
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "signin";
    }
    @RequestMapping(value = {"/403"})
    public String accessdenied() {
        return "403";
    }
    @RequestMapping(value="/info")
    public String info(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        System.out.println("name:"+auth.getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
        return "redirect:/";
    }

/*
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        //User user = new User();
        //modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());


        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        userExists=userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            System.out.print("12");
            userService.newUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            //modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
*/
/*
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth.getName());
        //System.out.println(auth.getPrincipal());

        User user = userService.findUserByUsername(auth.getName());

        modelAndView.addObject("userName", "Welcome " + user.toString());
        modelAndView.addObject("adminMessage", auth.getAuthorities());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    */
}
