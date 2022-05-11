package com.example.masterdetailjava.user.service;

import com.example.masterdetailjava.user.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
    boolean createUser(UserDTO userDTO);
    boolean deleteUser(String login);
    UserDTO getUser(String login);
}
