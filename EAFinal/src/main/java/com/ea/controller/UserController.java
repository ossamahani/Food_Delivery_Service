package com.ea.controller;

import com.ea.domain.*;
import com.ea.service.IBusinessService;
import com.ea.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ossama on 9/25/2016.
 */


@Controller
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    IBusinessService businessService;


    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request,Map<String,Object> map) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        HttpSession session = request.getSession();
        if(roles.contains(UserRole.ROLE_ADMIN.name())){
            User user = new Admin();
            session.setAttribute("user", user);
            return "admin/dashboard";
        }else if(roles.contains(UserRole.ROLE_BUSINESS.name())){
            session.setAttribute("user", userService.getUserByUsername(userName));
            return "redirect:business/dashboard";
        }else{
            session.setAttribute("user", userService.getUserByUsername(userName));
            map.put("businessList", businessService.findAll());
            return "redirect:consumer/dashboard";
        }

    }


    @RequestMapping(value="/user/check/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkUsernameAvailability(@PathVariable("username") String username)
    {
        return userService.getUserByUsername(username)== null?"200":"400";
    }



    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String registerNewUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userRole") String userRole,@RequestParam("email") String email, Model model ) {

        if (username.equals("") || password.equals("") || email.equals("")) {
            model.addAttribute("error", "The username or password can not be empty");
            return "register";
        }

        if(emailExist(email)){
            model.addAttribute("error", "This email already exist");
            return "register";
        }

        System.out.println("email = " + email);

        if(userService.getUserByUsername(username) == null)
        {
            if (userRole.equalsIgnoreCase(UserRole.ROLE_CONSUMER.name())) {
                Consumer consumer = new Consumer();
                consumer.setEnabled(true);
                consumer.setUsername(username);
                consumer.setPassword(password);
                consumer.setEmail(email);
                userService.save(consumer);
            } else if (userRole.equalsIgnoreCase(UserRole.ROLE_BUSINESS.name())) {
                Business business = new Business();
                business.setEnabled(true);
                business.setUsername(username);
                business.setPassword(password);
                business.setEmail(email);
                userService.save(business);
            } else {
                Admin admin = new Admin();
                admin.setEnabled(true);
                admin.setUsername(username);
                admin.setPassword(password);
                admin.setEmail(email);
                userService.save(admin);
            }
            return "login";
        }
        else {
            model.addAttribute("error", "The username '" +username+ "' has registered before, please select another one!");
            return "register";
        }

    }

    public boolean emailExist(String email){
        List<User> users =  userService.findUserByEmail(email);
        System.out.println("users = " + users);
        System.out.println("users = " + users.size());
        if(users.size() >= 1){
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/validateUser/{username:.+}",method = RequestMethod.GET)
    public String userValidation(@PathVariable("username") String username, Model model){
        System.out.println("username = " + username);
        User user = userService.getUserByUsername(username);
        System.out.println(user.getUsername());
        user.setEnabled(true);
        userService.save(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        return "login";
    }





}

