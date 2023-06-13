package com.subhajit.sbsecurity.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestSecurityController {

    @GetMapping("/get-public-user")
    public String getPublicUser(){
        return "This is get public user!";
    }

    @PostMapping("/post-public-user")
    public String postPublicUser(){
        return "This is post public user!";
    }

    @PutMapping("/put-public-user")
    public String putPublicUser(){
        return "This is put public user!";
    }

    @DeleteMapping("/delete-public-user")
    public String deletePublicUser(){
        return "This is delete public user!";
    }

    @GetMapping("/get-private-user")
    public String getPrivateUser(){
        return "This is get private user!";
    }

    @PostMapping("/post-private-user")
    public String postPrivateUser(){
        return "This is post private user!";
    }

    @PutMapping("/put-private-user")
    public String putPrivateUser(){
        return "This is put private user!";
    }

    @DeleteMapping("/delete-private-user")
    public String deletePrivateUser(){
        return "This is delete private user!";
    }

    // @Secured("ROLE_admin") // Need to append ROLE_ with the actual role name
    //@PreAuthorize("hasRole('admin')")
    @GetMapping("/admin-user")
    public String admin(){
        return "This is admin user!";
    }

    @GetMapping("/restricted-user")
    public String restrictedUser(){
        return "This is restricted user!";
    }

}
