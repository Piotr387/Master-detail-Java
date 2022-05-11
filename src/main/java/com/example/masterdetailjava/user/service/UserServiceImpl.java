package com.example.masterdetailjava.user.service;

import com.example.masterdetailjava.user.UserDTO;
import com.example.masterdetailjava.user.UserEntity;
import com.example.masterdetailjava.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map( userEntity -> new ModelMapper().map(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean createUser(UserDTO userDTO) {
        //In this place I would encode provided password with Spring Security
        userRepository.save(new ModelMapper().map(userDTO,UserEntity.class));
        return true;
    }

    @Override
    public boolean deleteUser(String login) {
        userRepository.findByLogin(login).ifPresentOrElse(
                userEntity -> {userRepository.delete(userEntity);},
                () -> {throw new RuntimeException("No such a user with provided login");});
        return true;
    }

    @Override
    public UserDTO getUser(String login) {
        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow( () -> {
            throw new RuntimeException("No such a user with provided login");
        });
        return new ModelMapper().map(userEntity, UserDTO.class);
    }

}
