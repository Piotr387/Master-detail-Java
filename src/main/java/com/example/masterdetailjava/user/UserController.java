package com.example.masterdetailjava.user;

import com.example.masterdetailjava.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestParam String login){
        return userService.deleteUser(login);
    }

    @GetMapping("/{login}")
    public UserDTO getUserDetails(@PathVariable(name = "login") String login){
        return userService.getUser(login);
    }

}
