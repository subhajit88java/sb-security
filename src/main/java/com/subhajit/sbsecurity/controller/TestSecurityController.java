package com.subhajit.sbsecurity.controller;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestSecurityController {

    @GetMapping("/public-user")
    public String publicUser(){
        System.out.println("PUBLIC USER");
        return "This is public user!";
    }

    @GetMapping("/private-user")
    public String privateUser(){
        System.out.println("PRIVATE USER");
        return "This is private user!";
    }

    @GetMapping("/admin")
    public String admin(){
        System.out.println("ADMIN");
        return "This is admin!";
    }

    @GetMapping("/super-public-user")
    public String superPublicUser(){
        System.out.println("SUPER PUBLIC USER");
        return "This is super public user!";
    }

}
