package com.subhajit.sbsecurity.controller;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('admin')") // No need to append ROLE_ with the actual role name
    @GetMapping("/admin-user")
    public String admin(){
        return "This is admin user!";
    }

    @GetMapping("/no-authorize-user")
    public String restrictedUser(){
        return "This is no authorize user!";
    }

    @RequestMapping(value = "/options-method", method = RequestMethod.OPTIONS)
    public String optionsMethod(){
        return "This is options method!";
    }

    @RequestMapping(value = "/get-method", method = RequestMethod.GET)
    public String getMethod(){
        return "This is get method!";
    }

    @RequestMapping(value = "/post-method", method = RequestMethod.POST)
    public String postMethod(){
        return "This is post method!";
    }

    @RequestMapping(value = "/put-method", method = RequestMethod.PUT)
    public String putMethod(){
        return "This is put method!";
    }

    @RequestMapping(value = "/delete-method", method = RequestMethod.DELETE)
    public String deleteMethod(){
        return "This is delete method!";
    }

}
