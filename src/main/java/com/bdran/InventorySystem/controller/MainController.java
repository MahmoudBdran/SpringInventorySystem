package com.bdran.InventorySystem.controller;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    String getAuthusrname;
//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("logoutuser", getAuthusrname);
//        return "login";
//    }



    @GetMapping("")
    public String home(Model model){
//        getAuthusrname=getAuthenticatedUsername();
        model.addAttribute("user","the user");

        return "landingPage";
    }

//    public String getAuthenticatedUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        return username;
//    }

}
